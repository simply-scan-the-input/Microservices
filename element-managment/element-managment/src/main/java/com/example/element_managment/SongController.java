package com.example.element_managment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums/{albumId}/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    // Dodawanie nowej piosenki do albumu
    @PostMapping
    public ResponseEntity<SongDTO> createSong(@PathVariable UUID albumId, @RequestBody SongCreateUpdateDTO songDTO) {
        return songService.createSong(albumId, songDTO)
                .map(song -> ResponseEntity.status(HttpStatus.CREATED).body(song))
                .orElse(ResponseEntity.badRequest().build());
    }

    // Pobranie wszystkich piosenek w albumie
    @GetMapping
    public ResponseEntity<List<SongCollectionDTO>> getAllSongsInAlbum(@PathVariable UUID albumId) {
        return songService.getSongsByAlbum(albumId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Pobranie konkretnej piosenki po ID w danym albumie
    @GetMapping("/{songId}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable UUID albumId, @PathVariable UUID songId) {
        return songService.getSongById(songId)
                .filter(song -> song.getAlbumId().equals(albumId)) // Weryfikacja, czy piosenka należy do albumu
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{songId}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable UUID albumId, @PathVariable UUID songId, @RequestBody SongCreateUpdateDTO songDTO) {
        return songService.updateSong(albumId, songId, songDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Usunięcie piosenki z albumu
    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable UUID albumId, @PathVariable UUID songId) {
        boolean deleted = songService.deleteSong(albumId, songId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
