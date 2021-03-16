package com.mikufans.blog.domain.aggregate.article;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ArticleCond {

    /**
     * 标签
     */
    private String tag;
    /**
     * 类别
     */
    private String category;
    /**
     * 状态
     */
    private String status;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容匹配
     */
    private String content;
    /**
     * 文章类型
     */
    private String type;

    /**
     * 开始时间戳
     */
    private Integer startTime;

    /**
     * 结束时间戳
     */
    private Integer endTime;
}
