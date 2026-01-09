package com.anla.Order.service;

import com.anla.Order.model.Order;
import com.anla.Order.VO.Pelanggan;
import com.anla.Order.VO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private CqrsClientService cqrsClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @SuppressWarnings("unchecked")
    public List<Object> getAllOrders() {
        return cqrsClient.findAll();
    }
    
    public Object getOrderById(Long id) {
        return cqrsClient.findById(String.valueOf(id));
    }
    
    public Object getOrderWithDetails(Long id) {
        Object orderData = cqrsClient.findById(String.valueOf(id));
        if (orderData != null) {
            // Fetch pelanggan & produk details via Eureka
            // Implementation depends on your VO structure
        }
        return orderData;
    }
    
    public void createOrder(Order order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");
        cqrsClient.save(order, String.valueOf(order.getId()));
    }
    
    public void updateOrder(Long id, Order orderDetails) {
        orderDetails.setId(id);
        cqrsClient.update(orderDetails, String.valueOf(id));
    }
    
    public void deleteOrder(Long id) {
        cqrsClient.delete(String.valueOf(id));
    }
}