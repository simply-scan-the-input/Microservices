package com.example.element_managment;

import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    // Konwersja z Song na SongDTO
    public SongDTO toDTO(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .author(song.getAuthor())
                .albumId(song.getAlbumId()) // ID albumu
                .build();
    }

    public SongCollectionDTO toCollectionDTO(Song song) {
        return SongCollectionDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .albumId(song.getAlbumId()) // Użycie UUID
                .build();
    }

    public SongCreateUpdateDTO toCreateUpdateDTO(Song song) {
        return SongCreateUpdateDTO.builder()
                .title(song.getTitle())
                .author(song.getAuthor())
                .build();
    }
}
