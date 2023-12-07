package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Test class for bypassing login
 */
public class managerEntryApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoaderManager = new FXMLLoader(com.example.project.MovieApplication.class.getResource("managerTabView.fxml"));
            Parent managerParent = fxmlLoaderManager.load();
            Scene scene = new Scene(managerParent, 600, 515);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }

