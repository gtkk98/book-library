package com.practice.book_library.controller;

import com.practice.book_library.entity.BorrowRecord;
import com.practice.book_library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    // POST /api/borrow?userId=1&bookId=2
    @PostMapping
    public ResponseEntity<BorrowRecord> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(borrowService.borrowBook(userId, bookId));
    }

    // PUT /api/borrow/5/return
    @PostMapping("/{recordId}/return")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long recordId) {
        return ResponseEntity.ok(borrowService.returnBook(recordId));
    }

    // GET /api/borrow/user/1/history
    @GetMapping("/user/{userId}/history")
    public ResponseEntity<List<BorrowRecord>> getBorrowHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getUserBorrowHistory(userId));
    }

    // GET /api/borrow/user/1/active
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<BorrowRecord>> getActivLoans(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getActiveLoans(userId));
    }
}
