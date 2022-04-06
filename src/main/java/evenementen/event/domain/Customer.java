package evenementen.event.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    private Long customerId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;

    @ManyToMany(targetEntity = Event.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_customer",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> event;

    public Customer() {
    }

    public Customer(Long customerId, String firstname, String lastname, String password, String email) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public List<Event> getEvent() {
        return event;
    }
}
