package com.example.project;

public class ConcreteUser extends User {
    private int userId;
    private String name;

    public ConcreteUser(int userId, String name, String username, String password, boolean isManager) {
        super();
        this.userId = userId;
        this.name = name;
        setUsername(username);
        setPassword(password);
        setManagerStatus(isManager);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
