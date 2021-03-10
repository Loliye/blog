package com.mikufans.bloginterface.http.article;

import com.mikufans.blog.application.service.LogAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/article")
@Slf4j
public class ArticleProviderImpl {
    @Resource
    private LogAppService logAppService;

}
