package evenementen.event.presentation.dto;

import javax.validation.constraints.Min;

public class PlaylistRequest {
    @Min(0)
    public int playlistId;
    public String name;
    public int lengthOfPlayList;

}
