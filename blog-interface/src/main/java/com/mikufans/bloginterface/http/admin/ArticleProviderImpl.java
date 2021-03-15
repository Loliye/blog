package com.mikufans.bloginterface.http.admin;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.application.service.ArticleAppService;
import com.mikufans.blog.application.service.LogAppService;
import com.mikufans.blog.application.service.MetaAppService;
import com.mikufans.blog.domain.aggregate.article.ArticleEntity;
import com.mikufans.blog.domain.aggregate.meta.MetaEntity;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.constant.Types;
import com.mikufans.bloginterface.common.exception.TipException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
@Slf4j
public class ArticleProviderImpl {
    @Resource
    private ArticleAppService articleAppService;

    @Resource
    private LogAppService logAppService;

    @Resource
    private MetaAppService metaAppService;

    @GetMapping("")
    public String index(@RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "limit",defaultValue = "15") int limit)
    {
        HttpServletRequest request = HttpUtil.getRequest();
        PageInfo<ArticleEntity> articles = articleAppService.getArticles(page, limit);
        request.setAttribute("articles",articles);
        return "admin/article_list";
    }

    @GetMapping(value = "publish")
    public String createArticle()
    {
        HttpServletRequest request = HttpUtil.getRequest();
        List<MetaEntity> categories = metaAppService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }

    @PostMapping(value = "publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto publicArticle(ArticleEntity articleEntity)
    {
        HttpServletRequest request = HttpUtil.getRequest();
        UserEntity loginUser = CommonUtil.getLoginUser();
        articleEntity.setAuthorId(loginUser.getUid());
        articleEntity.setType(Types.ARTICLE.getType());
        if(StringUtils.isBlank(articleEntity.getCategories()))
            articleEntity.setCategories("默认分类");

        try {
            articleAppService.publish(articleEntity);
        } catch (Exception e) {
            String msg = "文章发布失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();

    }

    @PostMapping(value = "modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto modifyArticle(ArticleEntity articleEntity)
    {
        UserEntity loginUser = CommonUtil.getLoginUser();
        articleEntity.setAuthorId(loginUser.getUid());
        articleEntity.setType(Types.ARTICLE.getType());

        try {
            articleAppService.updateArticle(articleEntity);
        } catch (Exception e) {
            String msg = "文章编辑失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto delete(@RequestParam int cid)
    {
        try {
            articleAppService.deleteById(cid);
            logAppService.insertLog(null);
        } catch (Exception e) {
            String msg = "文章删除失败";
            if(e instanceof TipException)
                msg=e.getMessage();
            else log.error(msg,e);
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }


}
