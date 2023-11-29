package com.example.project;

import java.util.List;

public class UserService {
    private List<ConcreteUser> users;

    public UserService(List<ConcreteUser> users) {
        this.users = users;
    }

    public ConcreteUser authenticateUser(String username, String password) {
        for (ConcreteUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
