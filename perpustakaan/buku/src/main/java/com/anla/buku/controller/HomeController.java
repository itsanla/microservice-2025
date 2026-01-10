package com.anla.buku.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return """
            Buku Service API - Running
            
            Available Endpoints:
            GET    /api/buku          - Get all books
            GET    /api/buku/{id}     - Get book by ID
            POST   /api/buku          - Create new book
            PUT    /api/buku/{id}     - Update book
            DELETE /api/buku/{id}     - Delete book
            """;
    }
}
