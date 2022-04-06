package evenementen.event.application.data;

import evenementen.event.domain.Event;
import evenementen.event.domain.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaylistData {

    private int playlistId;
    private String name;
    private int lengthOfplayList;
    private Event event;
    private List<Song> songs = new ArrayList<>();


    public PlaylistData(int playlistId, String name, int lengthOfplayList) {
        this.playlistId = playlistId;
        this.name = name;
        this.lengthOfplayList = lengthOfplayList;
        this.event = event;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    public int getLengthOfplayList() {
        return lengthOfplayList;
    }

    public Event getEvent() {
        return event;
    }

    public List<Song> getSongs() {
        return songs;
    }


}
