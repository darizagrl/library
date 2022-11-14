package com.example.library.api.controller;

import com.example.library.service.BookService;
import com.example.library.service.CartService;
import com.example.library.service.model.ItemElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final BookService bookService;

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/checkout")
    public ResponseEntity<BigDecimal> buyBooks(@RequestBody List<Long> ids) {
        List<ItemElement> items = ids.stream().map(bookService::getBook).collect(Collectors.toList());

        BigDecimal totalCost = cartService.calculatePrice(items);
        return ResponseEntity.ok(totalCost);
    }

}
