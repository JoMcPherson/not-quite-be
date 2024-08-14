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
@RequestMapping("/event_attendee")
public class EventAttendeeController {
    private EventAttendeeService eventAttendeeService;

    @Autowired
    public EventAttendeeController(EventAttendeeService eventAttendeeService) {this.eventAttendeeService = eventAttendeeService;}

    @GetMapping("/event/{id}")
    public ResponseEntity<List<EventAttendee>> getAllAttendeesForEvent(@PathVariable Long id) {
        List<EventAttendee> eventAttendees = eventAttendeeService.getAllAttendeesForEvent(id);
        if (eventAttendees == null || eventAttendees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventAttendees, HttpStatus.OK);
    }
    @GetMapping("/user/{cognitoUserId}")
    public ResponseEntity<List<Event>> getAllEventsForAttendee(@PathVariable String cognitoUserId) {
        List<Event> events = eventAttendeeService.getAllEventsForAttendee(cognitoUserId);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/delete_from_event")
    public ResponseEntity<Void> deleteAttendeeFromEvent(
            @RequestParam Long eventId,
            @RequestParam String cognitoUserId
    ) {

        boolean isRemoved = eventAttendeeService.deleteByEventIdAndCognitoUserId(eventId, cognitoUserId);

        if (isRemoved) {
            return ResponseEntity.noContent().build(); // Status 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Status 404 Not Found
        }
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<Void> addAttendeeToEvent(
            @PathVariable Long eventId,
            @RequestBody Map<String, String> requestBody) {
        String cognitoUserId = requestBody.get("cognitoUserId");
        eventAttendeeService.addAttendeeToEvent(eventId, cognitoUserId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
