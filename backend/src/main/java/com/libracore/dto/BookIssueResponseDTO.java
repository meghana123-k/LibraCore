package com.libracore.dto;

import com.libracore.entity.IssueStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookIssueResponseDTO {

    private Long id;

    private Long bookId;
    private String bookTitle;

    private Long memberId;
    private String memberName;

    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private IssueStatus status;
}