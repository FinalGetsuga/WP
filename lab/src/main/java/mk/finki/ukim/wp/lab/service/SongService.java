package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Song findById(Long id);
    void deleteById(Long id);
    void save(String trackId, String title, String genre, String release, Long album);
    void editSong(Long songId, String trackId, String title, String genre, String releaseYear, Long album);
}
