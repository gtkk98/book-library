package com.practice.book_library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
@Data
@NoArgsConstructor
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MANY borrow records belong to ONE user
    // @ManyToOne = the "many" side. This table stores the FK column "user_id"
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Creates a "user_id" column in this table
    private User user;

    // MANY borrow records belong to ONE book
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;     // null means not yet returned

    private boolean returned = false;
}
