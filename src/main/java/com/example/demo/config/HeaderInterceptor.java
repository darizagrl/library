package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Resource
public class HeaderInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestContext requestContext;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        requestContext.setJdbcHeaderValue(request.getHeader("repository"));
        return true;
    }
}
