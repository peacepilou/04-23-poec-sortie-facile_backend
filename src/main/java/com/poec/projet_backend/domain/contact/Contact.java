package com.poec.projet_backend.domain.contact;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(100)", nullable = false)
    private String title;

    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "isRead", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isRead;

    public Contact(String title, String email, String message, boolean isRead) {
        this.title = title;
        this.email = email;
        this.message = message;
        this.isRead = isRead;
    }
}
