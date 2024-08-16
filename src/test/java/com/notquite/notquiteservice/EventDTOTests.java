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
        String state = "Event State";
        String city = "Event City";
        String zip = "Event Zip";
        String street = "Event Street";
        String description = "Event Description";
        LocalDateTime date = LocalDateTime.of(2023, 8, 10, 12, 0);
        String image = "image.png";
        Boolean cancelled = false;
        Integer maxAttendees = 100;
        String sport = "test";
        String cognitoUserId = "614b05b0-9051-706d-1c16-081daeb1d357";

        // Act
        EventDTO eventDTO = new EventDTO(id, title, state, city, zip, street, description, date, image, cancelled,
                maxAttendees, sport, cognitoUserId);

        // Assert
        assertEquals(id, eventDTO.getId());
        assertEquals(title, eventDTO.getTitle());
        assertEquals(state, eventDTO.getState());
        assertEquals(city, eventDTO.getCity());
        assertEquals(zip, eventDTO.getZip());
        assertEquals(street, eventDTO.getStreet());
        assertEquals(description, eventDTO.getDescription());
        assertEquals(date, eventDTO.getDate());
        assertEquals(image, eventDTO.getImage());
        assertEquals(cancelled, eventDTO.getCancelled());
        assertEquals(maxAttendees, eventDTO.getMaxAttendees());
        assertEquals(sport, eventDTO.getSport());
        assertEquals(cognitoUserId, eventDTO.getCognitoUserId());
    }

    @Test
    public void testSetters() {
        // Arrange
        EventDTO eventDTO = new EventDTO();
        Integer id = 1;
        String title = "Event Title";
        String state = "Event State";
        String city = "Event City";
        String zip = "Event Zip";
        String street = "Event Street";
        String description = "Event Description";
        LocalDateTime date = LocalDateTime.of(2023, 8, 10, 12, 0);
        String cognitoUserId = "user-123";
        String image = "image.png";
        Boolean cancelled = false;
        Integer maxAttendees = 100;
        String sport = "test";

        // Act
        eventDTO.setId(id);
        eventDTO.setTitle(title);
        eventDTO.setState(state);
        eventDTO.setCity(city);
        eventDTO.setZip(zip);
        eventDTO.setStreet(street);
        eventDTO.setDescription(description);
        eventDTO.setDate(date);
        eventDTO.setImage(image);
        eventDTO.setCancelled(cancelled);
        eventDTO.setMaxAttendees(maxAttendees);
        eventDTO.setSport(sport);

        // Assert
        assertEquals(id, eventDTO.getId());
        assertEquals(title, eventDTO.getTitle());
        assertEquals(state, eventDTO.getState());
        assertEquals(city, eventDTO.getCity());
        assertEquals(zip, eventDTO.getZip());
        assertEquals(street, eventDTO.getStreet());
        assertEquals(description, eventDTO.getDescription());
        assertEquals(date, eventDTO.getDate());
        assertEquals(image, eventDTO.getImage());
        assertEquals(cancelled, eventDTO.getCancelled());
        assertEquals(maxAttendees, eventDTO.getMaxAttendees());
        assertEquals(sport, eventDTO.getSport());

    }
}