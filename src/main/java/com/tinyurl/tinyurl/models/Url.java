package com.tinyurl.tinyurl.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "urls", indexes = {@Index(name = "idx_url_id", columnList = "url_id", unique = true)})
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String urlId;

    @Column(nullable = false)
    private String originalUrl;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
