package com.anla.pelanggan.service;

import com.anla.pelanggan.model.Pelanggan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PelangganService {

    @Autowired
    private CqrsClientService cqrsClient;

    @SuppressWarnings("unchecked")
    public List<Object> getAllPelanggan() {
        return cqrsClient.findAll();
    }

    public Object getPelangganById(Long id) {
        return cqrsClient.findById(String.valueOf(id));
    }

    public void createPelanggan(Pelanggan pelanggan) {
        cqrsClient.save(pelanggan, String.valueOf(pelanggan.getId()));
    }

    public void updatePelanggan(Long id, Pelanggan pelangganDetails) {
        pelangganDetails.setId(id);
        cqrsClient.update(pelangganDetails, String.valueOf(id));
    }

    public void deletePelanggan(Long id) {
        cqrsClient.delete(String.valueOf(id));
    }
}