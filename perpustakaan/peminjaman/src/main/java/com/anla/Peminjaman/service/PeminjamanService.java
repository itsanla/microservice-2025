package com.anla.Peminjaman.service;

import com.anla.Peminjaman.dto.PeminjamanDto;
import com.anla.Peminjaman.dto.PeminjamanMessage;
import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.repository.PeminjamanRepository;
import com.anla.Peminjaman.VO.Anggota;
import com.anla.Peminjaman.VO.Buku;
import com.anla.Peminjaman.VO.ResponseTemplateVO;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import com.anla.Peminjaman.VO.Pengembalian;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final PeminjamanProducerService peminjamanProducerService;

    public PeminjamanService(
            PeminjamanRepository peminjamanRepository,
            DiscoveryClient discoveryClient,
            RestTemplate restTemplate,
            PeminjamanProducerService peminjamanProducerService) {
        this.peminjamanRepository = peminjamanRepository;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.peminjamanProducerService = peminjamanProducerService;
    }

    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    public Peminjaman getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id).orElse(null);
    }

    public Peminjaman createPeminjaman(Peminjaman peminjaman) {
        Peminjaman savedPeminjaman = peminjamanRepository.save(peminjaman);
        // Send notification to RabbitMQ
        PeminjamanMessage message = new PeminjamanMessage(
            savedPeminjaman.getId(),
            savedPeminjaman.getAnggotaId(),
            savedPeminjaman.getBukuId()
        );
        peminjamanProducerService.sendPeminjamanNotification(message);
        return savedPeminjaman;
    }

    public Peminjaman updatePeminjaman(Long id, Peminjaman peminjamanDetails) {
        Peminjaman peminjaman = peminjamanRepository.findById(id).orElse(null);
        Peminjaman result = null;
        if (peminjaman != null) {
            peminjaman.setTanggal_pinjam(peminjamanDetails.getTanggal_pinjam());
            peminjaman.setTanggalDikembalikan(peminjamanDetails.getTanggalDikembalikan());
            peminjaman.setTanggal_batas(peminjamanDetails.getTanggal_batas());
            peminjaman.setAnggotaId(peminjamanDetails.getAnggotaId());
            peminjaman.setBukuId(peminjamanDetails.getBukuId());
            result = peminjamanRepository.save(peminjaman);
        }
        return result;
    }

    public void deletePeminjaman(Long id) {
        peminjamanRepository.deleteById(id);
    }

    public List<ResponseTemplateVO> getPeminjamanWithDetailById(Long id) {
        List<ResponseTemplateVO> responseList = new ArrayList<>();
        Peminjaman peminjaman = getPeminjamanById(id);
        if (peminjaman != null) {
            Buku buku = restTemplate.getForObject("http://BUKU-SERVICE/api/buku/" 
                    + peminjaman.getBukuId(), Buku.class);

            Anggota anggota = restTemplate.getForObject("http://ANGGOTA-SERVICE/api/anggota/" 
                    + peminjaman.getAnggotaId(), Anggota.class);

            Pengembalian pengembalian = restTemplate.getForObject("http://PENGEMBALIAN-SERVICE/api/pengembalian/" 
                    + id, Pengembalian.class);

            processPengembalian(peminjaman, pengembalian);

            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setPeminjaman(peminjaman);
            vo.setBuku(buku);
            vo.setAnggota(anggota);
            vo.setPengembalian(pengembalian);
            
            responseList.add(vo);
        }
        return responseList;
    }

    private void processPengembalian(Peminjaman peminjaman, Pengembalian pengembalian) {
        if (pengembalian == null) {
            return;
        }
        java.time.LocalDate tanggalDikembalikan = pengembalian.getTanggalDikembalikan();
        java.time.LocalDate tanggalBatas = peminjaman.getTanggal_batas();
        if (tanggalDikembalikan == null || tanggalBatas == null) {
            return;
        }
        peminjaman.setTanggalDikembalikan(tanggalDikembalikan);
        long daysDiff = ChronoUnit.DAYS.between(tanggalBatas, tanggalDikembalikan);
        if (daysDiff > 0) {
            pengembalian.setTerlambat((int) daysDiff);
            pengembalian.setDenda(daysDiff * 1000.0);
        } else {
            pengembalian.setTerlambat(0);
            pengembalian.setDenda(0.0);
        }
    }

    public PeminjamanDto getPeminjamanWithDenda(Long id) {
        Peminjaman peminjaman = getPeminjamanById(id);
        PeminjamanDto result = null;
        if (peminjaman != null) {
            result = new PeminjamanDto(peminjaman);
        }
        return result;
    }
}

