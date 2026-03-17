package com.practice.book_library.repository;

import com.practice.book_library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marks this as a Spring-managed bean
public interface BookRepository extends JpaRepository<Book, Long> {

    // Spring Data JPA reads method names and generates SQL automatically!
    // "findBy" + "Title" + "ContainingIgnoreCase" → WHERE LOWER(title) LIKE %?%
    List<Book> findByTitleContainingIgnoreCase(String title);

    // "findBy" + "Authors" + "Name" + "ContainingIgnoreCase"
    // Spring knows to JOIN the authors table because of your @ManyToMany mapping
    List<Book> findByAuthorsNameContainingIgnoreCase(String authorName);

    List<Book> findByCategory(String category);

    List<Book> findByAvailableTrue(); // WHERE available = TRUE
}
