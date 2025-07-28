package com.nectar.order_service.service;

import com.nectar.order_service.dto.InventoryResponse;
import com.nectar.order_service.dto.OrderRequest;
import com.nectar.order_service.model.Order;
import com.nectar.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public String placeOrder(OrderRequest orderRequest){
        String inventoryUrl = "http://localhost:8082/api/inventory/" + orderRequest.getProductCode();
        String reduceStockUrl = "http://localhost:8082/api/inventory/reduce?code=" +
                orderRequest.getProductCode() + "&quantity=" + orderRequest.getQuantity();

        // 1. Get inventory info
        ResponseEntity<InventoryResponse> response =
                restTemplate.getForEntity(inventoryUrl, InventoryResponse.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            return "Product not found in inventory";
        }

        InventoryResponse inventory = response.getBody();

        // 2. Check stock
        if (inventory.getQuantity() < orderRequest.getQuantity()) {
            return "Insufficient stock";
        }

        // 3. Reduce stock
        ResponseEntity<String> reduceResponse =
                restTemplate.exchange(reduceStockUrl, HttpMethod.PUT, null, String.class);

        if (!reduceResponse.getStatusCode().is2xxSuccessful()) {
            return "Failed to update stock";
        }


//        // 3. Reduce stock using request body
//        StockUpdateRequest stockRequest = new StockUpdateRequest(
//                orderRequest.getProductCode(), orderRequest.getQuantity()
//        );
//        HttpEntity<StockUpdateRequest> entity = new HttpEntity<>(stockRequest);
//        ResponseEntity<String> reduceResponse =
//                restTemplate.exchange(reduceStockUrl, HttpMethod.PUT, entity, String.class);
//
//        if (!reduceResponse.getStatusCode().is2xxSuccessful()) {
//            return "Failed to update stock";
//        }

        // 4. Save order
        Order order = new Order();
        order.setSkuCode(orderRequest.getProductCode());
        order.setQuantity(orderRequest.getQuantity());
        orderRepository.save(order);

        return "Order placed successfully";
    }
}
