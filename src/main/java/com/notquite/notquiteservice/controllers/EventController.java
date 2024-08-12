package com.notquite.notquiteservice.controllers;


import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.EventDTO;
import com.notquite.notquiteservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {this.eventService = eventService;}

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(){
        return new ResponseEntity<List<EventDTO>>(this.eventService.getAllEvents(), HttpStatus.OK);
    }
}
