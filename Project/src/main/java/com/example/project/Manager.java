package com.example.project;

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
