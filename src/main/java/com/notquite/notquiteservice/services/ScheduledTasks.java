package com.notquite.notquiteservice.services;

import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.EventDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final EventService eventService;

    public ScheduledTasks(EventService eventService) {
        this.eventService = eventService;
    }

    // runs every 12 hours~
    @Scheduled(fixedRate = 43200000)
    public void reportCurrentTimeAndTomorrowsEvents() {
        LocalDateTime today = LocalDateTime.now();
        System.out.println("The time is now: " + today.toString());
        List<EventDTO> events = this.eventService.getAllEventsBetweenDates(today.plusDays(1), today.plusDays(2));
        events.forEach(EventDTO -> System.out.println(EventDTO.getTitle()));
    }

}
