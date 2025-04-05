package com.example.element_managment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository,SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbumAndSongs(UUID albumId) {
        songRepository.deleteByAlbumId(albumId);
        albumRepository.deleteById(albumId);
    }
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(album -> new AlbumDTO(album.getId(), album.getName()))
                .toList();
    }
}
