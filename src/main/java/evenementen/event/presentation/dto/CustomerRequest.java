package evenementen.event.presentation.dto;

import javax.validation.constraints.Min;

public class CustomerRequest {
    @Min(0)
    public Long customerId;
    public String firstname;
    public String lastname;
    public String password;
    public String email;

}
