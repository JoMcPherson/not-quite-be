package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.config.AuditorAwareImpl;
import com.notquite.notquiteservice.exceptions.IllegalDateArgumentException;
import com.notquite.notquiteservice.mapper.EventMapper;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.services.CognitoService;
import com.notquite.notquiteservice.models.dto.EventDTO;
import com.notquite.notquiteservice.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    private final EventMapper eventMapper;

    @Autowired
    private CognitoService cognitoService;

    private final AuditorAwareImpl auditorAware;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper, AuditorAwareImpl auditorAware) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.auditorAware = auditorAware;
    }
    private Optional<String> getCurrentUserId() {
        return auditorAware.getCurrentAuditor();
    }
    public List<EventDTO> getAllEvents() {
        List<Event> events = this.eventRepository.findAll();
        return events.stream().map(eventMapper::toEventDTO).toList();
    }

    public List<EventDTO> getAllEventsBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
        if (endDate.isBefore(startDate)) {
            throw new IllegalDateArgumentException("Your start date must be before your end date.");
        }
        List<Event> events = this.eventRepository.findByDateBetween(startDate, endDate);
        return events.stream().map(eventMapper::toEventDTO).toList();
    }

    public Event getEventById(Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(Event existingEvent) {
        return this.eventRepository.save(existingEvent);
    }

    public Event createEvent(Event newEvent) {
        return this.eventRepository.save(newEvent);
    }

    public List<Event> getEventsBySport(String sport) {
        return this.eventRepository.findBySport(sport);
    }

    public List<Event> getEventsByCity(String city) {
        return this.eventRepository.findByCity(city);
    }

    public boolean deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public String getEventCreatorUsername(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        String creatorCognitoId = event.getCognitoUserId();
        return cognitoService.getUsernameByCognitoUserId(creatorCognitoId);
    }
}
