package com.mikufans.blog.infrastructure.repository.relationship;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelationshipPo implements Serializable {
    /**
     * 内容主键
     */
    private Integer cid;

    /**
     * 项目主键
     */
    private Integer mid;

    private static final long serialVersionUID = 1L;
}
