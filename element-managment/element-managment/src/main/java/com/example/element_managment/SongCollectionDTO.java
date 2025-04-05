package com.example.element_managment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SongCollectionDTO {
    private UUID id;
    private String title; // Tylko podstawowe dane
    private UUID albumId;
}
