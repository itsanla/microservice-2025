package com.anla.Pengembalian.controller;

import com.anla.Pengembalian.model.Pengembalian;
import com.anla.Pengembalian.service.PengembalianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/pengembalian")
@RequiredArgsConstructor
public class PengembalianController {
    
    private final PengembalianService pengembalianService;
    
    @GetMapping
    public Map<String, Object> getAllReturns() {
        log.info("GET /api/pengembalian - Fetching all returns");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "pengembalian");
        response.put("data", pengembalianService.findAll());
        return response;
    }
    
    @GetMapping("/{id}")
    public Object getReturnById(@PathVariable Long id) {
        log.info("GET /api/pengembalian/{} - Fetching return by ID", id);
        return pengembalianService.findById(id);
    }
    
    @PostMapping
    public Pengembalian createReturn(@RequestBody Pengembalian pengembalian) {
        log.info("POST /api/pengembalian - Creating new return for peminjaman ID: {}", pengembalian.getPeminjamanId());
        return pengembalianService.save(pengembalian);
    }
    
    @PutMapping("/{id}")
    public Pengembalian updateReturn(@PathVariable Long id, @RequestBody Pengembalian pengembalian) {
        log.info("PUT /api/pengembalian/{} - Updating return", id);
        pengembalian.setId(id);
        return pengembalianService.update(pengembalian);
    }
    
    @DeleteMapping("/{id}")
    public void deleteReturn(@PathVariable Long id) {
        log.info("DELETE /api/pengembalian/{} - Deleting return", id);
        pengembalianService.delete(id);
    }
}