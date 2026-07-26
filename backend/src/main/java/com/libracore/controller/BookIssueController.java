package com.libracore.controller;

import com.libracore.dto.BookIssueRequestDTO;
import com.libracore.dto.BookIssueResponseDTO;
import com.libracore.service.BookIssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class BookIssueController {

    private final BookIssueService issueService;

    public BookIssueController(BookIssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookIssueResponseDTO issueBook(
            @Valid @RequestBody BookIssueRequestDTO dto) {

        return issueService.issueBook(dto);
    }

    @PutMapping("/{issueId}/return")
    public BookIssueResponseDTO returnBook(
            @PathVariable Long issueId) {

        return issueService.returnBook(issueId);
    }

    @GetMapping("/active")
    public List<BookIssueResponseDTO> getIssuedBooks() {

        return issueService.getIssuedBooks();
    }

    @GetMapping("/member/{memberId}")
    public List<BookIssueResponseDTO> getMemberHistory(
            @PathVariable Long memberId) {

        return issueService.getMemberHistory(memberId);
    }
}