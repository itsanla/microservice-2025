package com.anla.buku.controller;

import com.anla.buku.model.Buku;
import com.anla.buku.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * REST controller for book operations.
 */
@RestController
@RequestMapping("/api/buku")
public final class BukuController {
    
    /** Book service. */
    @Autowired
    private BukuService bukuService;
    
    /**
     * Constructor.
     */
    public BukuController() {
        // Default constructor
    }
    
    /**
     * Get all books.
     * @return list of books
     */
    @GetMapping
    public List<Object> getAllBooks() {
        return bukuService.findAll();
    }
    
    /**
     * Get book by ID.
     * @param bookId book identifier
     * @return book data
     */
    @GetMapping("/{bookId}")
    public Object getBookById(@PathVariable final Long bookId) {
        return bukuService.findById(bookId);
    }
    
    /**
     * Create new book.
     * @param book book to create
     * @return created book
     */
    @PostMapping
    public Buku createBook(@RequestBody final Buku book) {
        return bukuService.save(book);
    }
    
    /**
     * Update existing book.
     * @param bookId book identifier
     * @param book book data
     * @return updated book
     */
    @PutMapping("/{bookId}")
    public Buku updateBook(@PathVariable final Long bookId, @RequestBody final Buku book) {
        Buku updatedBook = new Buku(bookId, book.getJudul(), book.getPengarang(), book.getPenerbit(), book.getTahunTerbit());
        return bukuService.update(updatedBook);
    }
    
    /**
     * Delete book by ID.
     * @param bookId book identifier
     */
    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable final Long bookId) {
        bukuService.delete(bookId);
    }
}