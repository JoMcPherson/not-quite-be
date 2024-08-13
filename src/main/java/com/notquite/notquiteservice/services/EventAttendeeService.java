package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.repositories.EventAttendeeRepository;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventAttendeeService {
    private final EventAttendeeRepository eventAttendeeRepository;
    private final EventRepository eventRepository;

//    @Autowired
//    private final EventAttendeeMapper eventAttendeeMapper;

    @Autowired
    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, EventRepository eventRepository) {
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.eventRepository = eventRepository;
    }

    public List<EventAttendee> getAllAttendeesForEvent(Long id) {

        return this.eventAttendeeRepository.findByEventId(id);
    }

    public List<Event> getAllEventsForAttendee(String cognitoUserId) {
        // Fetch all event attendees for the given user
        List<EventAttendee> eventAttendees = eventAttendeeRepository.findByCognitoUserId(cognitoUserId);

        // Extract event IDs from event attendees
        List<Long> eventIds = eventAttendees.stream()
                .map(EventAttendee::getEventId)  // Assuming EventAttendee has a getEventId() method
                .distinct()  // Optional: Ensures unique event IDs
                .collect(Collectors.toList());

        // Fetch events by their IDs
        return eventRepository.findAllById(eventIds);
    }

    public boolean deleteByEventIdAndCognitoUserId(Long eventId, String cognitoUserId) {
        Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findByEventIdAndCognitoUserId(eventId, cognitoUserId);
        if (eventAttendee.isPresent()) {
            eventAttendeeRepository.delete(eventAttendee.get());
            return true;
        }
        return false;
    }
}
