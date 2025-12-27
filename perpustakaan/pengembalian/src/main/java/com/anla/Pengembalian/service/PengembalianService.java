package com.anla.Pengembalian.service;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.repository.PengembalianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengembalianService {

    private final PengembalianRepository pengembalianRepository;

    public PengembalianService(PengembalianRepository pengembalianRepository) {
        this.pengembalianRepository = pengembalianRepository;
    }

    public List<Pengembalian> getAllPengembalian() {
        return pengembalianRepository.findAll();
    }

    public Pengembalian getPengembalianById(Long id) {
        return pengembalianRepository.findById(id).orElse(null);
    }

    public Pengembalian createPengembalian(Pengembalian pengembalian) {
        return pengembalianRepository.save(pengembalian);
    }

    public Pengembalian updatePengembalian(Long id, Pengembalian pengembalianDetails) {
        Pengembalian pengembalian = pengembalianRepository.findById(id).orElse(null);
        Pengembalian result = null;
        if (pengembalian != null) {
            pengembalian.setTanggalDikembalikan(pengembalianDetails.getTanggalDikembalikan());
            pengembalian.setTerlambat(pengembalianDetails.getTerlambat());
            pengembalian.setDenda(pengembalianDetails.getDenda());
            pengembalian.setPeminjamanId(pengembalianDetails.getPeminjamanId());
            result = pengembalianRepository.save(pengembalian);
        }
        return result;
    }

    public void deletePengembalian(Long id) {
        pengembalianRepository.deleteById(id);
    }
}
