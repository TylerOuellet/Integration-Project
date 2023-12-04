package com.example.project;

import java.io.*;

public class ConcreteUser extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userId;
    private String name;

    public ConcreteUser(int userId, String name, String username, String password, String email, boolean isManager) {
        super();
        this.userId = userId;
        this.name = name;
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setManagerStatus(isManager);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
