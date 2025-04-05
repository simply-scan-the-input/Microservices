package com.example.element_managment;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "album_id", nullable = false)
    private UUID albumId; // Powiązanie z albumem przez UUID

    public Song(UUID id, String title, String author, UUID albumId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.albumId = albumId;
    }
}
