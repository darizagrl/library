package com.example.library.service.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorModel {
    private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal discount;
    private List<BookModel> booksList;
}
