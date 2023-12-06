package com.example.project;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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




    @FXML
    void screeningRoomSwitch (Event event){
        if (screeningRoomTab.isSelected()){
            if (nestedScreeningRoomViewController != null){
                this.nestedScreeningRoomViewController.bindToManagerMenuController(this);
                nestedScreeningRoomViewController.displayScreeningRooms();
            }

        }
    }

    @FXML
    void test (Event event){
        if (movieTab.isSelected()){
            if(nestedMoviesViewController != null){
                this.nestedMoviesViewController.bindTOManagerMenuController(this);
                System.out.println("ManagerMoviesController set successfully");
                nestedMoviesViewController.displayMovies();
            }
        }
    }

    @FXML
    void onShowtimeSwitch(Event event){
        if (showtimeTab.isSelected()){
            if(nestedShowtimeViewController != null){
                this.nestedShowtimeViewController.bindTOManagerMenuController(this);
                nestedShowtimeViewController.populateChoices();
               // nestedShowtimeViewController.loadData();
                nestedShowtimeViewController.displayShowtimes();
            }

        }
    }

    @FXML
    void onStatsSwich(Event event){
        if (statsTab.isSelected()){
            if(nestedStatsViewController != null){
                nestedStatsViewController.bindTOManagerMenuController(this);
                nestedStatsViewController.displaySalesLists();
                nestedStatsViewController.bindTest();
            }
        }
    }
    void save(){
        System.out.println("Saved!");
        try(FileOutputStream fs = new FileOutputStream("Lists.bin")){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(MovieList.getInstance());
            os.writeObject(ScreeningRoomList.getInstance());
            os.writeObject(ShowtimeList.getInstance());

            os.close();

        } catch (IOException e){
            e.fillInStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataInitializer.initializer();
        nestedMoviesViewController.displayMovies();
    }

}

