package com.anla.Pengembalian.controller;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.service.PengembalianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pengembalian")
@RequiredArgsConstructor
public class PengembalianController {
    
    private final PengembalianService pengembalianService;
    
    @GetMapping
    public List<Object> getAllPengembalian() {
        return pengembalianService.getAllPengembalian();
    }
    
    @GetMapping("/{id}")
    public Object getPengembalianById(@PathVariable Long id) {
        return pengembalianService.getPengembalianById(id);
    }
    
    @PostMapping
    public Pengembalian createPengembalian(@RequestBody Pengembalian pengembalian) {
        return pengembalianService.createPengembalian(pengembalian);
    }
    
    @PutMapping("/{id}")
    public Pengembalian updatePengembalian(@PathVariable Long id, @RequestBody Pengembalian pengembalian) {
        return pengembalianService.updatePengembalian(id, pengembalian);
    }
    
    @DeleteMapping("/{id}")
    public void deletePengembalian(@PathVariable Long id) {
        pengembalianService.deletePengembalian(id);
    }
}
