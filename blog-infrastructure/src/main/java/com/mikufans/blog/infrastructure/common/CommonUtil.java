package com.mikufans.blog.infrastructure.common;

import com.mikufans.blog.infrastructure.repository.user.UserPo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtil {
    public static UserPo getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null)
            return null;
        return (UserPo) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    }
}
