package com.mikufans.blog.application.service;

import com.mikufans.blog.domain.aggregate.comment.CommentEntity;
import com.mikufans.blog.domain.aggregate.statistics.StatisticsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteAppService {


    public List<CommentEntity> recentComments(int limit)
    {
        return null;
    }

    public List<ArticleEntity> recentArticles(int limit)
    {
        return null;
    }

    public CommentEntity getComment(Integer coid)
    {
        return null;
    }

    public StatisticsEntity getStatistics()
    {
        return null;
    }

}
