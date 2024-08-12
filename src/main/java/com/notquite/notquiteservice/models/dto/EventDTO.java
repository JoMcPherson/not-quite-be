package com.notquite.notquiteservice.models.dto;

import java.time.LocalDateTime;

public class EventDTO {
    private Integer id;
    private String title;
    private String location;
    private String description;
    private LocalDateTime date;
    private String cognitoUserId;
    private String image;
    private Boolean cancelled;
    private Integer maxAttendees;

    public EventDTO() {
    }

    public EventDTO(Integer id, String title, String location, String description, LocalDateTime date,
            String cognitoUserId,
            String image, Boolean cancelled, Integer maxAttendees) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.date = date;
        this.cognitoUserId = cognitoUserId;
        this.image = image;
        this.cancelled = cancelled;
        this.maxAttendees = maxAttendees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCognitoUserId() {
        return cognitoUserId;
    }

    public void setCognitoUserId(String cognitoUserId) {
        this.cognitoUserId = cognitoUserId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(Integer maxAttendees) {
        this.maxAttendees = maxAttendees;
    }
}
