package com.anla.Produk.controller;

import com.anla.Produk.model.Product;
import com.anla.Produk.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/produk")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping
    public Map<String, Object> getAllProducts() {
        log.info("GET /api/produk - Fetching all products");
        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("serviceName", "produk");
        response.put("data", productService.findAll());
        return response;
    }
    
    @GetMapping("/{id}")
    public Object getProductById(@PathVariable Long id) {
        log.info("GET /api/produk/{} - Fetching product by ID", id);
        return productService.findById(id);
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("POST /api/produk - Creating new product: {}", product.getNama());
        return productService.save(product);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        log.info("PUT /api/produk/{} - Updating product", id);
        product.setId(id);
        return productService.update(product);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/produk/{} - Deleting product", id);
        productService.delete(id);
    }
}