package com.notquite.notquiteservice.controllers;


import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {this.eventService = eventService;}

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return new ResponseEntity<List<Event>>(this.eventService.getAllEvents(), HttpStatus.OK);
    }
}
