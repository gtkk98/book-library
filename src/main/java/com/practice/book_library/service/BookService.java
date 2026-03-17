package com.practice.book_library.service;

import com.practice.book_library.entity.Author;
import com.practice.book_library.entity.Book;
import com.practice.book_library.repository.AuthorRepository;
import com.practice.book_library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    public List<Book> getCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    // Add an author to a book — managing the Many-to-Many relationship
    public Book addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBookById(bookId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Just add to the list
        book.getAuthors().add(author);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updateBook) {
        Book existing = getBookById(id);
        existing.setTitle(updateBook.getTitle());
        existing.setIsbn(updateBook.getIsbn());
        existing.setCategory(updateBook.getCategory());
        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
