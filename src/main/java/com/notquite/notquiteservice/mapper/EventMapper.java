package com.notquite.notquiteservice.mapper;


import com.notquite.notquiteservice.models.Event;
import com.notquite.notquiteservice.models.dto.CreateEventDTO;
import com.notquite.notquiteservice.models.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "id", target = "id")
    EventDTO toEventDTO(Event event);
    Event toEvent(EventDTO eventDTO);
    Event fromCreateEventDTO(CreateEventDTO createEventDTO);
}
