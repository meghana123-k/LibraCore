package com.libracore.service;

import com.libracore.dto.BookIssueRequestDTO;
import com.libracore.dto.BookIssueResponseDTO;
import com.libracore.entity.*;
import com.libracore.exception.*;
import com.libracore.repository.BookIssueRepository;
import com.libracore.repository.BookRepository;
import com.libracore.repository.MemberRepository;
import com.libracore.util.BookIssueMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookIssueService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookIssueRepository issueRepository;

    public BookIssueService(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            BookIssueRepository issueRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issueRepository = issueRepository;
    }

    @Transactional
    public BookIssueResponseDTO issueBook(BookIssueRequestDTO dto) {

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("Book is unavailable");
        }

        issueRepository.findByBookIdAndMemberIdAndStatus(
                book.getId(),
                member.getId(),
                IssueStatus.ISSUED).ifPresent(issue -> {
                    throw new DuplicateBookIssueException(
                            "Book already issued to this member");
                });

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        BookIssue issue = BookIssue.builder()
                .book(book)
                .member(member)
                .issueDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .status(IssueStatus.ISSUED)
                .build();

        bookRepository.save(book);

        return BookIssueMapper.toResponse(issueRepository.save(issue));
    }

    @Transactional
    public BookIssueResponseDTO returnBook(Long issueId) {

        BookIssue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        if (issue.getStatus() == IssueStatus.RETURNED) {
            throw new InvalidBookReturnException(
                    "Book has already been returned.");
        }

        issue.setStatus(IssueStatus.RETURNED);
        issue.setReturnDate(LocalDate.now());

        Book book = issue.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return BookIssueMapper.toResponse(issueRepository.save(issue));
    }

    public List<BookIssueResponseDTO> getIssuedBooks() {

        return issueRepository.findByStatus(IssueStatus.ISSUED)
                .stream()
                .map(BookIssueMapper::toResponse)
                .toList();
    }

    public List<BookIssueResponseDTO> getMemberHistory(Long memberId) {

        return issueRepository.findByMemberId(memberId)
                .stream()
                .map(BookIssueMapper::toResponse)
                .toList();
    }
}