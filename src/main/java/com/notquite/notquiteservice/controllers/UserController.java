package com.notquite.notquiteservice.controllers;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.notquite.notquiteservice.services.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final CognitoService cognitoService;

    @Autowired
    public UserController(CognitoService cognitoService) {
        this.cognitoService = cognitoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserType>> getAllUsers() {
        try {
            List<UserType> users = cognitoService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/username/{cognitoId}")
    public ResponseEntity<String> getUserNameByCognitoId(@PathVariable String cognitoId) {
        try {
            String username = cognitoService.getUsernameByCognitoId(cognitoId);
            return ResponseEntity.ok(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsernames() {
        try {
            List<String> usernames = cognitoService.getAllUsernames();
            return ResponseEntity.ok(usernames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/emails")
    public ResponseEntity<List<String>> getAllEmails() {
        try {
            List<String> emails = cognitoService.getAllEmails();
            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
