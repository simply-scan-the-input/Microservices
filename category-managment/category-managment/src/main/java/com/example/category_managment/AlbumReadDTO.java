package com.example.category_managment;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class AlbumReadDTO {
    private UUID id;
    private String name;
    private List<String> songTitles; // Lista tytułów piosenek
}
