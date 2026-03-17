package com.practice.book_library.repository;

import com.practice.book_library.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    // Find all active borrow for a user
    List<BorrowRecord> findByUserIdAndReturnedFalse(Long userId);

    // Full borrow history for user
    List<BorrowRecord> findByUserId(Long userId);

    // Check if a specific book is currently borrowed by anyone
    Optional<BorrowRecord> findByBookIdAndReturnedFalse(Long bookId);
}
