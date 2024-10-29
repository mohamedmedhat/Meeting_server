package com.example.demo.cart;

import org.springframework.http.ResponseEntity;

public interface ICartService {
    Cart createCart();
    ResponseEntity<Void> deleteCart(String id);
    Cart updateCart(String id);

}
