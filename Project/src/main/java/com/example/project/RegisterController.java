package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    @FXML
    public void initialize() {
        List<ConcreteUser> users = UserDataStorage.loadUsers();
        this.userService = new UserService(users);
    }

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (userService.isUsernameTaken(username)) {
            return;
        }

        userService.registerUser(username, firstname, lastname, email, password);

        // Save the updated user data
        UserDataStorage.saveUsers(userService.getUsers());

        switchToLoginView(event);
    }

    @FXML
    private void closeButtonClicked(ActionEvent event) throws IOException {
        switchToLoginView(event);
    }

    private void switchToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Manager");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
