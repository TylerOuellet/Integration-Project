package com.example.project;

import java.io.*;

/**
 * Represents a concrete implementation of the user.
 */
public class ConcreteUser extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int userId;
    private final String name;

    /**
     * Constructor
     * @param userId Unique identifier for the user.
     * @param name Name for the user.
     * @param username Username for the user.
     * @param password Password for the user.
     * @param email Email for the user.
     * @param isManager Indicating if the user is a manager, or not.
     */
    public ConcreteUser(int userId, String name, String username, String password, String email, boolean isManager) {
        super();
        this.userId = userId;
        this.name = name;
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setManagerStatus(isManager);
    }

    /**
     * Getter of the unique identifier.
     * @return the unique identifier.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter of the name.
     * @return the name.
     */
    public String getName() {
        return name;
    }
}
