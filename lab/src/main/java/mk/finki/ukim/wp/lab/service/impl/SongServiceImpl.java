package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.jpa.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }


    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        Song song1 = songRepository.findById(song.getId()).orElseThrow(() -> new RuntimeException("Song does not exist"));
        if (!song1.getPerformers().contains(artist)){
            song1.getPerformers().add(artist);
        }
        songRepository.save(song1);

        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public void save(String trackId, String title, String genre, String releaseYear, Long album) {
        Album newAlbum = albumRepository.findById(album).orElseThrow(() -> new RuntimeException("Album does not exist"));
        Song newSong = new Song(trackId,title,genre,releaseYear, newAlbum);
        this.songRepository.save(newSong);
    }

    @Override
    public void editSong(Long songId, String trackId, String title, String genre, String releaseYear, Long album) {
        Song song = songRepository.findById(songId).orElseThrow( () -> new RuntimeException("Song not found"));

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);

        Album a = albumRepository.findById(album).orElse(null);
        song.setAlbum(a);

        song.setReleaseYear(releaseYear);
        songRepository.save(song);
    }
}
