package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository {
    List<Song> songs;
    private final AlbumRepository albumRepository;
    List<Album> albumList = new ArrayList<>();
    public SongRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
        albumList = albumRepository.findAll();
        songs = new ArrayList<>();
        songs.add(new Song("0","Blinding Lights","Pop","2000",albumList.get(0)));
        songs.add(new Song("1","Bohemian Rhapsody","Rock","2001",albumList.get(1)));
        songs.add(new Song("2","Shape of You","Pop","2003",albumList.get(2)));
        songs.add(new Song("3","Take Five","Jazz","2005",albumList.get(3)));
        songs.add(new Song("4","Lose Yourself","Hip-Hop","1990",albumList.get(4)));
    }



    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId){
        return songs.stream().filter(z -> z.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Song findById(Long id){
        return songs.stream().filter(z -> z.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteById(Long id){
        songs.removeIf(z -> z.getId().equals(id));
    }

    public void save(String trackId, String title, String genre, String releaseYear, Long album){
        Album a = albumRepository.findAll().stream().filter(z -> z.getId().equals(album)).findFirst().orElse(null);
        songs.add(new Song(trackId,title,genre,releaseYear,a));
    }

    public void editSong(Long songId, String trackId, String title, String genre, String releaseYear, Long album){
        Song song = findById(songId);
        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumRepository.findAll().stream().filter(z -> z.getId().equals(album)).findFirst().orElse(null));
    }

    public Artist addArtistToSong(Artist artist, Song song){
        song.getPerformers().add(artist);
        return artist;
    }
}
