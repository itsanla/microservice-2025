package com.anla.buku;

import com.anla.buku.model.Buku;
import com.anla.buku.service.CqrsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data loader for initial book data.
 */
@Component
public final class DataLoader implements CommandLineRunner {

    /** CQRS client service. */
    @Autowired
    private CqrsClientService cqrsClient;
    
    /**
     * Constructor.
     */
    public DataLoader() {
        // Default constructor
    }

    @Override
    public void run(final String... args) throws Exception {
        // Load sample data
        final Buku book1 = new Buku(1L, "Java Programming", "John Doe", "Politeknik Negeri Padang", 2023);
        
        cqrsClient.save(book1, "1");
        
        // Sample data loaded message would go to logger in production
    }
}