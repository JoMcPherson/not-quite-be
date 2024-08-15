package com.notquite.notquiteservice.repositories;

import com.notquite.notquiteservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Correct method to find all events
    List<Event> findAll();
    List<Event> findBySport(String sport);
    List<Event> findByState(String state);
    List<Event> findByCity(String city);
    List<Event> findByDateBetween(LocalDateTime date, LocalDateTime date2);

}
