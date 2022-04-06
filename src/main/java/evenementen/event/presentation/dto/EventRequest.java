package evenementen.event.presentation.dto;


import javax.validation.constraints.Min;
import java.sql.Date;

public class EventRequest {

    public String houseNumber;
    public String postalCode;
    public String city;
    public String email;
    public String country;
    public Date date;
    public int persons;
    public String info;
    public String link;
}
