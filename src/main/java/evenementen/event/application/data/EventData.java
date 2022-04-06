package evenementen.event.application.data;

import evenementen.event.domain.Customer;
import evenementen.event.domain.Organizer;
import evenementen.event.domain.Playlist;

import java.sql.Date;
import java.util.List;

public class EventData {
    private String country;
    private String city;
    private Date date;
    private int persons;
    private int eventId;

    private String postalCode;
    private String houseNumber;
    private String info;
    private Organizer organizer;
    private List<Playlist> playlists;
    private List<Customer> customers;


    public EventData(int eventId, String country, String city, String postalCode, String houseNumber, Date date, int persons, String info, Organizer organizer) {
        this.eventId = eventId;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.date = date;
        this.persons = persons;
        this.info = info;
        this.organizer = organizer;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public int getPersons() {
        return persons;
    }

    public int getEventId() {
        return eventId;
    }

    public String getInfo() {
        return info;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
