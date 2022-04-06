package evenementen.event.presentation.controller;

import evenementen.event.application.EventService;
import evenementen.event.domain.Event;
import evenementen.event.domain.exception.EventNotFoundException;
import evenementen.event.presentation.dto.EventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(value = "/events")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    private void saveEvent(@Validated @RequestBody EventRequest event) {
        System.out.println(event.info);
        try {
            eventService.save(event.country, event.city, event.postalCode, event.houseNumber,event.date,event.persons,event.info, event.link ,event.email);
        } catch (EventNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }


    @GetMapping("/event/{eventId}")
    private Event findById(@PathVariable("eventId") int eventId) {
        try {
            return eventService.findEventById(eventId);
        }
        catch (EventNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/event/{username}")
    private List<Event> findByOrganizer(@PathVariable("username") String username) {
        try {
            return eventService.findByOrganizer(username);
        }
        catch (EventNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/events")
    private List<Event> getAllEvents() {
        try {
            return eventService.getAllEvents();

        }
        catch (EventNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }}

    @DeleteMapping("/event/{eventId}")
    private void deleteEvent(@PathVariable("eventId") int eventId) {
        try {
            eventService.deleteEventById(eventId);
        }
        catch (EventNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

//    @PutMapping("/event")
//    private Event updateEvent(@Validated @RequestBody EventRequest event) {
//        try {
//            eventService.save(event.country, event.city, event.postalCode, event.houseNumber,  event.organizer);;
//        } catch (EventNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//        return null;
//    }

}

