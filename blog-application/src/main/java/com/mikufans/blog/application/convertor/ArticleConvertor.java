package com.mikufans.blog.application.convertor;

import com.mikufans.blog.domain.aggregate.article.ArticleEntity;
import com.mikufans.blog.infrastructure.repository.content.ArticlePo;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleConvertor {
    public static ArticlePo articleEntity2Po(ArticleEntity articleEntity)
    {
        return null;
    }
    public static ArticleEntity articlePo2Entity(ArticlePo articlePo)
    {
        return null;
    }

    public static List<ArticleEntity> articlePos2Entites(List<ArticlePo> articlePos)
    {
        return articlePos.stream().map(ArticleConvertor::articlePo2Entity)
                .collect(Collectors.toList());
    }
}
