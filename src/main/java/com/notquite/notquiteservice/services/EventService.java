package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.mapper.EventMapper;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.EventDTO;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = this.eventRepository.findAll();
        return events.stream().map(eventMapper::toEventDTO).toList();
    }
}
