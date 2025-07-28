package com.nectar.product_service.service;

import com.nectar.product_service.model.Product;
import com.nectar.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }


    public void save(Product product) {
        productRepository.save(product);
    }

}
