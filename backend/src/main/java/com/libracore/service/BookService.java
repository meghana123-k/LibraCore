package com.libracore.service;

import com.libracore.dto.BookRequestDTO;
import com.libracore.dto.BookResponseDTO;
import com.libracore.entity.Book;
import com.libracore.exception.BookNotFoundException;
import com.libracore.exception.DuplicateResourceException;
import com.libracore.repository.BookRepository;
import com.libracore.util.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    public BookResponseDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        return BookMapper.toResponse(book);
    }

    public BookResponseDTO addBook(BookRequestDTO dto) {

        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN already exists.");
        }

        Book savedBook = bookRepository.save(BookMapper.toEntity(dto));

        return BookMapper.toResponse(savedBook);
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        if (!book.getIsbn().equals(dto.getIsbn())
                && bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN already exists.");
        }

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailableCopies(dto.getAvailableCopies());

        Book updatedBook = bookRepository.save(book);

        return BookMapper.toResponse(updatedBook);
    }

    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        bookRepository.delete(book);
    }
}