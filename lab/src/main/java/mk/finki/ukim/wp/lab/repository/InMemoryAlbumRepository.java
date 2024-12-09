package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlbumRepository {

    List<Album> albumList;

    InMemoryAlbumRepository(AlbumRepository repository) {
        albumList = new ArrayList<>();
        if (repository.count() != 0)
            return;

        albumList.add(new Album("Future Nostalgia","POP","2000"));
        albumList.add(new Album("The Dark Side of the Moon","ROCK","2018"));
        albumList.add(new Album("Kind of Blue","JAZZ","2019"));
        albumList.add(new Album("Master of Puppets","METAL","2020"));
        albumList.add(new Album("To Pimp a Butterfly","HIP-HOP","2021"));
        repository.saveAll(albumList);
    }

    public List<Album> findAll(){
        return albumList;
    }
}
