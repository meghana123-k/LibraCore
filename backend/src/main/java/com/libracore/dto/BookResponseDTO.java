package com.libracore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer availableCopies;
}