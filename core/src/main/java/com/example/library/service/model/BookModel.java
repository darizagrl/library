package com.example.library.service.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {
    private Long id;
    private String title;
    private BigDecimal price;
    private BigDecimal discount;
    private List<AuthorModel> authorsList;
}