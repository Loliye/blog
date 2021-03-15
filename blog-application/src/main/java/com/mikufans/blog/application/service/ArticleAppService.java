package com.mikufans.blog.application.service;

import com.github.pagehelper.PageInfo;
import com.mikufans.blog.domain.aggregate.article.ArticleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleAppService {


    public void publish(ArticleEntity articleEntity)
    {

    }

    public PageInfo<ArticleEntity> getArticles(Integer page, Integer limit)
    {
        return null;
    }

    public void updateArticleById(ArticleEntity articleEntity)
    {

    }

    public PageInfo<ArticleEntity> getArticles(String keyword,Integer page,Integer limit)
    {
        return null;
    }

    public void deleteById(Integer id)
    {

    }

    public void updateArticle(ArticleEntity articleEntity)
    {

    }


}
