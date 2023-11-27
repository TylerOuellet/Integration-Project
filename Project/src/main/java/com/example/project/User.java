package com.example.project;

public abstract class User {
    private String aPassword;

    private String aUsername;

    private Boolean aIsManager;

    void setPassword(String pPassword){
        if (pPassword.length() > 8 && pPassword.length() < 256 ){
            this.aPassword = pPassword;
        } else {
            throw new IllegalArgumentException("Password should be more than 8 characters & less than 256 characters");
        }
    }

    String getPassword(){
        return aPassword;
    }

     void setUsername(String pUsername){
        if (pUsername.length() >= 2 && pUsername.length() < 256){
            this.aUsername = pUsername;
        } else {
            throw new IllegalArgumentException("Username must have at least 2 characters  and less than 256 characters");
        }
    }

    String getUsername(){
        return aUsername;
    }

    void setManagerStatus(Boolean pIsManager){
        if (pIsManager != null ){
            aIsManager = pIsManager;
        } else {
            throw new IllegalArgumentException("Manager status has to be true or false");
        }
    }

    boolean getManagerStatus(){
        return aIsManager;
    }


}
