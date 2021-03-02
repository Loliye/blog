package com.mikufans.blog.infrastructure.repository.attach;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttachPo implements Serializable {
    private Integer id;

    private String fname;

    private String ftype;

    private String fkey;

    private Integer authorId;

    private Integer created;

    private static final long serialVersionUID = 1L;
}
