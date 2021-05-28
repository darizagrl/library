package com.example.library.api.dto;

import com.example.library.persistence.model.AuthorModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AuthorModel> authorsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorModel> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<AuthorModel> authorsList) {
        this.authorsList = authorsList;
    }
}
