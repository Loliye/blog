package com.mikufans.blog.infrastructure.repository.option;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OptionPo implements Serializable {
    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置值
     */
    private String value;

    private String description;

    private static final long serialVersionUID = 1L;
}
