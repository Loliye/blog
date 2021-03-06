package com.mikufans.bloginterface;

import com.mikufans.blog.application.BlogAppStart;
import com.mikufans.blog.infrastructure.common.BlogStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({BlogStart.class, BlogAppStart.class})
public class BlogInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogInterfaceApplication.class, args);
    }

}
