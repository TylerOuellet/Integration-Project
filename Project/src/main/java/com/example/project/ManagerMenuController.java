package com.example.project;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMenuController implements Initializable {
    @FXML

    public AnchorPane movieManager;
    @FXML
    private Tab movieTab;

    @FXML
    private TextField movieNameTextfield;

    @FXML
    private ManagerMoviesController aManagerMoviesController;

    @FXML
    void test (Event event){
        if (movieTab.isSelected()){
            if(aManagerMoviesController != null){
                aManagerMoviesController.importMovies();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (movieManager != null){
            FXMLLoader loader = new FXMLLoader(com.example.project.MovieApplication.class.getResource("managerMovies-view.fxml"));
            try{
                loader.load();
                aManagerMoviesController = loader.getController();
                //aManagerMoviesController.bindTOManagerMenuController(this);
            } catch (IOException e) {
                System.out.println("ERROR");
                throw new RuntimeException(e);

            }
        }
        aManagerMoviesController.bindTOManagerMenuController(this);

    }


}
