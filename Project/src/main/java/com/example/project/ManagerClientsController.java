package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to view the registered user.
 */
public class ManagerClientsController {

    @FXML
    ListView<String> clientsListView;

    /**
     * Initialize method.
     */
    @FXML
    private void initialize() {
        UserService userService = new UserService(UserDataStorage.loadUsers());
        List<ConcreteUser> users = userService.getUsers();
        List<String> clientNames = new ArrayList<>();
        for (ConcreteUser user : users) {
            if (!user.getManagerStatus()) {
                clientNames.add(user.getName());
            }
        }
        clientsListView.getItems().addAll(clientNames);
    }
}
