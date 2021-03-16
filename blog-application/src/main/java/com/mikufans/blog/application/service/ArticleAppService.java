package com.mikufans.blog.application.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mikufans.blog.application.convertor.ArticleConvertor;
import com.mikufans.blog.domain.aggregate.article.ArticleCond;
import com.mikufans.blog.domain.aggregate.article.ArticleEntity;
import com.mikufans.blog.infrastructure.common.*;
import com.mikufans.blog.infrastructure.repository.content.ArticlePo;
import com.mikufans.blog.infrastructure.repository.content.ArticleRepository;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ArticleAppService {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private MetaAppService metaAppService;

    public void publish(ArticleEntity articleEntity)
    {
        if (null == articleEntity) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isBlank(articleEntity.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(articleEntity.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        int titleLength = articleEntity.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        int contentLength = articleEntity.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            throw new TipException("文章内容过长");
        }
        if (null == articleEntity.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        if (StringUtils.isNotBlank(articleEntity.getSlug())) {
            if (articleEntity.getSlug().length() < 5) {
                throw new TipException("路径太短了");
            }
            if (!CommonUtil.isPath(articleEntity.getSlug()))
                throw new TipException("您输入的路径不合法");
//            articleEntity contentVoExample = new articleEntity();
//            contentVoExample.createCriteria().andTypeEqualTo(contents.getType()).andStatusEqualTo(contents.getSlug());
//            long count = contentDao.countByExample(contentVoExample);
//            if (count > 0)
//                throw new TipException("该路径已经存在，请重新输入");
        } else {
            articleEntity.setSlug(null);
        }

        articleEntity.setContent(EmojiParser.parseToAliases(articleEntity.getContent()));

        int time = DateKit.getCurrentUnixTime();
        articleEntity.setCreated(time);
        articleEntity.setModified(time);
        articleEntity.setHits(0);
        articleEntity.setCommentsNum(0);

        String tags = articleEntity.getTags();
        String categories = articleEntity.getCategories();
        articleRepository.createArticle(articleEntity);

        Integer cid = articleEntity.getCid();

        metaAppService.saveMeta(cid, tags, Types.TAG.getType());
        metaAppService.saveMeta(cid, categories, Types.CATEGORY.getType());
    }

    public PageInfo<ArticleEntity> getArticlesByCond(ArticleCond cond,Integer page, Integer limit)
    {
        if(cond==null)
            throw new TipException("参数为空");
        PageHelper.startPage(page, limit);
        List<ArticlePo> articlesByCond = articleRepository.getArticlesByCond(cond);
        List<ArticleEntity> articleEntities = ArticleConvertor.articlePos2Entites(articlesByCond);
        return new PageInfo<>(articleEntities);

    }

    public PageInfo<ArticleEntity> getArticles(Integer page,Integer limit)
    {
        PageInfo<ArticleEntity> articlesByCond = this.getArticlesByCond(new ArticleCond(),page,limit);
        return articlesByCond;
    }

    public void updateArticleById(ArticleEntity articleEntity)
    {
        if(articleEntity==null||articleEntity.getAuthorId()==null)
            throw new TipException("参数为空");
        articleRepository.updateArticleById(ArticleConvertor.articleEntity2Po(articleEntity));
    }

    public PageInfo<ArticleEntity> searchArticles(String keyword,Integer page,Integer limit)
    {
        PageHelper.startPage(page,limit);
        List<ArticlePo> articlePos = articleRepository.searchArticle(keyword);
        List<ArticleEntity> articleEntities = ArticleConvertor.articlePos2Entites(articlePos);
        return new PageInfo<>(articleEntities);
    }

    public void deleteById(Integer id)
    {

    }

    public void updateArticle(ArticleEntity articleEntity)
    {

    }


}
