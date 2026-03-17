package com.practice.book_library.repository;

import com.practice.book_library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingIgnoreCase(String name);

    // Optional = might return null. handled safely
    Optional<Author> findByName(String name);
}
