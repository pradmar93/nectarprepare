package com.nectar.inventory_service.dto;

import org.springframework.stereotype.Component;

@Component
public class InventoryResponse {

    private String skuCode;
    private Long quantity;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
