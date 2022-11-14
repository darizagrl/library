package com.example.library.service.model;

import com.example.library.service.ShoppingCartVisitor;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel implements ItemElement {
    private Long id;
    private String title;
    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AuthorModel> authorsList;


    @Override
    public BigDecimal accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}