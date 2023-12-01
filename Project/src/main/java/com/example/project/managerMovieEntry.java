package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class managerMovieEntry extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(com.example.project.MovieApplication.class.getResource("managerMovies-view.fxml"));
        Scene scene = new Scene(loader.load());
        ManagerMoviesController mv = loader.getController();
        mv.importMovies();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
