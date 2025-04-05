package com.example.element_managment;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, AlbumRepository albumRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.songMapper = songMapper;
    }

    // Tworzenie piosenki
    public Optional<SongDTO> createSong(UUID albumId, SongCreateUpdateDTO songDTO) {
        // Sprawdź, czy album istnieje
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found!"));

        // Tworzenie nowej piosenki
        Song song = new Song(UUID.randomUUID(), songDTO.getTitle(), songDTO.getAuthor(), albumId);
        Song savedSong = songRepository.save(song);

        return Optional.of(songMapper.toDTO(savedSong));
    }

    // Pobranie wszystkich piosenek
    public List<SongCollectionDTO> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::toCollectionDTO)
                .toList();
    }

    // Pobranie piosenek przypisanych do albumu
    public Optional<List<SongCollectionDTO>> getSongsByAlbum(UUID albumId) {
        if (!albumRepository.existsById(albumId)) {
            return Optional.empty();
        }

        return Optional.of(
                songRepository.findByAlbumId(albumId)
                        .stream()
                        .map(songMapper::toCollectionDTO)
                        .collect(Collectors.toList())
        );
    }

    // Pobranie piosenki po ID
    public Optional<SongDTO> getSongById(UUID id) {
        return songRepository.findById(id)
                .map(songMapper::toDTO);
    }

    // Aktualizacja piosenki
    public Optional<SongDTO> updateSong(UUID albumId, UUID songId, SongCreateUpdateDTO songDTO) {
        return songRepository.findById(songId)
                .filter(song -> song.getAlbumId().equals(albumId)) // Sprawdzenie, czy piosenka należy do albumu
                .map(song -> {
                    song.setTitle(songDTO.getTitle());
                    song.setAuthor(songDTO.getAuthor());
                    Song updatedSong = songRepository.save(song);
                    return songMapper.toDTO(updatedSong);
                });
    }

    // Usunięcie piosenki z albumu
    public boolean deleteSong(UUID albumId, UUID songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isPresent() && song.get().getAlbumId().equals(albumId)) {
            songRepository.deleteById(songId);
            return true;
        }
        return false;
    }

}
