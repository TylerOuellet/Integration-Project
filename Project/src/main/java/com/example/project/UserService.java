package com.example.project;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class UserService {
    private List<ConcreteUser> users;

    public UserService(List<ConcreteUser> users) {
        this.users = new ArrayList<>(users);
    }

    public List<ConcreteUser> getUsers() {
        return users;
    }

    public ConcreteUser authenticateUser(String username, String password) {
        for (ConcreteUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUsernameTaken(String username) {
        for (ConcreteUser user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void registerUser(String username, String firstname, String lastname, String email, String password) {
        int userId = generateUniqueUserId();

        ConcreteUser newUser = new ConcreteUser(userId, firstname + " " + lastname, username, password, email, false);
        users.add(newUser);
        UserDataStorage.saveUsers(users); // Save the updated list
    }

    private int generateUniqueUserId() {
        ConcreteUser lastUser = users.get(users.size() - 1);
        return lastUser.getUserId() + 1;
    }

    public void printUserDetails() {
        for (ConcreteUser user : users) {
            System.out.println("User ID: " + user.getUserId() +
                    ", Name: " + user.getName() +
                    ", Username: " + user.getUsername() +
                    ", Password: " + user.getPassword() +
                    ", Email:" + user.getEmail() +
                    ", Manager: " + user.getManagerStatus());
        }
    }
}
