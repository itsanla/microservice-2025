package com.anla.Pengembalian;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        Pengembalian pengembalian1 = new Pengembalian();
        pengembalian1.setId(1L);
        pengembalian1.setTanggalDikembalikan(LocalDate.now());
        pengembalian1.setTerlambat(0);
        pengembalian1.setDenda(BigDecimal.ZERO);
        pengembalian1.setPeminjamanId(1L);
        cqrsClient.save(pengembalian1, "1");
    }
}
