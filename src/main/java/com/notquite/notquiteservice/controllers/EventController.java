package com.notquite.notquiteservice.controllers;


import com.notquite.notquiteservice.exceptions.IllegalDateArgumentException;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.EventDTO;
import com.notquite.notquiteservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {this.eventService = eventService;}

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(){
        return new ResponseEntity<>(this.eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/in-range")
    public ResponseEntity<List<EventDTO>> getAllEventsBetweenDates(@RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<EventDTO> events = this.eventService.getAllEventsBetweenDates(startDate, endDate);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event = this.eventService.getEventById(id);
        if(event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/by_sport")
    public ResponseEntity<List<Event>> getEventsBySport(@RequestParam String sport) {
        List<Event> events = eventService.getEventsBySport(sport);
        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/by_city")
    public ResponseEntity<List<Event>> getEventsByCity(@RequestParam String city) {
        List<Event> events = eventService.getEventsByCity(city);
        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        Event existingEvent = eventService.getEventById(id);

        if (existingEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional.ofNullable(updatedEvent.getTitle()).ifPresent(existingEvent::setTitle);
        Optional.ofNullable(updatedEvent.getState()).ifPresent(existingEvent::setState);
        Optional.ofNullable(updatedEvent.getCity()).ifPresent(existingEvent::setCity);
        Optional.ofNullable(updatedEvent.getZip()).ifPresent(existingEvent::setZip);
        Optional.ofNullable(updatedEvent.getStreet()).ifPresent(existingEvent::setStreet);
        Optional.ofNullable(updatedEvent.getDescription()).ifPresent(existingEvent::setDescription);
        Optional.ofNullable(updatedEvent.getDate()).ifPresent(existingEvent::setDate);
        Optional.ofNullable(updatedEvent.getImage()).ifPresent(existingEvent::setImage);
        Optional.ofNullable(updatedEvent.getCancelled()).ifPresent(existingEvent::setCancelled);
        Optional.ofNullable(updatedEvent.getMaxAttendees()).ifPresent(existingEvent::setMaxAttendees);
        Optional.ofNullable(updatedEvent.getSport()).ifPresent(existingEvent::setSport);

        Event savedEvent = eventService.updateEvent(existingEvent);
        return new ResponseEntity<>(savedEvent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
        Event createdEvent = eventService.createEvent(newEvent);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean isRemoved = eventService.deleteEvent(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/my_events")
    public ResponseEntity<List<Event>> getEventsCreatedBy() {
        List<Event> events = eventService.getEventsCreatedBy();

        if (events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(events);
    }
}


