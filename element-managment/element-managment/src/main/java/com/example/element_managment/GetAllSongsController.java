package com.example.element_managment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
public class GetAllSongsController{

    private final SongService songService;

    public GetAllSongsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<SongCollectionDTO>> getAllSongs() {
        List<SongCollectionDTO> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }
}
