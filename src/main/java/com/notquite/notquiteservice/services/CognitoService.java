package com.notquite.notquiteservice.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CognitoService {

    private final AWSCognitoIdentityProvider cognitoClient;

    // Inject the AWS access key and secret key from the application.properties
    public CognitoService(
            @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey) {
        // Create AWS credentials using the provided access key and secret key
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretKey);

        // Build the Cognito client with the credentials and region
        this.cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion("us-east-2")
                .build();
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String paginationToken = null;

        do {
            ListUsersRequest request = new ListUsersRequest()
                    .withUserPoolId("us-east-2_Zb5Kje5Vs")
                    .withPaginationToken(paginationToken);

            ListUsersResult result = cognitoClient.listUsers(request);

            for (UserType user : result.getUsers()) {
                usernames.add(user.getUsername());
            }

            paginationToken = result.getPaginationToken();
        } while (paginationToken != null);

        return usernames;
    }

    public List<UserType> getAllUsers() {
        List<UserType> users = new ArrayList<>();
        String paginationToken = null;

        do {
            ListUsersRequest request = new ListUsersRequest()
                    .withUserPoolId("us-east-2_Zb5Kje5Vs")
                    .withPaginationToken(paginationToken);

            ListUsersResult result = cognitoClient.listUsers(request);

            for (UserType user : result.getUsers()) {
                users.add(user);
            }

            paginationToken = result.getPaginationToken();
        } while (paginationToken != null);

        return users;
    }

    public List<String> getAllEmails() {
        List<String> emails = new ArrayList<>();
        String paginationToken = null;

        do {
            ListUsersRequest request = new ListUsersRequest()
                    .withUserPoolId("us-east-2_Zb5Kje5Vs")
                    .withPaginationToken(paginationToken);

            ListUsersResult result = cognitoClient.listUsers(request);

            for (UserType user : result.getUsers()) {
                // Fetch email attribute from user attributes
                for (AttributeType attribute : user.getAttributes()) {
                    if ("email".equals(attribute.getName())) {
                        emails.add(attribute.getValue());
                        break; // Stop iterating through attributes if email is found
                    }
                }
            }

            paginationToken = result.getPaginationToken();
        } while (paginationToken != null);

        return emails;
    }

    public Map<String, String> getCognitoIdToUsernameMap() {
        List<UserType> allUsers = getAllUsers();
        Map<String, String> cognitoIdToUsernameMap = new HashMap<>();

        for (UserType user : allUsers) {
            String cognitoUserId = user.getAttributes().stream()
                    .filter(attr -> "sub".equals(attr.getName())) // "sub" is the attribute name for cognitoUserId
                    .map(AttributeType::getValue)
                    .findFirst()
                    .orElse(null);

            if (cognitoUserId != null) {
                cognitoIdToUsernameMap.put(cognitoUserId, user.getUsername());
            }
        }

        return cognitoIdToUsernameMap;
    }

    public String getUsernameByCognitoUserId(String cognitoUserId) {
        List<UserType> allUsers = getAllUsers();
        for (UserType user : allUsers) {
            for (AttributeType attribute : user.getAttributes()) {
                if ("sub".equals(attribute.getName()) && cognitoUserId.equals(attribute.getValue())) {
                    return user.getUsername();
                }
            }
        }
        return null; // or throw an exception if you want to handle the case where the user is not found
    }
}