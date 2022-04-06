package evenementen.security.application;

import evenementen.event.application.CustomerService;
import evenementen.event.application.OrganizerService;
import evenementen.security.data.UserRepository;
import evenementen.security.domain.User;
import evenementen.security.presentation.dto.Registration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private CustomerService customerService;
    private OrganizerService organizerService;
    private EmailSenderService emailSenderService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomerService customerService, OrganizerService organizerService,EmailSenderService emailSenderServic) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
        this.organizerService = organizerService;
        this.emailSenderService = emailSenderServic;
    }



    public void register(String username, String password, String firstname, String  lastname, String email, String role) throws Exception {
        String encodedPassword = this.passwordEncoder.encode(password);
            User user = new User(username, encodedPassword, firstname,  lastname, email, role);

            this.userRepository.save(user);

    }

    private boolean existsByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user == null;
    }

    public void updateUser(String username, String password, String firstname, String  lastname, String email, String role)  {
        String encodedPassword = this.passwordEncoder.encode(password);

        if (!existsByUsername(username)) {
            User user = new User(getIdByUsername(username),username, encodedPassword, firstname,  lastname, email, role);

            this.userRepository.save(user);
        } else {
            System.out.println("user bestaat niet");;
        }
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public int getIdByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return user.getId();
    }

    public void saveByRole(Registration registration) {
        if (registration.role.equals("ROLE_CUSTOMER")) {

            this.customerService.save(
                    (long) getIdByUsername(registration.username),
                    registration.firstname,
                    registration.lastname,
                    registration.password,
                    registration.email
            );
            System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
        }
        else if (registration.role.equals("ROLE_ORGANIZER")){
            System.out.println("----------------------------------------------------");
           this.organizerService.save(
                   getIdByUsername(registration.username),
                            registration.firstname,
                            registration.lastname,
                            registration.password,
                            registration.email);}

        emailSenderService.sendSimpleEmail(Collections.singletonList(registration.email),
                "Geachte "+registration.lastname+", \n\nBedankt dat je onze website gebrukt.\nveel plazier!\n\nMet hartelijke groet,\nService team Musiqay", "registreren");

    }




}

