package com.example.element_managment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongCreateUpdateDTO {
    private String title;
    private String author;
}
