package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) { this.eventRepository = eventRepository;}

    public List<Event> getAllEvents() { return this.eventRepository.findAll();}
}
