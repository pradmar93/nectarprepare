package com.nectar.inventory_service.controller;

import com.nectar.inventory_service.dto.InventoryResponse;
import com.nectar.inventory_service.model.Inventory;
import com.nectar.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{code}")
    public ResponseEntity<Inventory> getInventory(@PathVariable String code) {
        return ResponseEntity.ok(inventoryService.findBySku(code));
    }

    @PutMapping("/reduce")
    public ResponseEntity<String> reduceStock(@RequestParam String code,
                                              @RequestParam int quantity) {
        boolean success = inventoryService.reduceStock(code, quantity);
        return success ?
                ResponseEntity.ok("Stock updated") :
                ResponseEntity.badRequest().body("Insufficient stock");
    }
}
