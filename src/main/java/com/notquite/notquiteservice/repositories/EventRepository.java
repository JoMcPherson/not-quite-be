package com.notquite.notquiteservice.repositories;

import com.notquite.notquiteservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

@Component
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllEvents();
}
