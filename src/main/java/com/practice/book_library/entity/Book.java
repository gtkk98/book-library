package com.practice.book_library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String isbn;

    private String category;

    private boolean available = true;

    // MANY books can have MANY authors (Many-to-Many)
    // @JoinTable defines the JOIN TABLE that links books and authors
    // This join table is called "book_authors" with two foreign key columns
    @ManyToMany
    @JoinTable(
            name = "book_authors", // Name of the join table
            joinColumns = @JoinColumn(name = "book_id"), // FK pointing to Book
            inverseJoinColumns = @JoinColumn(name = "author_id") // FK pointing to Author
    )
    private List<Author> authors;

    // One book can have MANY borrow records (history of who borrowed it)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords;
}
