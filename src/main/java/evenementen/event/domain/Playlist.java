package evenementen.event.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {
    @Id
    private int playlistId;
    private String name;
    private int lengthOfPlayList;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "playlist")
    private List<Song> songs = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(int playlistId, String name, int lengthOfplayList) {
        this.playlistId = playlistId;
        this.name = name;
        this.lengthOfPlayList = lengthOfplayList;
//        this.event = event;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    public int getLengthOfPlayList() {
        return lengthOfPlayList;
    }

    public Event getEvent() {
        return event;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
