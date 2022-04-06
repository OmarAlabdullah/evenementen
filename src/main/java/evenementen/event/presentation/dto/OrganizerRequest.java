package evenementen.event.presentation.dto;

import javax.validation.constraints.Min;

public class OrganizerRequest {
    @Min(0)
    public int organizerId;
    public String name;
    public String password;
    public String email;
    public String firstname;
    public String lastname;
}
