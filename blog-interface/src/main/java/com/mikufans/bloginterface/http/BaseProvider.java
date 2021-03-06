package com.mikufans.bloginterface.http;

import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.MapCache;
import com.mikufans.blog.infrastructure.repository.user.UserPo;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseProvider {
    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();

    /**
     * 主页的页面主题
     * @param viewName
     * @return
     */
    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseProvider title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseProvider keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public UserPo user(HttpServletRequest request) {
        return CommonUtil.getLoginUser(request);
    }

    public Integer getUid(HttpServletRequest request){
        return this.user(request).getUid();
    }

    public String render_404() {
        return "comm/error_404";
    }
}
