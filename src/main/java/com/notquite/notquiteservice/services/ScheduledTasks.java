package com.notquite.notquiteservice.services;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.notquite.notquiteservice.models.EventAttendee;
import com.notquite.notquiteservice.models.dto.EventDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private final EventService eventService;
    private final CognitoService cognitoService;
    private final EventAttendeeService eventAttendeeService;
    private final EmailService emailService;

    public ScheduledTasks(EventService eventService, CognitoService cognitoService,
            EventAttendeeService eventAttendeeService, EmailService emailService) {
        this.eventService = eventService;
        this.cognitoService = cognitoService;
        this.eventAttendeeService = eventAttendeeService;
        this.emailService = emailService;
    }

    // runs every day at 6AM
    @Scheduled(cron = "0 30 6 * * *")
    public void reportCurrentTimeAndTomorrowsEvents() {
        LocalDateTime today = LocalDateTime.now();

        List<EventDTO> events = this.eventService.getAllEventsBetweenDates(today, today.plusDays(1));

        List<UserType> allUsers = cognitoService.getAllUsers();

        events.forEach(event -> {
                    Long eventId = Long.valueOf(event.getId());

                    List<EventAttendee> attendees = eventAttendeeService.getAllEventAttendeesByEventId(eventId);

                    attendees.forEach(eventAttendee -> {
                        allUsers.forEach(user -> {
                            if(eventAttendee.getCognitoUserId().equals(user.getAttributes().get(3).getValue())){
                                String email = (user.getAttributes().get(0).getValue());
                                String name = (user.getUsername());
                                String eventName = event.getTitle();
                                String sport = event.getSport();
                                String subject = "Don't forget that the ".concat(eventName).concat(" is happening " +
                                        "today!");
                                String emailBody =
                                        "Hey " + name + " ,get ready to have fun doing " + sport + "!";
                                emailService.sendEmail(email, subject, emailBody);
                            }
                        });
                    });
                }
        );
    }
}
