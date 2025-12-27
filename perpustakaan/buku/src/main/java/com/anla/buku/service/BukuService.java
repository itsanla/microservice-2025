package com.anla.buku.service;

import com.anla.buku.model.Buku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Book service for business logic.
 */
@Service
public class BukuService {
    
    /** CQRS client service. */
    @Autowired
    private CqrsClientService cqrsClient;
    
    /** Auto-increment counter. */
    private final AtomicLong idCounter = new AtomicLong(1);
    
    /**
     * Constructor.
     */
    public BukuService() {
        // Default constructor
    }
    
    /**
     * Save book.
     * @param book book to save
     * @return saved book
     */
    public Buku save(final Buku book) {
        book.setBookId(idCounter.getAndIncrement());
        cqrsClient.save(book, book.getBookId().toString());
        return book;
    }
    
    /**
     * Update book.
     * @param book book to update
     * @return updated book
     */
    public Buku update(final Buku book) {
        cqrsClient.update(book, book.getBookId().toString());
        return book;
    }
    
    /**
     * Delete book by ID.
     * @param bookId book identifier
     */
    public void delete(final Long bookId) {
        cqrsClient.delete(bookId.toString());
    }
    
    /**
     * Find book by ID.
     * @param bookId book identifier
     * @return book data
     */
    public Object findById(final Long bookId) {
        return cqrsClient.findById(bookId.toString());
    }
    
    /**
     * Find all books.
     * @return list of books
     */
    public List<Object> findAll() {
        return cqrsClient.findAll();
    }
}