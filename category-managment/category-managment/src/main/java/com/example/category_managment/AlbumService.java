package com.example.category_managment;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final RestTemplate restTemplate;


    public AlbumService(AlbumRepository albumRepository, RestTemplate restTemplate) {
        this.albumRepository = albumRepository;
        this.restTemplate = restTemplate;
    }

    public Album createAlbum(AlbumCreateUpdateDTO albumDTO) {
        Album album = new Album(UUID.randomUUID(), albumDTO.getName());
        album = albumRepository.save(album);

        // Wysłanie informacji do Element Management
        AlbumDTO albumDTOToSend = AlbumMapper.toDTO(album);
        String url = "http://localhost:8082/api/albums";
        restTemplate.postForObject(url, albumDTOToSend, Void.class);

        return album;
    }

    public List<AlbumCollectionDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(AlbumMapper::toCollectionDTO)
                .toList();
    }

    public Optional<AlbumReadDTO> getAlbumById(UUID id) {
        return albumRepository.findById(id)
                .map(AlbumMapper::toReadDTO);
    }

    public Optional<AlbumReadDTO> updateAlbum(UUID id, AlbumDTO albumDTO) {
        return albumRepository.findById(id)
                .map(existingAlbum -> {
                    existingAlbum.setName(albumDTO.getName());
                    Album updatedAlbum = albumRepository.save(existingAlbum);
                    return AlbumMapper.toReadDTO(updatedAlbum);
                });
    }

    public void deleteAlbum(UUID id) {
        if (!albumRepository.existsById(id)) {
            throw new RuntimeException("Album not found");
        }
        albumRepository.deleteById(id);

        // Wysłanie żądania usunięcia do Element Management
        String url = "http://localhost:8082/api/albums/" + id;
        restTemplate.delete(url);
    }
}
