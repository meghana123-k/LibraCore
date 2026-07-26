package com.libracore.util;

import com.libracore.dto.BookIssueResponseDTO;
import com.libracore.entity.BookIssue;

public class BookIssueMapper {

    public static BookIssueResponseDTO toResponse(BookIssue issue) {

        return BookIssueResponseDTO.builder()
                .id(issue.getId())
                .bookId(issue.getBook().getId())
                .bookTitle(issue.getBook().getTitle())
                .memberId(issue.getMember().getId())
                .memberName(issue.getMember().getName())
                .issueDate(issue.getIssueDate())
                .dueDate(issue.getDueDate())
                .returnDate(issue.getReturnDate())
                .status(issue.getStatus())
                .build();
    }
}