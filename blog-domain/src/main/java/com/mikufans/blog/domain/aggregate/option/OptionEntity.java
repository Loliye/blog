package com.mikufans.blog.domain.aggregate.option;

import lombok.Data;

@Data
public class OptionEntity {
    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;
}
