package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false)String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("songs",this.songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    public String addSong(Model model) {
        List<Album> albumList = this.albumService.findAll();
        model.addAttribute("albums", albumList);
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam String releaseYear,
            @RequestParam String album){
        this.songService.save(trackId,title,genre,releaseYear,Long.parseLong(album));
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        Song song = this.songService.findById(songId);
        List<Album> albumList = this.albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("albums", albumList);
        return "edit-song";
    }

    @PostMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam String releaseYear,
                           @RequestParam String album){
        songService.editSong(songId, trackId, title, genre, releaseYear, Long.parseLong(album));
        return "redirect:/songs";
    }


}
