package com.notquite.notquiteservice.controllers;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.services.EventAttendeeService;
import com.notquite.notquiteservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/event_attendees")
public class EventAttendeeController {
    private EventAttendeeService eventAttendeeService;

    @Autowired
    public EventAttendeeController(EventAttendeeService eventAttendeeService) {this.eventAttendeeService = eventAttendeeService;}

    @GetMapping("/events/{id}")
    public ResponseEntity<List<EventAttendee>> getAllAttendeesForEvent(@PathVariable Long id) {
        List<EventAttendee> eventAttendees = eventAttendeeService.getAllAttendeesForEvent(id);
        if (eventAttendees == null || eventAttendees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventAttendees, HttpStatus.OK);
    }
    @GetMapping("/attended_events")
    public ResponseEntity<List<Event>> getAllEventsForAttendee() {
        List<Event> events = eventAttendeeService.getAllEventsForAttendee();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Void> deleteEventAttendee(@PathVariable Long eventId) {
        boolean isRemoved = eventAttendeeService.deleteEventAttendee(eventId);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<Void> addAttendeeToEvent(
            @PathVariable Long eventId) {
       EventAttendee createdEventAttendee = eventAttendeeService.addAttendeeToEvent(eventId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
