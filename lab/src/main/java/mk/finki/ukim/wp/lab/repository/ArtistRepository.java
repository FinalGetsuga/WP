package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class ArtistRepository {
    List<Artist> artists;
    public ArtistRepository() {
        artists = new ArrayList<>();
        artists.add(new Artist("The","Weeknd","bio0"));
        artists.add(new Artist("Ed","Sheeran","bio1"));
        artists.add(new Artist("50","Cent","bio2"));
        artists.add(new Artist("Dave","Brubeck","bio3"));
        artists.add(new Artist("Dave","Shone","bio4"));

    }

    public List<Artist> findALL() {
        return artists;
    }

    public Optional<Artist> findById(Long id){
        return artists.stream().filter(z -> z.getId().equals(id)).findFirst();
    }
}
