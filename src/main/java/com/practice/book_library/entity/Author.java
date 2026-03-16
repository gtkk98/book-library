package com.practice.book_library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String bio;

    // One author can write MANY books
    // mappedBy = "authors" means: Book.java owns this relationship (has the @JoinTable)
    // CascadeType.ALL = if you delete an author, related operations cascade
    // fetch = LAZY = don't load books unless you explicitly ask for them (performance)
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;
}
