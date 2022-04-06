package evenementen.event.application;

import evenementen.event.data.EventRepository;
import evenementen.event.domain.Event;
import evenementen.event.domain.Playlist;
import evenementen.event.domain.exception.EventNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EventService {
    private EventRepository eventRepository;
    private OrganizerService organizerService;


    public EventService(EventRepository eventRepository, OrganizerService organizerService) {
        this.eventRepository = eventRepository;
        this.organizerService= organizerService;
    }

    public void save(String country, String city, String postalCode, String houseNumber, Date date, int persons,String info, String link ,String email) {

        this.eventRepository.save(new Event(country, city, houseNumber, postalCode,date, persons,info, link, organizerService.findByEmail(email)));

    }

    public void update(int eventId, String country,  String city, String postalCode, String houseNumber, Date date, int persons,String info, String link , String email, List<Playlist> playlists) {
        if (eventRepository.existsById(eventId)) {

            this.eventRepository.save(new Event(country, city, houseNumber, postalCode,date, persons,info, link, organizerService.findByEmail(email)));
        } else {
            throw new EventNotFoundException();
        }

    }

    public void deleteEventById(int eventsId) {
        this.eventRepository.deleteById(eventsId);
    }

    public Event findEventById(int eventsId) {
        return this.eventRepository.findById(eventsId).
                orElseThrow(EventNotFoundException::new);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }


    public List<Event> findByOrganizer(String username) {
        List <Event> eventList = new ArrayList<>();
        List<Event> events = this.eventRepository.findAll();
        int id = organizerService.findByEmail(username).getOrganizerId();
        for (Event event : events){
            if (event.getOrganizer().getOrganizerId() == id){
                eventList.add(event);
            }
        }

        return eventList;
    }
}

