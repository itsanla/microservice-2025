package com.anla.Peminjaman.controller;

import com.anla.Peminjaman.VO.ResponseTemplateVO;
import com.anla.Peminjaman.dto.PeminjamanDto;
import com.anla.Peminjaman.model.Peminjaman;
import com.anla.Peminjaman.service.PeminjamanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/peminjaman")
@RequiredArgsConstructor
public class PeminjamanController {

    private final PeminjamanService service;

    @GetMapping
    public List<Object> getAllPeminjaman() {
        return service.getAllPeminjaman();
    }

    @GetMapping("/{id}")
    public Object getPeminjamanById(@PathVariable Long id) {
        return service.getPeminjamanById(id);
    }

    @GetMapping("/denda/{id}")
    public PeminjamanDto getPeminjamanWithDenda(@PathVariable Long id) {
        return service.getPeminjamanWithDenda(id);
    }

    @PostMapping
    public Peminjaman createPeminjaman(@RequestBody Peminjaman peminjaman) {
        return service.createPeminjaman(peminjaman);
    }

    @PutMapping("/{id}")
    public Peminjaman updatePeminjaman(@PathVariable Long id, @RequestBody Peminjaman peminjaman) {
        return service.updatePeminjaman(id, peminjaman);
    }

    @DeleteMapping("/{id}")
    public void deletePeminjaman(@PathVariable Long id) {
        service.deletePeminjaman(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseTemplateVO getPeminjamanWithDetailById(@PathVariable Long id) {
        return service.getPeminjamanWithDetailById(id);
    }
}
