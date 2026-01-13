package com.anla.Order.service;

import com.anla.Order.model.Order;
import com.anla.Order.VO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final CqrsClientService cqrsClient;
    private final RestTemplate restTemplate;
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public List<Object> getAllOrders() {
        return cqrsClient.findAll();
    }
    
    public Object getOrderById(Long id) {
        return cqrsClient.findById(id.toString());
    }
    
    public Order createOrder(Order o) {
        o.setId(idCounter.getAndIncrement());
        o.setOrderDate(LocalDate.now());
        o.setStatus("PENDING");
        cqrsClient.save(o, o.getId().toString());
        return o;
    }
    
    public Order updateOrder(Long id, Order o) {
        o.setId(id);
        cqrsClient.update(o, id.toString());
        return o;
    }
    
    public void deleteOrder(Long id) {
        cqrsClient.delete(id.toString());
    }

    public ResponseTemplateVO getOrderWithDetailById(Long id) {
        Object obj = getOrderById(id);
        if (obj == null) return null;
        
        @SuppressWarnings("unchecked")
        Map<String, Object> dataMap = (Map<String, Object>) obj;
        Order o = new Order();
        o.setId(((Number) dataMap.get("id")).longValue());
        o.setPelangganId(dataMap.get("pelanggan_id") != null ? ((Number) dataMap.get("pelanggan_id")).longValue() : null);
        o.setProdukId(dataMap.get("produk_id") != null ? ((Number) dataMap.get("produk_id")).longValue() : null);
        
        Pelanggan p = null;
        Produk pr = null;
        
        try {
            if (o.getPelangganId() != null) {
                p = restTemplate.getForObject("http://pelanggan/api/pelanggan/" + o.getPelangganId(), Pelanggan.class);
            }
            if (o.getProdukId() != null) {
                pr = restTemplate.getForObject("http://produk/api/produk/" + o.getProdukId(), Produk.class);
            }
        } catch (Exception e) {
            // Service call failed, continue with null values
        }
        
        return new ResponseTemplateVO(o, p, pr);
    }
}