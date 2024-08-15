package com.notquite.notquiteservice.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


public class CreateEventDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String location;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDateTime date;


    private String image;

    private Boolean cancelled;

    @Min(value = 1)
    @NotBlank
    private Integer maxAttendees;
}
