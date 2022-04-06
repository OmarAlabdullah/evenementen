package evenementen.event.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private int eventId;
    private String country;
    private String city;
    private String postalCode;
    private String houseNumber;
    private Date date;
    private int persons;

    public String getLink() {
        return link;
    }

    private String info;
    private String link;

    @ManyToOne
    @JoinColumn(name = "organizerId")
    @Convert(converter = Organizer.class)
    private Organizer organizer;

    @OneToMany(mappedBy = "event")
    private List<Playlist> playlists;

    @ManyToMany(mappedBy = "event")
    private List<Customer> customers;

    public Event( String country, String city, String postalCode, String houseNumber, Date date, int persons,String info, String link ,Organizer organizer) {

        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.date = date;
        this.persons = persons;
        this.info = info;
        this.link = link;
        this.organizer = organizer;

    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Date getDate() {
        return date;
    }

    public int getPersons() {
        return persons;
    }

    public String getInfo() {
        return info;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

//    public List<Playlist> getPlaylists() {
//        return playlists;
//    }
//
//    public List<Customer> getCustomers() {
//        return customers;
//    }
}
