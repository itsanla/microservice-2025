package com.anla.pelanggan.controller;

import com.anla.pelanggan.model.Pelanggan;
import com.anla.pelanggan.service.PelangganService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
@RequiredArgsConstructor
public class PelangganController {

    private final PelangganService pelangganService;

    @GetMapping
    public List<Object> getAllPelanggan() {
        return pelangganService.findAll();
    }

    @GetMapping("/{id}")
    public Object getPelangganById(@PathVariable Long id) {
        return pelangganService.findById(id);
    }

    @PostMapping
    public Pelanggan createPelanggan(@RequestBody Pelanggan pelanggan) {
        return pelangganService.save(pelanggan);
    }

    @PutMapping("/{id}")
    public Pelanggan updatePelanggan(@PathVariable Long id, @RequestBody Pelanggan pelanggan) {
        pelanggan.setId(id);
        return pelangganService.update(pelanggan);
    }

    @DeleteMapping("/{id}")
    public void deletePelanggan(@PathVariable Long id) {
        pelangganService.delete(id);
    }
}