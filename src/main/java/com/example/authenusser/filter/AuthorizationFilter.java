package com.example.authenusser.filter;


import com.example.authenusser.entity.UserEntity;
import com.example.authenusser.utils.SessionUtil;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.security.core.userdetails.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if(url.startsWith("/quan-tri")){
            SessionUtil.getInstance();
//            UserEntity user = (UserEntity) SessionUtil.getInstance().getValue()
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
