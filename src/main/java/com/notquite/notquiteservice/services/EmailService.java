package com.notquite.notquiteservice.services;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {
    public void sendEmail(String recipient, String subject, String body) {
        Map<String, String> env = System.getenv();
//        for (String envName : env.keySet()) {
//            System.out.format("%s=%s%n",
//                    envName,
//                    env.get(envName));
//        }
        //provide recipient's email ID
        String from = "mailtrap@demomailtrap.com";

        final String username = "api";
        //provide Mailtrap's password
        final String password = env.get("MAILTRAP_PASSWORD");
        //provide Mailtrap's host address
        String host = "live.smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            //set email subject field
            message.setSubject(subject);
            //set the content of the email message
            message.setText(body);
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
