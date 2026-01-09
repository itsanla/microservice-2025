package com.anla.Peminjaman;

import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.service.CqrsClientService;
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
        Peminjaman peminjaman1 = new Peminjaman();
        peminjaman1.setId(1L);
        peminjaman1.setTanggal_pinjam(LocalDate.now());
        peminjaman1.setTanggalDikembalikan(LocalDate.now().plusDays(10));
        peminjaman1.setTanggal_batas(LocalDate.now().plusDays(7));
        peminjaman1.setAnggotaId(1L);
        peminjaman1.setBukuId(1L);
        cqrsClient.save(peminjaman1, "1");
    }
}
