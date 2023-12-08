package com.example.project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserDataStorage class handles the loading, saving, and deletion of user data.
 */
public class UserDataStorage {
    /** The file name for storing user data. */
    private static final String USER_DATA_FILE = "user_data.ser";

    /**
     * Loads the list of users from the serialized file.
     *
     * @return The list of loaded users, or initializes default users if the file doesn't exist.
     */
    public static List<ConcreteUser> loadUsers() {
        //deleteUsers();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            return (List<ConcreteUser>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File does not exist yet
            return initializeDefaultUsers();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Deletes the user data file.
     */
    public static void deleteUsers() {
        File file = new File(USER_DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Initializes a list of default users and saves them to the user data file.
     *
     * @return The list of initialized default users.
     */
    private static List<ConcreteUser> initializeDefaultUsers() {
        List<ConcreteUser> defaultUsers = new ArrayList<>();
        defaultUsers.add(new ConcreteUser(375902, "Sherry Roselyn", "MSH456", "weifh7832", "sher@gmail.com", true));
        defaultUsers.add(new ConcreteUser(375903, "Kevin Watson", "MKW789", "klsdg9871", "kevi@gmail.com", false));
        defaultUsers.add(new ConcreteUser(375904, "John Smith", "MJS123", "kadh23907", "john@gmail.com", false));

        saveUsers(defaultUsers);
        return defaultUsers;
    }

    /**
     * Saves the list of users to the serialized file.
     *
     * @param users The list of users to save.
     */
    public static void saveUsers(List<ConcreteUser> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
