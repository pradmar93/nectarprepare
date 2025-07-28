package com.nectar.inventory_service.service;

import com.nectar.inventory_service.model.Inventory;
import com.nectar.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory findBySku(String code){
       return inventoryRepository.findBySkuCode(code).
               orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public boolean reduceStock(String code, int quantity) {
        Inventory inv = findBySku(code);
        if (inv.getQuantity() >= quantity) {
            inv.setQuantity(inv.getQuantity() - quantity);
            inventoryRepository.save(inv);
            return true;
        }
        return false;
    }

}
