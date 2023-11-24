package com.example.project;

import java.time.LocalDateTime;

/**
 * Represents a client that extends the user class.
 * Additional information like the client ID, the email, and the signup time.
 */
public class Client extends User {

    /**Hold the client ID*/
    private String aClientId;

    /**Hold the email*/
    private String aEmail;

    /**Hold the time*/
    private LocalDateTime aSignUP;


}
