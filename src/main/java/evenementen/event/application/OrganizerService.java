package evenementen.event.application;

import evenementen.event.application.data.OrganizerData;
import evenementen.event.data.OrganizerRepository;
import evenementen.event.domain.Event;
import evenementen.event.domain.Organizer;
import evenementen.event.domain.exception.OrganizerNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrganizerService {
    OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }


    public void save(int organizerId, String firstname, String lastname, String password, String email) {


            this.organizerRepository.save(new Organizer(organizerId, firstname, lastname, password, email));


    }


    public void delete(int organizersNumber) {
        this.organizerRepository.deleteById(organizersNumber);
    }

    public List<Organizer> getAllOrganizers() {
        return this.organizerRepository.findAll();
    }


    public void Update( String firstname, String lastname, String password, String email)  {
        Organizer  organizer = this.organizerRepository.findByEmail(email).
                orElseThrow(OrganizerNotFoundException::new);
        if (organizerRepository.existsById(organizer.getOrganizerId())) {
            this.organizerRepository.save(new Organizer(organizer.getOrganizerId(), firstname, lastname, password, email));
//            this.userService.updateUser( email, firstname, lastname, password, email, "ROLE_ORGANIZER");
        } else {
            throw new OrganizerNotFoundException();
        }
    }

    public OrganizerData getByOrganizerId(int organizersId) {
        Organizer organizer = this.organizerRepository.findById(organizersId)
                .orElseThrow(OrganizerNotFoundException::new);

        return new OrganizerData(
                organizersId,
                organizer.getFirstname(),
                organizer.getLastname(),
                organizer.getPassword(),
                organizer.getEmail()
        );

    }

    public Organizer findByEmail(String userName) {
        return  this.organizerRepository.findByEmail(userName).
                orElseThrow(OrganizerNotFoundException::new);
    }

    public List<Event> getMyEvents(String userName) {
         Organizer organizer =   this.organizerRepository.findByEmail(userName).
                orElseThrow(OrganizerNotFoundException::new);
        return organizer.getEvents();
    }
}



