package com.mikufans.blog.application.service;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.domain.aggregate.comment.CommentCond;
import com.mikufans.blog.domain.aggregate.comment.CommentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CommentAppService {




    @Resource
    private ArticleAppService articleAppService;

    public void createComment(CommentEntity commentEntity)
    {

    }

    public CommentEntity getCommentsById(Integer cid)
    {
        return null;
    }

    public void deleteComment(Integer coid)
    {

    }

    public void updateCommentStatus(Integer coid,String status)
    {

    }

    public PageInfo<CommentEntity> getCommentsByCond(CommentCond commentCond,int page,int limit)
    {
        return null;
    }




}
