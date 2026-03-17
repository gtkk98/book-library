package com.practice.book_library.service;

import com.practice.book_library.entity.Book;
import com.practice.book_library.entity.BorrowRecord;
import com.practice.book_library.entity.User;
import com.practice.book_library.repository.BorrowRecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookService bookService;
    private final UserService userService;

    // @Transactional = if anything fails inside, ALL database changes are rolled back
    // Either everything succeeds together, or nothing changes
    @Transactional
    public BorrowRecord borrowBook(Long id, Long bookId) {
        Book book = bookService.getBookById(bookId);
        User user = userService.getUserById(id);

        if (!book.isAvailable()) {
            throw new RuntimeException("Book " + book.getTitle() + " is not available");
        }

        // Mark book as unavailable
        book.setAvailable(false);
        bookService.updateBook(bookId, book); // save the change

        // Create a borrow book
        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        return borrowRecordRepository.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() ->  new RuntimeException("Record not found"));

        if (record.isReturned()) {
            throw new RuntimeException("Record is already returned");
        }

        // Mark as returned
        record.setReturned(true);
        record.setBorrowDate(LocalDate.now());

        // Make book available again
        Book book = record.getBook();
        book.setAvailable(true);
        bookService.updateBook(book.getId(), book);

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getUserBorrowHistory(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    public List<BorrowRecord> getActiveLoans(Long userId) {
        return borrowRecordRepository.findByUserIdAndReturnedFalse(userId);
    }
}
