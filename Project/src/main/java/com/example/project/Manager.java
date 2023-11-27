package com.example.project;

/**
 * This Manager class represents when a manager enter into the app.
 * It extends the User class, inheriting some functionalities.
 */
public class
Manager extends User{
    /**
     * constructor for manager, manager status is always true
     * @param pUsername the username input
     * @param pPassword the password input
     */
    public Manager (String pUsername, String pPassword){
        setPassword(pPassword);
        setUsername(pUsername);
        setManagerStatus(true);
    }

}
