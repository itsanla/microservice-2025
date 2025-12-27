package com.anla.buku;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Test class for BukuApplication.
 */
@SpringBootTest
final class BukuApplicationTests {

    /**
     * Constructor.
     */
    BukuApplicationTests() {
        // Default constructor
    }

    /**
     * Test context loads.
     */
    @Test
    void contextLoads() {
        // Context loads successfully
        assertDoesNotThrow(() -> {
            // Context loading test
        });
    }

}