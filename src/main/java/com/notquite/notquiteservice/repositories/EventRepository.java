package com.notquite.notquiteservice.repositories;

import com.notquite.notquiteservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Correct method to find all events
    List<Event> findAll();
    List<Event> findBySport(String sport);
    List<Event> findByState(String state);
    List<Event> findByCity(String city);
    List<Event> findByCreatedBy(String cognitoUserId);

}
