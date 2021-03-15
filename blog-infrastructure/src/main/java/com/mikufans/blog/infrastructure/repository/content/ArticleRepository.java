package com.mikufans.blog.infrastructure.repository.content;

import com.mikufans.blog.domain.aggregate.article.ArticleCond;
import com.mikufans.blog.domain.aggregate.article.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleRepository {

    int createArticle(ArticleEntity articleEntity);

    ArticlePo getArticleById(@Param("cid") Integer cid);

    int updateArticleById(ArticlePo articlePo);

    int deleteArticleById(@Param("cid") Integer cid);

    Long gerArticleCount();

    List<ArticlePo> getArticlesByCond(ArticleCond articleCond);

    Long getArticleCount();

    List<ArticlePo> getRecentlyArticle();

    List<ArticlePo> searchArticle(@Param("param") String param);

}
