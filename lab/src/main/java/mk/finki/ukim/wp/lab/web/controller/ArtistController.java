package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistsPage(@RequestParam(required = false)String error, Model model,String trackId) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("artists",artistService.listArtists());
        model.addAttribute("trackId",trackId);
        return "artistsList";
    }

    @PostMapping
    public String getSongDetailsPage(@RequestParam Long artistId,@RequestParam String trackId) {
        Artist artist = artistService.findById(artistId);
        Song song = songService.findByTrackId(trackId);
        songService.addArtistToSong(artist,song);
        return "redirect:/songs/details/"+trackId;
    }



}
