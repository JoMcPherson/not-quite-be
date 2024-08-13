package com.notquite.notquiteservice.repositories;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
    // Find all attendees by event ID
    List<EventAttendee> findByEventId(Long eventId);

    // Find all events a user is attending
    List<Event> findByCognitoUserId(String cognitoUserId);

    // Optional: Delete an attendee from an event
    Optional<EventAttendee> findByEventIdAndCognitoUserId(Long eventId, String cognitoUserId);
}
