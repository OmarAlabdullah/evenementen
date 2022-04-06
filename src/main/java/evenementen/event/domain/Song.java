package evenementen.event.domain;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue
    private int songId;
    private String name;
    private String artist;
    private String album;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;


    public Song() {

    }

    public Song(int songId, String name, String artist, String album) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    public int getSongId() {
        return songId;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }
}
