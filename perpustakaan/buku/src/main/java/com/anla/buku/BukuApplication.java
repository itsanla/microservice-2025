package com.anla.buku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class for Buku service.
 */
@SpringBootApplication
@EnableDiscoveryClient
public final class BukuApplication {

    private BukuApplication() {
        // Utility class
    }

    /**
     * Main method to start the application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(BukuApplication.class, args);
    }
}
