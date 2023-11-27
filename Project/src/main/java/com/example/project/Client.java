package com.example.project;

import java.time.LocalDateTime;

/**
 * This class is used to modal a client. it inherits from a general user class.
 */
public class Client extends User {

    /**
     * Attribute to define an ID for the Client
     */
    private String aClientId;

    /**
     * Attribute to define an Email for the client
     */
    private String aEmail;

    /**
     * Attribute to define a signup date for the client
     */
    private LocalDateTime aSignUP;

    /**
     * Setter for email, contains input validation to validate if the input is an email
     * @param pEmail the setter requests an email as a string which it then validates
     */
    void setEmail(String pEmail){
        if (pEmail.contains("@") && !pEmail.startsWith("@") && !pEmail.endsWith("@")){
            this.aEmail = pEmail;
        } else {
            throw new IllegalArgumentException("Incorrect email format, use abcdef@ghijkl");
        }
    }

    /**
     * setter for the signup date, contains validation ensuring that the datetime is not in the future
     * @param pSignUp accepts a local date time as a signup date
     */
    void setSignUp(LocalDateTime pSignUp){
        if (pSignUp.isBefore(LocalDateTime.now()) || pSignUp.isEqual(LocalDateTime.now())){
            this.aSignUP = pSignUp;
        }
    }

    /**
     * setter for the clientID, contains validation to make sure the ID is 8 characters
     * @param pClientID accepts a String of 8 characters as an input
     */
    void setClientID(String pClientID){
        if (pClientID.length() == 8 ){
            this.aClientId = pClientID;
        } else {
            throw new IllegalArgumentException("Client ID must be a 8 Character string");
        }
    }

    /**
     * Simple getter to return email
     * @return the email address as a string.
     */
    String getEmail(){
        return aEmail;
    }

    /**
     * Simple getter to return the signup date
     * @return returns the signup date as a LocalDateTime
     */
    LocalDateTime getSignUp(){
        return aSignUP;
    }

    /**
     * Simple getter to return the client ID
     * @return returns the clientID as a string.
     */
    String getClientID(){
        return aClientId;
    }
}
