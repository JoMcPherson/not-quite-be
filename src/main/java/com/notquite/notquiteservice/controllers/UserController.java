package com.notquite.notquiteservice.controllers;


import com.amazonaws.services.cognitoidp.model.UserType;
import com.notquite.notquiteservice.services.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/username/{cognitoUserId}")
    public ResponseEntity<String> getUsernameByCognitoUserId(@PathVariable String cognitoUserId) {
        try {
            // Get the map of Cognito user ID to username
            Map<String, String> cognitoIdToUsernameMap = cognitoService.getCognitoIdToUsernameMap();

            // Check if the map contains the given Cognito user ID
            String username = cognitoIdToUsernameMap.get(cognitoUserId);

            if (username != null) {
                // Return the username if found
                return ResponseEntity.ok(username);
            } else {
                // Return a not found response if the user ID does not exist
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving username");
        }
    }
}
