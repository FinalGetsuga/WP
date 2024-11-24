package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository {
    List<Album> albumList;
    AlbumRepository(){
        albumList = new ArrayList<>();
        albumList.add(new Album("Future Nostalgia","POP","2000"));
        albumList.add(new Album("The Dark Side of the Moon","ROCK","2018"));
        albumList.add(new Album("Kind of Blue","JAZZ","2019"));
        albumList.add(new Album("Master of Puppets","METAL","2020"));
        albumList.add(new Album("To Pimp a Butterfly","HIP-HOP","2021"));
    }

    public List<Album> findAll(){
        return albumList;
    }
}
