package evenementen.event.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Organizer {
    @Id
    private int organizerId;
    private String firstname;
    private String lastname;

    @OneToMany
    private List<Event> events;

    public Organizer() {
    }

    public Organizer(int organizerId, String firstname, String lastname, String password, String email) {
        this.organizerId = organizerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Event> getEvents() {
        return events;
    }

    private String password;
    private String email;






}
