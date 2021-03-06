package com.mikufans.bloginterface.http.user;

import com.mikufans.blog.infrastructure.repository.user.UserPo;
import com.mikufans.bloginterface.common.RestResponseDto;
import com.mikufans.bloginterface.common.util.HttpUtil;
import com.mikufans.bloginterface.common.util.IPUtil;
import com.mikufans.bloginterface.http.BaseProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@Slf4j
public class UserProviderImpl extends BaseProvider {
    @GetMapping(value = "/login")
    public String login()
    {
        return "admin/login";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseDto login(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam(required = false) String rememberMe)
    {
        HttpServletRequest request = HttpUtil.getRequest();
        String ip= IPUtil.getIpAddrByRequest(request);
        Integer error_count=cache.hget("login_error_count",ip);
        UserPo
    }

}
