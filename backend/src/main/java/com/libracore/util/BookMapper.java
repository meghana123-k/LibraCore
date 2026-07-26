package com.libracore.util;

import com.libracore.dto.BookRequestDTO;
import com.libracore.dto.BookResponseDTO;
import com.libracore.entity.Book;

public class BookMapper {

    public static Book toEntity(BookRequestDTO dto) {

        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .availableCopies(dto.getAvailableCopies())
                .build();
    }

    public static BookResponseDTO toResponse(Book book) {

        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .availableCopies(book.getAvailableCopies())
                .build();
    }
}