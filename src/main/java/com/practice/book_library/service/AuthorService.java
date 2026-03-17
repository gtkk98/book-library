package com.practice.book_library.service;

import com.practice.book_library.entity.Author;
import com.practice.book_library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Marks this as a Spring service bean (lives in the Application Context)
@RequiredArgsConstructor // Lombok generates: public AuthorService(AuthorRepository repo) {}
public class AuthorService {

    // final + @RequiredArgsConstructor = Constructor Injection
    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);   // save() = INSERT if new, UPDATE if existing
    }

    public List<Author> getAllAuthors() {

        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        // orElseThrow: if not found, throw an exception instead of returning null
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No author found with id: " + id));
    }

    public List<Author> searchByName(String name) {
        return authorRepository.findByAuthorNameContainingIgnoreCase(name);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existing = getAuthorById(id);  // reuse our own method above
        existing.setName(updatedAuthor.getName());
        existing.setBio(updatedAuthor.getBio());
        return authorRepository.save(existing);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
