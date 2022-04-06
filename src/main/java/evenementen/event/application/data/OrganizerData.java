package evenementen.event.application.data;

import evenementen.event.domain.Event;

import java.util.List;

public class OrganizerData {
    private int organizerId;
    private String name;
    private String password;
    private String email;

    private List<Event> events;


    public OrganizerData(int organizerId, String firstname, String name, String password, String email) {
        this.organizerId = organizerId;
        this.name = name;
        this.password = password;
        this.email = email;
    }


    public int getOrganizerId() {
        return organizerId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
