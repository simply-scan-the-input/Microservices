package com.example.category_managment;

import lombok.Builder;

@Builder
public class AlbumMapper {
    public static AlbumReadDTO toReadDTO(Album album) {
        return AlbumReadDTO.builder()
                .id(album.getId())
                .name(album.getName())
                .build();
    }

    public static AlbumCollectionDTO toCollectionDTO(Album album) {
        return AlbumCollectionDTO.builder()
                .id(album.getId())
                .name(album.getName())
                .build();
    }
    public static AlbumDTO toDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setName(album.getName());
        return dto;
    }

}
