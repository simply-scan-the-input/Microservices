package com.example.category_managment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AlbumCollectionDTO {
    private UUID id;
    private String name; // Tylko podstawowe dane
}
