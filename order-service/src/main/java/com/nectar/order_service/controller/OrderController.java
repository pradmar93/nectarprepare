package com.nectar.order_service.controller;

import com.nectar.order_service.dto.OrderRequest;
import com.nectar.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {
        String result = orderService.placeOrder(request);
        return ResponseEntity.ok(result);
    }
}
