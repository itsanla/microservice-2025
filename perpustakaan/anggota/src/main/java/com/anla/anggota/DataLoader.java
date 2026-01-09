package com.anla.anggota;

import com.anla.anggota.model.Anggota;
import com.anla.anggota.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CqrsClientService cqrsClient;

    @Override
    public void run(String... args) {
        Anggota anggota1 = new Anggota();
        anggota1.setId(1L);
        anggota1.setNim("2311083015");
        anggota1.setNama("Anla");
        anggota1.setAlamat("Padang");
        anggota1.setJenisKelamin("Laki-laki");
        anggota1.setEmail("anlaharpanda@gmail.com");
        cqrsClient.save(anggota1, "1");
    }
}