package com.anla.Order.controller;

import com.anla.Order.model.Order;
import com.anla.Order.service.OrderService;
import com.anla.Order.VO.ResponseTemplateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService service;
    
    @GetMapping
    public List<Object> getAllOrders() {
        return service.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public Object getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseTemplateVO getOrderWithDetailById(@PathVariable Long id) {
        return service.getOrderWithDetailById(id);
    }
    
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }
    
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}