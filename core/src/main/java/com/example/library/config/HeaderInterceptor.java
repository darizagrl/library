package com.example.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Resource
@RequiredArgsConstructor
public class HeaderInterceptor implements HandlerInterceptor {
    private final RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        requestContext.setJdbcHeaderValue(request.getHeader("repository"));
        return true;
    }
}
