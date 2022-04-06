package evenementen.security.presentation.controller;

import evenementen.event.application.CustomerService;
import evenementen.event.application.OrganizerService;
import evenementen.security.application.UserService;
import evenementen.security.presentation.dto.Registration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/register")
public class RegistrationController {
    private final UserService userService;
    private CustomerService customerService;
    private OrganizerService organizerService;

    public RegistrationController(UserService userService, CustomerService customerService, OrganizerService organizerService) {
        this.userService = userService;
        this.customerService = customerService;
        this.organizerService = organizerService;
    }

    @PostMapping
    public void register(@Validated @RequestBody Registration registration) throws Exception {

            this.userService.register(
                    registration.username,
                    registration.password,
                    registration.firstname,
                    registration.lastname,
                    registration.email,
                    registration.role
            );
        userService.saveByRole(registration);
            System.out.println(registration.role);



            this.customerService.save(
                    (long) userService.getIdByUsername(registration.username),
                    registration.firstname,
                    registration.lastname,
                    registration.password,
                    registration.email
            );

            this.organizerService.save(
                            userService.getIdByUsername(registration.username),
                            registration.firstname,
                            registration.lastname,
                            registration.password,
                            registration.email)
                    ;

    }

    @PutMapping
    public void update(@Validated @RequestBody Registration registration) throws Exception {

            this.userService.updateUser(
                    registration.username,
                    registration.password,
                    registration.firstname,
                    registration.lastname,
                    registration.email,
                    registration.role
            );
        userService.saveByRole(registration);
            System.out.println(registration.role);



            this.customerService.save(
                    (long) userService.getIdByUsername(registration.email),
                    registration.firstname,
                    registration.lastname,
                    registration.password,
                    registration.email
            );

            this.organizerService.save(
                            userService.getIdByUsername(registration.username),
                            registration.firstname,
                            registration.lastname,
                            registration.password,
                            registration.email)
                    ;

    }
}
