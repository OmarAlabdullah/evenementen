package evenementen.event.presentation.controller;

import evenementen.event.application.OrganizerService;
import evenementen.event.application.data.OrganizerData;
import evenementen.event.domain.Event;
import evenementen.event.domain.Organizer;
import evenementen.event.domain.exception.OrganizerNotFoundException;
import evenementen.event.domain.exception.OrganizerAlreadyExistsException;
import evenementen.event.presentation.dto.OrganizerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(value = "/events")
public class OrganizerController {
    OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping("/organizer")
    private void saveOrganizer(@Validated @RequestBody OrganizerRequest organizer) {
        try {
            organizerService.save(organizer.organizerId, organizer.firstname, organizer.lastname, organizer.password, organizer.email);
        } catch (OrganizerAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/organizer/{organizersId}")
    private OrganizerData getOrganizer(@PathVariable("organizersId") int organizersId) {

        try {
        return organizerService.getByOrganizerId(organizersId);
        } catch (OrganizerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/organizer/{userName}")
    private Organizer findByUserName(@PathVariable("userName") String userName) {
        System.out.println(userName+"controller");
        return organizerService.findByEmail(userName);
    }

    @PostMapping("/myEvents/{userName}")
    private List<Event> getMyEvents(@PathVariable("userName") String userName) {
        return organizerService.getMyEvents(userName);
    }

    @GetMapping("/organizers")
    private List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @DeleteMapping("/organizer/{organizersId}")
    private void deleteOrganizer(@PathVariable("organizersId") int organizersId) {
        try {
            organizerService.delete(organizersId);
        } catch (OrganizerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/organizer")
    private void updateOrganizer(@Validated @RequestBody OrganizerRequest organizer) {
        try {
            organizerService.Update( organizer.firstname, organizer.lastname, organizer.password, organizer.email);
        } catch (OrganizerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception  e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

