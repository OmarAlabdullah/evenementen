package evenementen.event.application.data;

import evenementen.event.domain.Playlist;

public class SongData {

    private int songId;
    private String name;
    private String artist;
    private String album;
    private Playlist playlist;

    public SongData(int songId, String name, String artist, String album) {
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


