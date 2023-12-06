package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private UserService userService;

    public void initialize() {
        // Load users from file
        List<ConcreteUser> loadedUsers = UserDataStorage.loadUsers();
        userService = new UserService(loadedUsers);
        userService.printUserDetails();
    }

    @FXML
    void registerButtonClicked(ActionEvent event) {
        openRegisterView();
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            openMainMenuOrMovieSelection(authenticatedUser.getManagerStatus());
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    private void openRegisterView() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();

            stage.setTitle("Register");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void openMainMenuOrMovieSelection(boolean isManager) {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        try {
            if (isManager) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("managerTabView.fxml"));
                Parent root = loader.load();

                stage.setTitle("Manager");
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("movieSelection-view.fxml"));
                Parent root = loader.load();

                stage.setTitle("User");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void closeButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
