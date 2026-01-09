package com.anla.Order;

import com.anla.Order.model.Order;
import com.anla.Order.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private CqrsClientService cqrsClient;
    
    @Override
    public void run(String... args) {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setPelangganId(1L);
        order1.setProdukId(1L);
        order1.setQuantity(2);
        order1.setTotalHarga(30000000.0);
        order1.setStatus("COMPLETED");
        order1.setOrderDate(LocalDate.now().minusDays(5));
        cqrsClient.save(order1, "1");
        
        Order order2 = new Order();
        order2.setId(2L);
        order2.setPelangganId(2L);
        order2.setProdukId(2L);
        order2.setQuantity(1);
        order2.setTotalHarga(8000000.0);
        order2.setStatus("PENDING");
        order2.setOrderDate(LocalDate.now().minusDays(2));
        cqrsClient.save(order2, "2");
    }
}