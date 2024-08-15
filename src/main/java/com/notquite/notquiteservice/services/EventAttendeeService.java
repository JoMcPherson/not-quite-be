package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.config.AuditorAwareImpl;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.repositories.EventAttendeeRepository;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventAttendeeService {
    private final EventAttendeeRepository eventAttendeeRepository;
    private final EventRepository eventRepository;

    private final AuditorAwareImpl auditorAware;

    @Autowired
    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, EventRepository eventRepository, AuditorAwareImpl auditorAware) {
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.eventRepository = eventRepository;
        this.auditorAware = auditorAware;
    }

    private Optional<String> getCurrentUserId() {
        return auditorAware.getCurrentAuditor();
    }
    public List<EventAttendee> getAllAttendeesForEvent(Long eventId) {
        return this.eventAttendeeRepository.findByEventId(eventId);
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

    public boolean deleteEventAttendee(Long eventId) {
        // Get the current user's ID
        Optional<String> cognitoUserId = getCurrentUserId();

        // Find the attendee by event ID and current user's ID
        Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findByEventIdAndCognitoUserId(eventId, cognitoUserId);

        if (eventAttendee.isPresent()) {
            // Delete the found attendee
            eventAttendeeRepository.delete(eventAttendee.get());
            return true;
        }
        return false;
    }

    public Long checkAttending(Optional<String> cognitoUserId, Long eventId) {
        Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findByEventIdAndCognitoUserId(eventId, cognitoUserId);
        return eventAttendee.map(EventAttendee::getId).orElse(null);
    }

    public EventAttendee addAttendeeToEvent(Long eventId) {
        EventAttendee newAttendee = new EventAttendee();
        newAttendee.setEventId(eventId);

        // Save the new attendee to the repository
        eventAttendeeRepository.save(newAttendee);
        return newAttendee;
    }
}
