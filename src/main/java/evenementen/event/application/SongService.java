package evenementen.event.application;

import evenementen.event.application.data.SongData;
import evenementen.event.data.SongRepository;
import evenementen.event.domain.Song;
import evenementen.event.domain.exception.SongAlreadyExistsException;
import evenementen.event.domain.exception.SongNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SongService {
    private SongRepository songRepository;
    private PlaylistService playlistService;

    public SongService(SongRepository songRepository, PlaylistService playlistService) {
        this.songRepository = songRepository;
        this.playlistService = playlistService;
    }

    public void save(int songId, String name, String artist, String album) {

        if (songRepository.existsById(songId)) {
            throw new SongAlreadyExistsException();
        } else {
            this.songRepository.save(new Song(songId, name, artist, album));
        }
    }

    public void update(int songId, String name, String artist, String album) {
        if (!songRepository.existsById(songId)) {
            throw new SongNotFoundException();
        } else {
            this.songRepository.save(new Song(songId, name, artist, album));
        }
    }


    public void delete(int songId) {
        this.songRepository.deleteById(songId);
    }

    public List<Song> getAllSongs() {
        return this.songRepository.findAll();
    }


    public SongData getSongById(int songId) {
        Song song = this.songRepository.findById(songId).
                orElseThrow(() -> new SongNotFoundException());

        return new SongData(song.getSongId(),
                song.getName(),
                song.getArtist(),
                song.getAlbum());
    }
}
