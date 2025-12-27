package com.anla.Pengembalian;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.repository.PengembalianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final PengembalianRepository pengembalianRepository;

    public DataLoader(PengembalianRepository pengembalianRepository) {
        this.pengembalianRepository = pengembalianRepository;
    }

    @Override
    public void run(String... args) {
        if (pengembalianRepository.count() == 0) {
            
            Pengembalian pengembalian1 = new Pengembalian();
            pengembalian1.setTanggalDikembalikan(LocalDate.now());
            pengembalian1.setTerlambat(0);
            pengembalian1.setDenda(BigDecimal.ZERO);
            pengembalian1.setPeminjamanId(1L);
            pengembalianRepository.save(pengembalian1);
        }
    }
}
