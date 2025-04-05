package com.example.element_managment;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class SongDTO implements Comparable<SongDTO> {
    private UUID id;
    private String title;
    private String author;
    private UUID albumId;
    private String albumName;

    // Implementacja metody compareTo, sortowanie po tytule książki
    @Override
    public int compareTo(SongDTO other) {
        return this.title.compareTo(other.title); // Naturalne sortowanie po tytule
    }

    @Override
    public String toString() {
        return "SongDTO{id=" + id + ", title='" + title + "', author='" + author + "', album='" + albumId + "'}";
    }
}
