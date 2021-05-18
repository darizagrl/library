package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestContext {
    private String jdbcHeaderValue;

    public String getJdbcHeaderValue() {
        return jdbcHeaderValue;
    }

    public void setJdbcHeaderValue(String jdbcHeaderValue) {
        this.jdbcHeaderValue = jdbcHeaderValue;
    }
}
