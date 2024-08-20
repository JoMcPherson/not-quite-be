package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.config.AuditorAwareImpl;
import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.repositories.EventAttendeeRepository;
import com.notquite.notquiteservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventAttendeeService {
    private final EventAttendeeRepository eventAttendeeRepository;
    private final EventRepository eventRepository;

    private final AuditorAwareImpl auditorAware;
    AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
            .withRegion("us-east-2") // Replace with your region
            .build();


    @Autowired
    private CognitoService cognitoService;

    @Autowired
    public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, EventRepository eventRepository, AuditorAwareImpl auditorAware) {
        this.eventAttendeeRepository = eventAttendeeRepository;
        this.eventRepository = eventRepository;
        this.auditorAware = auditorAware;
    }

    private Optional<String> getCurrentUserId() {
        return auditorAware.getCurrentAuditor();
    }

    public List<Event> getAllEventsForAttendee() {
        Optional<String> cognitoUserId = getCurrentUserId();
        List<EventAttendee> eventAttendees = eventAttendeeRepository.findByCognitoUserId(cognitoUserId);

        // Extract event IDs from event attendees
        List<Long> eventIds = eventAttendees.stream()
                .map(EventAttendee::getEventId)  // Assuming EventAttendee has a getEventId() method
                .distinct()  // Optional: Ensures unique event IDs
                .collect(Collectors.toList());

        // Fetch events by their IDs
        return eventRepository.findAllById(eventIds);
    }

    public List<String> getAllAttendeesForEvent(Long eventId) {
        // Fetch all EventAttendee records for the event
        List<EventAttendee> eventAttendees = eventAttendeeRepository.findByEventId(eventId);

        // Extract the cognitoUserId from each EventAttendee
        List<String> cognitoUserIds = eventAttendees.stream()
                .map(EventAttendee::getCognitoUserId)
                .collect(Collectors.toList());

        // Get the map of cognitoUserId to username
        Map<String, String> cognitoIdToUsernameMap = cognitoService.getCognitoIdToUsernameMap();

        // Filter and collect usernames based on the cognitoUserIds from the eventAttendees
        return cognitoUserIds.stream()
                .map(cognitoIdToUsernameMap::get)
                .filter(username -> username != null)
                .collect(Collectors.toList());
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