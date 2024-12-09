package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.repository.InMemoryAlbumRepository;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
