package com.libracore.repository;

import com.libracore.entity.BookIssue;
import com.libracore.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

    Optional<BookIssue> findByBookIdAndMemberIdAndStatus(
            Long bookId,
            Long memberId,
            IssueStatus status);

    List<BookIssue> findByStatus(IssueStatus status);

    List<BookIssue> findByMemberId(Long memberId);
}