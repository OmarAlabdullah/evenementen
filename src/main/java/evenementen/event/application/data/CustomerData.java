package evenementen.event.application.data;

import evenementen.event.domain.Event;

import java.util.List;

public class CustomerData {
    private Long customerId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private List<Event> event;


    public CustomerData(Long customerId, String firstname, String lastname, String password, String email) {
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
