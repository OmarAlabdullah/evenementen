package evenementen.event.presentation.controller;

import evenementen.event.application.SongService;
import evenementen.event.application.data.SongData;
import evenementen.event.domain.Song;
import evenementen.event.domain.exception.SongAlreadyExistsException;
import evenementen.event.domain.exception.SongNotFoundException;
import evenementen.event.presentation.dto.SongRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping(value = "/song")
    public void saveSong(@RequestBody SongRequest request) {
        try {
            songService.save(request.songId, request.name, request.artist, request.album);
        } catch (SongAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/song/{songId}")
    private SongData getSong(@PathVariable("songId") int songId) {

        try {
            return songService.getSongById(songId);
        } catch (SongNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/songs")
    private List<Song> getSongen() {
        return songService.getAllSongs();
    }

    @DeleteMapping("/song/{songId}")
    private void deleteSong(@PathVariable("songId") int songId) {
        try {
            songService.delete(songId);
        }
        catch (SongNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @PutMapping("/song")
    private void updateSong(@Validated @RequestBody SongRequest request) {
        try {
            songService.update(request.songId, request.name, request.artist, request.album);
        } catch (SongNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
