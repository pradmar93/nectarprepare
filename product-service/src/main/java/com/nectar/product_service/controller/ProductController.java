package com.nectar.product_service.controller;

import com.nectar.product_service.model.Product;
import com.nectar.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    private ResponseEntity<String> createProduct(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.ok("Product created");
    }

}
