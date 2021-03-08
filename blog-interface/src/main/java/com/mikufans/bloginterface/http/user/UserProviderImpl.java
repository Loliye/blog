package com.mikufans.bloginterface.http.user;

import com.mikufans.blog.application.service.LogAppService;
import com.mikufans.blog.application.service.UserAppService;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.WebConst;
import com.mikufans.blog.infrastructure.repository.user.UserPo;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.exception.TipException;
import com.mikufans.bloginterface.common.util.HttpUtil;
import com.mikufans.bloginterface.common.util.IPUtil;
import com.mikufans.bloginterface.common.util.TaleUtil;
import com.mikufans.bloginterface.http.BaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserProviderImpl extends BaseProvider {

    @Resource
    private UserAppService userAppService;

    @Resource
    private LogAppService logAppService;

    @GetMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseDto login(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam(required = false) String rememberMe) {
        HttpServletRequest request = HttpUtil.getRequest();
        HttpServletResponse response = HttpUtil.getResponse();
        String ip = IPUtil.getIpAddrByRequest(request);
        Integer error_count = cache.hget("login_error_count", ip);
        try {
            UserEntity userEntity = userAppService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userEntity);
            if (StringUtils.isNotBlank(rememberMe))
                TaleUtil.setCookie(response, userEntity.getUid());
            //todo logAppService.insertLog

        } catch (Exception e) {

            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return RestResponseDto.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }

    @GetMapping("/logout")
    public void logout() {
        HttpServletRequest request = HttpUtil.getRequest();
        HttpServletResponse response = HttpUtil.getResponse();
        HttpSession session = HttpUtil.getSession();
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("注销失败", e);
        }
    }

}
