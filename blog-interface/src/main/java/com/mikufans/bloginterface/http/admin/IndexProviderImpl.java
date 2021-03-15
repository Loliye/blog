package com.mikufans.bloginterface.http.admin;

import com.mikufans.blog.application.service.LogAppService;
import com.mikufans.blog.application.service.SiteAppService;
import com.mikufans.blog.application.service.UserAppService;
import com.mikufans.blog.domain.aggregate.comment.CommentEntity;
import com.mikufans.blog.domain.aggregate.log.LogEntity;
import com.mikufans.blog.domain.aggregate.statistics.StatisticsEntity;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.DateKit;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import com.mikufans.blog.infrastructure.common.WebConst;
import com.mikufans.blog.infrastructure.repository.log.LogActionEnum;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.exception.TipException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("adminIndexController")
@RequestMapping("/admin")
@Slf4j
public class IndexProviderImpl {
    @Resource
    private SiteAppService siteAppService;

    @Resource
    private LogAppService logAppService;

    @Resource
    private UserAppService userAppService;

    @GetMapping(value = {"","/index"})
    public String index()
    {
        log.info("[] enter admin index method", DateKit.getNowTime().toString());
        List<ArticleEntity> articleEntities = siteAppService.recentArticles(5);
        List<CommentEntity> commentEntities = siteAppService.recentComments(5);
        StatisticsEntity statisticsEntity=siteAppService.getStatistics();
        List<LogEntity> logs=logAppService.getLogs(1,5);
        HttpServletRequest request = HttpUtil.getRequest();
        request.setAttribute("comments",commentEntities);
        request.setAttribute("articles",articleEntities);
        request.setAttribute("statistics",statisticsEntity);
        request.setAttribute("logs",logs);
        log.info("exit admin index method");
        return "admin/index";
    }

    @GetMapping(value = "profile")
    public String profile()
    {
        return "admin/profile";
    }

    @GetMapping(value = "logout")
    public String logout()
    {
        log.info("index logout");
        return "admin/login";
    }

    @PostMapping(value = "/profile")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto saveProfile(@RequestParam String screenName,@RequestParam String email)
    {
        UserEntity loginUser = CommonUtil.getLoginUser();
        if(StringUtils.isNotBlank(screenName)&&StringUtils.isNotBlank(email))
        {
            UserEntity userEntity= UserEntity.builder().uid(loginUser.getUid())
                    .screenName(screenName).email(email)
                    .build();
            userAppService.updateByUid(userEntity);
            HttpServletRequest request = HttpUtil.getRequest();
            LogEntity logEntity= LogEntity.builder().action(LogActionEnum.UP_INFO.getAction())
                    .data(CommonUtil.toJsonString(userEntity))
                    .ip(request.getRemoteAddr()).id(loginUser.getUid())
                    .build();

            logAppService.insertLog(logEntity);
            UserEntity user = CommonUtil.getLoginUser();
            user.setEmail(email);
            user.setScreenName(screenName);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY,user);
        }
        return RestResponseDto.ok();
    }

    @PostMapping(value = "/password")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto updatePassword(@RequestParam String oldPwd,@RequestParam String newPwd)
    {
        UserEntity users = CommonUtil.getLoginUser();
        if(StringUtils.isBlank(oldPwd)||StringUtils.isBlank(newPwd))
            return RestResponseDto.fail("请确认输入信息");

        if(!users.getPassword().equals(CommonUtil.MD5encode(users.getUsername()+oldPwd)))
            return RestResponseDto.fail("密码错误");
        if(newPwd.length()<6||newPwd.length()>14)
            return RestResponseDto.fail("请输入6-14位密码");

        try {
            UserEntity temp = new UserEntity();
            temp.setUid(users.getUid());
            String pwd = CommonUtil.MD5encode(users.getUsername() + newPwd);
            temp.setPassword(pwd);
            userAppService.updateByUid(temp);

            HttpServletRequest request = HttpUtil.getRequest();
            HttpSession session = HttpUtil.getSession();
            LogEntity logEntity= LogEntity.builder().action(LogActionEnum.UP_PWD.getAction())
                    .data(null)
                    .ip(request.getRemoteAddr()).id(users.getUid())
                    .build();

            logAppService.insertLog(logEntity);

            //更新session中的数据
            UserEntity original= (UserEntity) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            original.setPassword(pwd);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY,original);
            return RestResponseDto.ok();
        } catch (Exception e){
            String msg = "密码修改失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }

    }
}
