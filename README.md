# 📚 Book Library API

A RESTful Book Library API built with **Spring Boot 4**, **Java 21**, and **Spring Data JPA**. 
Supports full CRUD for books, authors, and users, many-to-many book-author relationships, 
borrow & return workflow with history tracking, and search by title/author/category.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Core language |
| Spring Boot 4 | Application framework |
| Spring Data JPA | Database ORM layer |
| Hibernate | JPA implementation |
| H2 Database | In-memory database |
| Lombok | Boilerplate reduction |
| Maven | Dependency management |

---

## 📁 Project Structure
```
src/main/java/com/practice/book_library/
├── Application.java
├── entity/
│   ├── Book.java
│   ├── Author.java
│   ├── User.java
│   └── BorrowRecord.java
├── repository/
│   ├── BookRepository.java
│   ├── AuthorRepository.java
│   ├── UserRepository.java
│   └── BorrowRecordRepository.java
├── service/
│   ├── BookService.java
│   ├── AuthorService.java
│   ├── UserService.java
│   └── BorrowService.java
└── controller/
    ├── BookController.java
    ├── AuthorController.java
    ├── UserController.java
    └── BorrowController.java
```

---

## 🗄️ Database Schema
```
users                books               authors
─────────────────    ────────────────    ──────────────
id (PK)              id (PK)             id (PK)
username             title               name
email                isbn                bio
                     category
                     available
                        │
                        └──── book_authors (join table)
                                  book_id (FK)
                                  author_id (FK)

borrow_records
──────────────────
id (PK)
user_id (FK)
book_id (FK)
borrow_date
return_date
returned
```

---

## 🚀 Getting Started

### Run the app
```bash
git clone https://github.com/your-username/book-library.git
cd book-library
mvn spring-boot:run
```

The server starts at `http://localhost:8080`

### H2 Console (view database in browser)
```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:librarydb
Username: sa
Password: (leave empty)
```

---

## 📡 API Endpoints

### 📖 Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/available` | Get all available books |
| GET | `/api/books/search?title=` | Search by title |
| GET | `/api/books/search?author=` | Search by author name |
| GET | `/api/books/category/{category}` | Get books by category |
| POST | `/api/books` | Create a new book |
| POST | `/api/books/{bookId}/authors/{authorId}` | Link author to book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

### ✍️ Authors

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/authors` | Get all authors |
| GET | `/api/authors/{id}` | Get author by ID |
| GET | `/api/authors/search?name=` | Search by name |
| POST | `/api/authors` | Create a new author |
| PUT | `/api/authors/{id}` | Update an author |
| DELETE | `/api/authors/{id}` | Delete an author |

### 👤 Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update a user |
| DELETE | `/api/users/{id}` | Delete a user |

### 🔄 Borrow & Return

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/borrow?userId=&bookId=` | Borrow a book |
| PUT | `/api/borrow/{recordId}/return` | Return a book |
| GET | `/api/borrow/user/{userId}/history` | Full borrow history |
| GET | `/api/borrow/user/{userId}/active` | Currently borrowed books |

---
