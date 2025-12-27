package com.anla.Peminjaman;

import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.repository.PeminjamanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final PeminjamanRepository peminjamanRepository;

    public DataLoader(PeminjamanRepository peminjamanRepository) {
        this.peminjamanRepository = peminjamanRepository;
    }

    @Override
    public void run(String... args) {
        if (peminjamanRepository.count() == 0) {
            
            Peminjaman peminjaman1 = new Peminjaman();
            peminjaman1.setTanggal_pinjam(LocalDate.now());
            peminjaman1.setTanggalDikembalikan(LocalDate.now().plusDays(10));
            peminjaman1.setTanggal_batas(LocalDate.now().plusDays(7));
            peminjaman1.setAnggotaId(1L);
            peminjaman1.setBukuId(1L);
            peminjamanRepository.save(peminjaman1);
        }
    }
}
