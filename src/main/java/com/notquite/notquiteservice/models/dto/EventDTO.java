package com.notquite.notquiteservice.models.dto;

import java.time.LocalDateTime;

public class EventDTO {
    private Integer id;
    private String title;
    private String state;
    private String city;
    private String zip;
    private String street;
    private String description;
    private LocalDateTime date;
    private String cognitoUserId;
    private String image;
    private Boolean cancelled;
    private Integer maxAttendees;
    private String sport;

    public EventDTO() {
    }

    public EventDTO(Integer id, String title, String state, String city, String zip, String street, String description, LocalDateTime date,
            String cognitoUserId,
            String image, Boolean cancelled, Integer maxAttendees, String sport) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.state = street;
        this.description = description;
        this.date = date;
        this.cognitoUserId = cognitoUserId;
        this.image = image;
        this.cancelled = cancelled;
        this.maxAttendees = maxAttendees;
        this.sport = sport;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
