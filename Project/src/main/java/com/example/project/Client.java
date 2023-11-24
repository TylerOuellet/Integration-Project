package com.example.project;

import java.time.LocalDateTime;

public class Client extends User {

    private String aClientId;

    private String aEmail;

    private LocalDateTime aSignUP;

    void setEmail(String pEmail){
        if (pEmail.contains("@") && !pEmail.startsWith("@") && !pEmail.endsWith("@")){
            this.aEmail = pEmail;
        } else {
            throw new IllegalArgumentException("Incorrect email format, use abcdef@ghijkl");
        }
    }
    void setSignUp(LocalDateTime pSignUp){
        if (pSignUp.isBefore(LocalDateTime.now()) || pSignUp.isEqual(LocalDateTime.now())){
            this.aSignUP = pSignUp;
        }
    }

    void setClientID(String pClientID){
        if (pClientID.length() == 8 ){
            this.aClientId = pClientID;
        } else {
            throw new IllegalArgumentException("Client ID must be a 8 Character string");
        }
    }

    String getEmail(){
        return aEmail;
    }

    LocalDateTime getSignUp(){
        return aSignUP;
    }

    String getClientID(){
        return aClientId;
    }
}
