package com.example.library.service;

import com.example.library.service.model.ItemElement;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    BigDecimal calculatePrice(List<ItemElement> items);
}
