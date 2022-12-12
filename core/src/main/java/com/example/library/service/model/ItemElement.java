package com.example.library.service.model;

import com.example.library.service.visitor.ShoppingCartVisitor;

import java.math.BigDecimal;

public interface ItemElement {

    BigDecimal accept(ShoppingCartVisitor visitor);
}
