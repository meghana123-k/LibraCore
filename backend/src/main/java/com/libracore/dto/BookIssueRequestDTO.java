package com.libracore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookIssueRequestDTO {

    @NotNull
    private Long bookId;

    @NotNull
    private Long memberId;
}