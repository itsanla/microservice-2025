package com.anla.Produk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anla.Produk.model.Product;

@Service
public class ProductService {
    @Autowired
    private CqrsClientService cqrsClient;

    @SuppressWarnings("unchecked")
    public List<Object> getAllProducts() {
        return cqrsClient.findAll();
    }

    public Object getProductById(Long id) {
        return cqrsClient.findById(String.valueOf(id));
    }

    public void createProduct(Product product) {
        cqrsClient.save(product, String.valueOf(product.getId()));
    }

    public void deleteProduct(Long id) {
        cqrsClient.delete(String.valueOf(id));
    }

    public void updateProduct(Long id, Product productDetails) {
        productDetails.setId(id);
        cqrsClient.update(productDetails, String.valueOf(id));
    }
}