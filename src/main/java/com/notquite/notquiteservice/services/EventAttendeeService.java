package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.repositories.EventAttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventAttendeeService {
    private final EventAttendeeRepository eventAttendeeRepository;

//    @Autowired
//    private final EventAttendeeMapper eventAttendeeMapper;

    @Autowired
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

    public void addAttendeeToEvent(Long eventId, String cognitoUserId) {
        // Check if the attendee is already registered for the event
        Optional<EventAttendee> existingAttendee = eventAttendeeRepository.findByEventIdAndCognitoUserId(eventId, cognitoUserId);
        if (existingAttendee.isPresent()) {
            // Attendee already registered, return the existing attendee
//            return existingAttendee.get();
            System.out.println("User already attending");
        }    // Create a new EventAttendee entity
        EventAttendee newAttendee = new EventAttendee();
        newAttendee.setEventId(eventId);
        newAttendee.setCognitoUserId(cognitoUserId.replace("\"", ""));

        // Save the new attendee to the repository
        eventAttendeeRepository.save(newAttendee);
    }
}
