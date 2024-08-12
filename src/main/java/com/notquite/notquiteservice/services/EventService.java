package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }
}
