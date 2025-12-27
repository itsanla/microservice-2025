package com.anla.Peminjaman.dto;

import com.anla.Peminjaman.model.Peminjaman;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class PeminjamanDto {
    private Peminjaman peminjaman;
    private Double denda;

    public PeminjamanDto(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
        this.denda = calculateDenda();
    }

    private Double calculateDenda() {
        Double result = 0.0;
        java.time.LocalDate tanggalDikembalikan = peminjaman.getTanggalDikembalikan();
        java.time.LocalDate tanggalBatas = peminjaman.getTanggal_batas();
        if (tanggalDikembalikan != null && tanggalBatas != null) {
            long daysDiff = ChronoUnit.DAYS.between(tanggalBatas, tanggalDikembalikan);
            if (daysDiff > 0) {
                result = daysDiff * 1000.0;
            }
        }
        return result;
    }
}
