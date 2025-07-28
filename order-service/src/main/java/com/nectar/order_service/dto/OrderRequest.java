package com.nectar.order_service.dto;

import org.springframework.stereotype.Component;

@Component
public class OrderRequest {

    private String productCode;
    private Long quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
