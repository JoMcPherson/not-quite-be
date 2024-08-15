package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.config.AuditorAwareImpl;
import com.notquite.notquiteservice.mapper.EventMapper;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.EventDTO;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    private final EventMapper eventMapper;

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

    public List<Event> getEventsCreatedBy() {
        Optional<String> cognitoUserId = getCurrentUserId();
        return this.eventRepository.findByCognitoUserId(cognitoUserId);

    }
}
