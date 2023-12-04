package com.example.project;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMenuController implements Initializable {
    @FXML
    private AnchorPane nestedShowtimesView;

    @FXML
    private AnchorPane nestedScreeningRoomView;

    @FXML
    private AnchorPane nestedMoviesView;

    @FXML
    private AnchorPane nestedStatsView;

    @FXML
    private Tab showtimeTab;

    @FXML
    private Tab screeningRoomTab;

    @FXML
    private Tab movieTab;

    @FXML
    private Tab statsTab;

    @FXML
    private ManagerMoviesController nestedMoviesViewController;

    @FXML
    private ManagerScreeningRoomController nestedScreeningRoomViewController;

    public MovieList aMovieList = new MovieList();

    @FXML
    void screeningRoomSwitch (Event event){
        if (screeningRoomTab.isSelected()){
            if (nestedScreeningRoomViewController != null){
                this.nestedScreeningRoomViewController.bindToManagerMenuController(this);
                nestedScreeningRoomViewController.displayScreeningRooms();
                nestedScreeningRoomViewController.importMovies(aMovieList);
            }

        }
    }

    @FXML
    void test (Event event){
        if (movieTab.isSelected()){
            if(nestedMoviesViewController != null){
                this.nestedMoviesViewController.bindTOManagerMenuController(this);
                System.out.print("ManagerMoviesController set successfully");
                nestedMoviesViewController.displayMovies();
                aMovieList = nestedMoviesViewController.export();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (movieManager != null){
//            FXMLLoader loader = new FXMLLoader(com.example.project.MovieApplication.class.getResource("managerMovies-view.fxml"));
//            try{
//                loader.load();
//                aManagerMoviesController = loader.getController();
//                //aManagerMoviesController.bindTOManagerMenuController(this);
//            } catch (IOException e) {
//                System.out.println("ERROR");
//                throw new RuntimeException(e);
//            }
//        }
//        aManagerMoviesController.bindTOManagerMenuController(this);
    }


}
