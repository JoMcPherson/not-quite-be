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
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/event_attendees")
public class EventAttendeeController {
    private EventAttendeeService eventAttendeeService;

    @Autowired
    public EventAttendeeController(EventAttendeeService eventAttendeeService) {this.eventAttendeeService = eventAttendeeService;}

    @GetMapping("/events/{eventId}")
    public ResponseEntity<List<String>> getAllAttendeesForEvent(@PathVariable Long eventId) {
        try {
            List<String> usernames = eventAttendeeService.getAllAttendeesForEvent(eventId);
            return ResponseEntity.ok(usernames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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

    @GetMapping("/check/{eventId}/user/{cognitoUserId}")
    public ResponseEntity<Boolean> checkIfUserAttending(
            @PathVariable Long eventId,
            @PathVariable String cognitoUserId) {
        Long attendeeId = eventAttendeeService.checkAttending(Optional.of(cognitoUserId), eventId);
        if (attendeeId != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }


}