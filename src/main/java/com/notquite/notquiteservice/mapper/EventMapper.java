package com.notquite.notquiteservice.mapper;


import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.CreateEventDTO;
import com.notquite.notquiteservice.models.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface EventMapper {
    @Mapping(source = "events", target = "events")
    EventDTO toEventDTO(Event event);
    Event toEvent(EventDTO eventDTO);
    Event fromCreateEventDTO(CreateEventDTO createEventDTO);
}
