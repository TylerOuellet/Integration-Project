package com.example.project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * parent class of all the manager tabs, therefore contains the tab view.
 * is also responsible for saving and loading the lists.
 */
public class ManagerMenuController implements Initializable {
    @FXML
    private AnchorPane nestedShowtimeView;

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

    @FXML
    private ManagerShowtimeController nestedShowtimeViewController;

    @FXML
    private ManagerReportsController nestedStatsViewController;


    /**
     * method that executes when switched to the screening room tab. binds the controllers then displays the content
     */
    @FXML
    void screeningRoomSwitch (){
        if (screeningRoomTab.isSelected()){
            if (nestedScreeningRoomViewController != null){
                this.nestedScreeningRoomViewController.bindToManagerMenuController(this);
                nestedScreeningRoomViewController.displayScreeningRooms();
            }
        }
    }

    /**
     * method that executes when switched to movies tab. binds the controllers then displays the content.
     */
    @FXML
    void movieSwitch(){
        if (movieTab.isSelected()){
            if(nestedMoviesViewController != null){
                this.nestedMoviesViewController.bindTOManagerMenuController(this);
                nestedMoviesViewController.displayMovies();
            }
        }
    }

    /**
     * method that executes when switched to the Showtime tab. binds the controllers then displays the content.
     */
    @FXML
    void onShowtimeSwitch(){
        if (showtimeTab.isSelected()){
            if(nestedShowtimeViewController != null){
                this.nestedShowtimeViewController.bindTOManagerMenuController(this);
                nestedShowtimeViewController.populateChoices();
                nestedShowtimeViewController.displayShowtimes();
            }
        }
    }

    /**
     * method that executes when switched to the stats tab. binds the controllers then displays the content.
     */
    @FXML
    void onStatsSwitch(){
        if (statsTab.isSelected()){
            if(nestedStatsViewController != null){
                nestedStatsViewController.bindTOManagerMenuController(this);
                nestedStatsViewController.displaySalesLists();
            }
        }
    }

    /**
     * Method that saves the content of every manager list to a bin file.
     */
    void save(){
        MovieSelectionController.save();
    }

    /**
     * loads data and displays the data on the first tab.
     * @param url comes with interface
     * @param resourceBundle comes with interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataInitializer.initializer();
        nestedMoviesViewController.displayMovies();
    }

}

