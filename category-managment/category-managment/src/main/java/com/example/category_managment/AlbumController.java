package com.example.category_managment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // Create Album
    @PostMapping
    public Album createAlbum(@RequestBody AlbumCreateUpdateDTO albumDTO) {
        return albumService.createAlbum(albumDTO);
    }



    // Get all Albums
    @GetMapping
    public List<AlbumCollectionDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    // Get Album by ID
    @GetMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> getAlbumById(@PathVariable UUID id) {
        return albumService.getAlbumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Album
    @PutMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> updateAlbum(@PathVariable UUID id, @RequestBody AlbumDTO albumDTO) {
        return albumService.updateAlbum(id, albumDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable UUID id) {
        albumService.deleteAlbum(id);
    }
}