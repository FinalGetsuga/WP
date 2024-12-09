package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.jpa.ArtistRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class InMemoryArtistRepository {

    List<Artist> artists;

    public InMemoryArtistRepository(ArtistRepository repository) {

        artists = new ArrayList<>();
        if (repository.count() != 0)
            return;

        artists.add(new Artist("The","Weeknd","bio0"));
        artists.add(new Artist("Ed","Sheeran","bio1"));
        artists.add(new Artist("50","Cent","bio2"));
        artists.add(new Artist("Dave","Brubeck","bio3"));
        artists.add(new Artist("Dave","Shone","bio4"));
        repository.saveAll(artists);

    }

    public List<Artist> findALL() {
        return artists;
    }

    public Optional<Artist> findById(Long id){
        return artists.stream().filter(z -> z.getId().equals(id)).findFirst();
    }
}
