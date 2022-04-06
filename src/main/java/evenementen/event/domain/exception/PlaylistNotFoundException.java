package evenementen.event.domain.exception;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException() {
        super("Playlist niet gevonden!");
    }
}
