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

/**
 * The LoginController class handles user authentication and navigation between login and registration views.
 */
public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private UserService userService;

    /**
     * Initializes the controller by loading users from the file and printing user details.
     */
    public void initialize() {
        // Load users from file
        List<ConcreteUser> loadedUsers = UserDataStorage.loadUsers();
        userService = new UserService(loadedUsers);
        userService.printUserDetails();
    }

    /**
     * Handles the action when the register button is clicked.
     *
     * @param event The ActionEvent triggered by the register button.
     */
    @FXML
    void registerButtonClicked(ActionEvent event) {
        openRegisterView();
    }

    /**
     * Handles the action when the login button is clicked.
     *
     * @param event The ActionEvent triggered by the login button.
     */
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

    /**
     * Opens the register view when called.
     */
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

    /**
     * Opens either the manager or user view based on the manager status.
     *
     * @param isManager A boolean indicating whether the user is a manager or not.
     */
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

    /**
     * Shows an alert with the specified title and content.
     *
     * @param title   The title of the alert.
     * @param content The content of the alert.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Closes the current stage.
     *
     * @param event The ActionEvent triggered by the close button.
     */
    @FXML
    void closeButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
