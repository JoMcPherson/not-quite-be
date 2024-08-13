package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.repositories.EventAttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventAttendeeService {
    private final EventAttendeeRepository eventAttendeeRepository;

    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository) {
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    public List<EventAttendee> getAllAttendeesForEvent(Long id) {

        return this.eventAttendeeRepository.findByEventId(id);
    }

    public List<Event> getAllEventsForAttendee(String cognitoId) {

        return this.eventAttendeeRepository.findByCognitoUserId(cognitoId);
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
