package com.notquite.notquiteservice;

import com.notquite.notquiteservice.models.dto.EventDTO;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventDTOTests {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Integer id = 1;
        String title = "Event Title";
        String location = "Event Location";
        String description = "Event Description";
        LocalDateTime date = LocalDateTime.of(2023, 8, 10, 12, 0);
        String cognitoUserId = "user-123";
        String image = "image.png";
        Boolean cancelled = false;
        Integer maxAttendees = 100;

        // Act
        EventDTO eventDTO = new EventDTO(id, title, location, description, date, cognitoUserId, image, cancelled, maxAttendees);

        // Assert
        assertEquals(id, eventDTO.getId());
        assertEquals(title, eventDTO.getTitle());
        assertEquals(location, eventDTO.getLocation());
        assertEquals(description, eventDTO.getDescription());
        assertEquals(date, eventDTO.getDate());
        assertEquals(cognitoUserId, eventDTO.getCognitoUserId());
        assertEquals(image, eventDTO.getImage());
        assertEquals(cancelled, eventDTO.getCancelled());
        assertEquals(maxAttendees, eventDTO.getMaxAttendees());
    }

    @Test
    public void testSetters() {
        // Arrange
        EventDTO eventDTO = new EventDTO();
        Integer id = 1;
        String title = "Event Title";
        String location = "Event Location";
        String description = "Event Description";
        LocalDateTime date = LocalDateTime.of(2023, 8, 10, 12, 0);
        String cognitoUserId = "user-123";
        String image = "image.png";
        Boolean cancelled = false;
        Integer maxAttendees = 100;

        // Act
        eventDTO.setId(id);
        eventDTO.setTitle(title);
        eventDTO.setLocation(location);
        eventDTO.setDescription(description);
        eventDTO.setDate(date);
        eventDTO.setCognitoUserId(cognitoUserId);
        eventDTO.setImage(image);
        eventDTO.setCancelled(cancelled);
        eventDTO.setMaxAttendees(maxAttendees);

        // Assert
        assertEquals(id, eventDTO.getId());
        assertEquals(title, eventDTO.getTitle());
        assertEquals(location, eventDTO.getLocation());
        assertEquals(description, eventDTO.getDescription());
        assertEquals(date, eventDTO.getDate());
        assertEquals(cognitoUserId, eventDTO.getCognitoUserId());
        assertEquals(image, eventDTO.getImage());
        assertEquals(cancelled, eventDTO.getCancelled());
        assertEquals(maxAttendees, eventDTO.getMaxAttendees());
    }
}