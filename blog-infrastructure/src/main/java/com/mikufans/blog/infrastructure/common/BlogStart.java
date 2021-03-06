package com.mikufans.blog.infrastructure.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
@MapperScan("com.mikufans.blog.infrastructure.repository")
public class BlogStart {
}
