package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * The RegisterController class handles user registration and navigation between registration and login views.
 */
public class RegisterController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    private UserService userService;

    /**
     * Initializes the controller by loading existing users from the file.
     */
    @FXML
    public void initialize() {
        List<ConcreteUser> users = UserDataStorage.loadUsers();
        this.userService = new UserService(users);
    }

    /**
     * Handles the action when the register button is clicked.
     *
     * @param event The ActionEvent triggered by the register button.
     * @throws IOException If an error occurs during the switch to the login view.
     */
    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (username.isBlank() || firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank()) {
            showAlert("All fields must be filled in.");
            return;
        }

        if (userService.isUsernameTaken(username)) {
            showAlert("The username is already taken.");
            return;
        }

        try {
            userService.registerUser(username, firstname, lastname, email, password);

            // Save the updated user data
            UserDataStorage.saveUsers(userService.getUsers());

            switchToLoginView(event);
        } catch (IllegalArgumentException e) {
            showAlert(e.getMessage());
        }
    }

    /**
     * Handles the action when the close button is clicked.
     *
     * @param event The ActionEvent triggered by the close button.
     * @throws IOException If an error occurs during the switch to the login view.
     */
    @FXML
    private void closeButtonClicked(ActionEvent event) throws IOException {
        switchToLoginView(event);
    }

    /**
     * Switches to the login view.
     *
     * @param event The ActionEvent triggered by the switch to login view.
     * @throws IOException If an error occurs during the switch to the login view.
     */
    private void switchToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Shows an alert with the specified message.
     *
     * @param message The content of the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
