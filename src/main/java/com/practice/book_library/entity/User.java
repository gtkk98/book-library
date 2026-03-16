package com.practice.book_library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    // One user can have MANY borrow records
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords;
}
