package com.example.project;

import java.io.Serial;
import java.io.Serializable;

/**
 * This User class represents a basic user in the system.
 * It includes functionality for managing the user's password, username, and manager status.
 */
public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String aPassword;

    private String aUsername;

    private String aEmail;

    private Boolean aIsManager;

    /**
     * It sets the password for the user.
     *
     * @param pPassword The password of the user.
     * @throws IllegalArgumentException If the password length is not between 8 and 256.
     */
    void setPassword(String pPassword){
        if (pPassword.length() > 8 && pPassword.length() < 256 ){
            this.aPassword = pPassword;
        } else {
            throw new IllegalArgumentException("Password should be more than 8 characters & less than 256 characters");
        }
    }

    /**
     * It returns the password of the user.
     * @return The password's username.
     */
    String getPassword(){
        return aPassword;
    }

    /**
     * It sets all the manager status for the user.
     *
     * @param pUsername The username of the user.
     * @throws IllegalArgumentException If the user status length is not between 2 and 256.
     */
     void setUsername(String pUsername){
        if (pUsername.length() >= 2 && pUsername.length() < 256){
            this.aUsername = pUsername;
        } else {
            throw new IllegalArgumentException("Username must have at least 2 characters  and less than 256 characters");
        }
    }

    /**
     * It returns the username of the user.
     * @return The user's username.
     */
    String getUsername(){
        return aUsername;
    }

    void setEmail(String pEmail){
        if (pEmail.contains("@") && !pEmail.startsWith("@") && !pEmail.endsWith("@")){
            this.aEmail = pEmail;
        } else {
            throw new IllegalArgumentException("Incorrect email format, use abcdef@ghijkl");
        }
    }

    String getEmail(){
        return aEmail;
    }

    /**
     * It sets the manager status for the user if necessary (TRUE or FALSE).
     *
     * @param pIsManager The manager status to set for the user.
     * @throws IllegalArgumentException If the manager status is null.
     */
    void setManagerStatus(Boolean pIsManager){
        if (pIsManager != null ){
            aIsManager = pIsManager;
        } else {
            throw new IllegalArgumentException("Manager status has to be true or false");
        }
    }

    /**
     * It returns the manager status of the user.
     * @return The manager status of the user (aIsManager).
     */
    boolean getManagerStatus(){
        return aIsManager;
    }

}
