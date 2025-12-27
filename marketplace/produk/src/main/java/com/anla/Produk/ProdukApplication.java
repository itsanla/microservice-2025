package com.anla.Produk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public final class ProdukApplication {

	private ProdukApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdukApplication.class, args);
	}

}
