package com.example.library.api.controller;

import com.example.library.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public void addToCart(@RequestParam(name = "id") String productId) {
        cartService.addToCart(productId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/checkout")
    public ResponseEntity<BigDecimal> checkout() {
        BigDecimal totalCost = cartService.calculatePrice();
        return ResponseEntity.ok(totalCost);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @GetMapping("/empty")
    public ResponseEntity<Void> empty() {
        cartService.emptyCart();
        return ResponseEntity.noContent().build();
    }

}
