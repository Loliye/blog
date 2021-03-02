package com.mikufans.bloginterface.http.attach;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/attach")
@Slf4j
public class AttachProviderImpl {

    /**
     * 附件页面
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("")
    public String index(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit)
    {

    }

}
