package com.example.library.api.dto;

import com.example.library.service.model.BookModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal discount;
    private List<BookModel> booksList;
}
