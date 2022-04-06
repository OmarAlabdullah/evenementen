package evenementen.event.presentation.controller;


import evenementen.event.application.PlaylistService;
import evenementen.event.application.data.PlaylistData;
import evenementen.event.domain.Playlist;
import evenementen.event.domain.exception.PlaylistAlreadyExistsException;
import evenementen.event.domain.exception.PlaylistNotFoundException;
import evenementen.event.presentation.dto.PlaylistRequest;
import evenementen.event.domain.SpotifyUserAuthorizationCode;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class PlaylistController {

    private PlaylistService playlistService;
    private SpotifyUserAuthorizationCode spotifyUserAuthorizationCode;

    public PlaylistController(PlaylistService playlistService, SpotifyUserAuthorizationCode spotifyUserAuthorizationCode) {
        this.playlistService = playlistService;
        this.spotifyUserAuthorizationCode = spotifyUserAuthorizationCode;
    }

    @PostMapping("/playlist")
    private void savePlaylist(@Validated @RequestBody PlaylistRequest playlist) {
        try {
            playlistService.save(playlist.playlistId, playlist.name, playlist.lengthOfPlayList);
        } catch (PlaylistAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/playlist/{playlistId}")
    private PlaylistData getPlaylist(@PathVariable("playlistId") int playlistId) {

        try {
            playlistService.findByPlaylistId(playlistId);
        } catch (PlaylistNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return playlistService.findByPlaylistId(playlistId);
    }

    @GetMapping("/playlists")
    private List<Playlist> getPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @DeleteMapping("/playlist/{playlistId}")
    private void deletePlaylist(@PathVariable("playlistId") int playlistId) {
        try {
            playlistService.delete(playlistId);
        } catch (PlaylistNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/playlist")
    private void update(@Validated @RequestBody PlaylistRequest playlist) {
        try {
            playlistService.save(playlist.playlistId, playlist.name, playlist.lengthOfPlayList);
        } catch (PlaylistNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }



    @GetMapping("/playlists/{playlist_id}")
    private JSONObject getPlaylistItems(@PathVariable("playlist_id") String playlistId) throws Exception {

            JSONObject response = new JSONObject();
            if (spotifyUserAuthorizationCode.getAccessToken() == null || spotifyUserAuthorizationCode.getAccessToken().isEmpty()) {
                response.put("Error", "UserAccessToken not fetched yet");
                return response;
            }
        return playlistService.getPlaylistItems(spotifyUserAuthorizationCode.getTokenType() + " " + spotifyUserAuthorizationCode.getAccessToken(), playlistId);


    }
    @GetMapping("/playlistt/{playlist_id}")
    private void findByPlaylist(@PathVariable("playlist_id") String playlistId) throws Exception {

            playlistService.findByplaylist(playlistId);

    }

}
