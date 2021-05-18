package com.example.demo.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
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