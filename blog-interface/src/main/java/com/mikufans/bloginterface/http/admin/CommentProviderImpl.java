package com.mikufans.bloginterface.http.admin;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.application.service.CommentAppService;
import com.mikufans.blog.domain.aggregate.comment.CommentCond;
import com.mikufans.blog.domain.aggregate.comment.CommentEntity;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.HttpUtil;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.exception.TipException;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/comments")
@Slf4j
public class CommentProviderImpl {
    @Resource
    private CommentAppService commentAppService;

    @GetMapping(value = "")
    public String index(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {
        PageInfo<CommentEntity> comments = commentAppService.getCommentsByCond(new CommentCond(), page, limit);
        HttpServletRequest request = HttpUtil.getRequest();
        request.setAttribute("comments", comments);
        return "admin/comment_list";
    }

    @PostMapping(value = "delete")
    @ResponseBody
    public RestResponseDto deleteComment(@RequestParam Integer coid) {
        try {
            CommentEntity commentsById = commentAppService.getCommentsById(coid);
            if (commentsById == null)
                return RestResponseDto.fail("不存在该评论");
            commentAppService.deleteComment(coid);
        } catch (Exception e) {
            String msg = "评论删除失败";
            if (e instanceof TipException)
                msg = e.getMessage();
            else log.error(msg, e);
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }

    @PostMapping(value = "status")
    @ResponseBody
    public RestResponseDto updateStatus(@RequestParam("coid") Integer coid,
                                        @RequestParam("status") String status) {
        try {
            CommentEntity commentsById = commentAppService.getCommentsById(coid);
            if (commentsById != null)
                commentAppService.updateCommentStatus(coid, status);
            else return RestResponseDto.fail("操作失败");
        } catch (Exception e) {
            String msg = "操作失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();

    }

    @PostMapping(value = "")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseDto reply(@RequestParam Integer coid, @RequestParam String content) {
        if (coid == null || StringUtils.isBlank(content))
            return RestResponseDto.fail("请输入完整评论");

        if (content.length() > 2000) {
            return RestResponseDto.fail("请输入2000个字符以内的回复");
        }

        CommentEntity c = commentAppService.getCommentsById(coid);
        if (null == c) {
            return RestResponseDto.fail("不存在该评论");
        }
        UserEntity users = CommonUtil.getLoginUser();
        content = CommonUtil.cleanXSS(content);
        content = EmojiParser.parseToAliases(content);
        HttpServletRequest request = HttpUtil.getRequest();
        CommentEntity comments = new CommentEntity();
        comments.setAuthor(users.getUsername());
        comments.setAuthorId(users.getUid());
        comments.setCid(c.getCid());
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(users.getHomeUrl());
        comments.setContent(content);
        comments.setMail(users.getEmail());
        comments.setParent(coid);
        try {
            commentAppService.createComment(comments);
        } catch (Exception e) {
            String msg = "回复失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponseDto.fail(msg);
        }
        return RestResponseDto.ok();
    }


}
