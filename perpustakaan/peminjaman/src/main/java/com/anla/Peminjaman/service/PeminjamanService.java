package com.anla.Peminjaman.service;

import com.anla.Peminjaman.dto.PeminjamanDto;
import com.anla.Peminjaman.dto.PeminjamanMessage;
import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.VO.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PeminjamanService {

    private final CqrsClientService cqrsClient;
    private final RestTemplate restTemplate;
    private final PeminjamanProducerService producer;
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Object> getAllPeminjaman() {
        return cqrsClient.findAll();
    }

    public Object getPeminjamanById(Long id) {
        return cqrsClient.findById(id.toString());
    }

    public Peminjaman createPeminjaman(Peminjaman p) {
        p.setId(idCounter.getAndIncrement());
        cqrsClient.save(p, p.getId().toString());
        producer.sendPeminjamanNotification(new PeminjamanMessage(p.getId(), p.getAnggotaId(), p.getBukuId()));
        return p;
    }

    public Peminjaman updatePeminjaman(Long id, Peminjaman p) {
        p.setId(id);
        cqrsClient.update(p, id.toString());
        return p;
    }

    public void deletePeminjaman(Long id) {
        cqrsClient.delete(id.toString());
    }

    public ResponseTemplateVO getPeminjamanWithDetailById(Long id) {
        Object obj = getPeminjamanById(id);
        if (obj == null) return null;
        
        Peminjaman p = obj instanceof Peminjaman ? (Peminjaman) obj : new Peminjaman();
        Buku b = restTemplate.getForObject("http://BUKU-SERVICE/api/buku/" + p.getBukuId(), Buku.class);
        Anggota a = restTemplate.getForObject("http://ANGGOTA-SERVICE/api/anggota/" + p.getAnggotaId(), Anggota.class);
        Pengembalian pg = restTemplate.getForObject("http://PENGEMBALIAN-SERVICE/api/pengembalian/" + id, Pengembalian.class);
        
        if (pg != null && p.getTanggal_batas() != null && pg.getTanggalDikembalikan() != null) {
            p.setTanggalDikembalikan(pg.getTanggalDikembalikan());
            long days = ChronoUnit.DAYS.between(p.getTanggal_batas(), pg.getTanggalDikembalikan());
            if (days > 0) {
                pg.setTerlambat((int) days);
                pg.setDenda(days * 1000.0);
            }
        }
        
        return new ResponseTemplateVO(p, b, a, pg);
    }

    public PeminjamanDto getPeminjamanWithDenda(Long id) {
        Object obj = getPeminjamanById(id);
        return obj == null ? null : new PeminjamanDto(obj instanceof Peminjaman ? (Peminjaman) obj : new Peminjaman());
    }
}