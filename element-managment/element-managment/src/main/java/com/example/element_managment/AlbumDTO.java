package com.example.element_managment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {
    private UUID id;      // Identyfikator albumu
    private String name;  // Nazwa albumu
}
