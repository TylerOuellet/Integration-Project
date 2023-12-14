package com.example.project;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The UserService class manages user-related operations such as authentication,
 * user registration, and handling user data.
 */
public class UserService {
    /** The list of users managed by the service. */
    private List<ConcreteUser> users;

    /**
     * Constructs a UserService with the provided list of users.
     *
     * @param users The initial list of users.
     */
    public UserService(List<ConcreteUser> users) {
        this.users = new ArrayList<>(users);
    }

    /**
     * Gets the list of users.
     *
     * @return The list of users.
     */
    public List<ConcreteUser> getUsers() {
        return users;
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user, or null if authentication fails.
     */
    public ConcreteUser authenticateUser(String username, String password) {
        for (ConcreteUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Checks if a username is already taken by an existing user.
     *
     * @param username The username to check.
     * @return True if the username is already taken, false otherwise.
     */
    public boolean isUsernameTaken(String username) {
        for (ConcreteUser user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a new user with the specified details.
     *
     * @param username The username of the new user.
     * @param firstname The first name of the new user.
     * @param lastname The last name of the new user.
     * @param email The email of the new user.
     * @param password The password of the new user.
     */
    public void registerUser(String username, String firstname, String lastname, String email, String password) {
        int userId = generateUniqueUserId();

        ConcreteUser newUser = new ConcreteUser(userId, firstname + " " + lastname, username, password, email, false);
        users.add(newUser);
        UserDataStorage.saveUsers(users); // Save the updated list
    }

    /**
     * Generates a unique user ID based on the current number of users.
     *
     * @return The generated unique user ID.
     */
    private int generateUniqueUserId() {
        ConcreteUser lastUser = users.get(users.size() - 1);
        return lastUser.getUserId() + 1;
    }

    /**
     * Prints details of all users to the console.
     */
    public void printUserDetails() {
        for (ConcreteUser user : users) {
            System.out.println("User ID: " + user.getUserId() +
                    ", Name: " + user.getName() +
                    ", Username: " + user.getUsername() +
                    ", Password: " + user.getPassword() +
                    ", Email:" + user.getEmail() +
                    ", Manager: " + user.getManagerStatus() +
                    ", Registration Date: " + user.getRegistrationDateTime());
        }
    }
}
