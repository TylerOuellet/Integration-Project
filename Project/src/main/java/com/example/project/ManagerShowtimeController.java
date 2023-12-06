package com.example.project;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerShowtimeController implements Initializable {
    @FXML
    private ListView<String> showtimesListView;

    @FXML
    private TextField showtimeTextField;

    @FXML
    private ChoiceBox<String> movieChoiceBox;

    @FXML
    private ChoiceBox<String> screeningRoomChoiceBox;

    private ManagerMenuController aManagerMenuController;

    private ShowtimeList aShowtimeList = ShowtimeList.getInstance();

    private MovieList aMovieList = MovieList.getInstance();

    private ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();

    int loadCounter = 0;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showtimesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showtimeTextField.setText(aShowtimeList.getIndex(showtimesListView.getSelectionModel().getSelectedIndex()).getShowTime().format(formatter));
                movieChoiceBox.setValue(aShowtimeList.getIndex(showtimesListView.getSelectionModel().getSelectedIndex()).getShownMovie().getTitle());
                screeningRoomChoiceBox.setValue(aShowtimeList.getIndex(showtimesListView.getSelectionModel().getSelectedIndex()).getScreeningRoom().getRoomID());

            }
        });

    }

    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    void displayShowtimes(){
        showtimesListView.getSelectionModel().selectFirst();
        showtimesListView.getItems().clear();
        showtimesListView.getItems().setAll(aShowtimeList.composeList());
    }

    void populateChoices(){
        LinkedList<String> moviesToDisplay;
        moviesToDisplay = MovieList.getInstance().composeList();
        movieChoiceBox.getItems().setAll(moviesToDisplay);

        LinkedList<String> screeningRoomsToDisplay;
        screeningRoomsToDisplay = ScreeningRoomList.getInstance().composeList();
        screeningRoomChoiceBox.getItems().setAll(screeningRoomsToDisplay);
    }
    void loadData(){
        while (loadCounter < 1){
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            System.out.println(MovieList.getInstance().getIndex(2).getTitle());
            System.out.println(ScreeningRoomList.getInstance().getIndex(2).getRoomID());


            Showtime test1 = new Showtime(LocalDateTime.parse("2023-12-04 16:00", formatter), MovieList.getInstance().getIndex(0), ScreeningRoomList.getInstance().getIndex(0));
            Showtime test2 = new Showtime(LocalDateTime.parse("2023-12-04 12:00", formatter), MovieList.getInstance().getIndex(1), ScreeningRoomList.getInstance().getIndex(1));
            Showtime test3 = new Showtime(LocalDateTime.parse("2023-12-04 01:30", formatter), MovieList.getInstance().getIndex(2), ScreeningRoomList.getInstance().getIndex(2));

            ShowtimeList.getInstance().add(test1);
            ShowtimeList.getInstance().add(test2);
            ShowtimeList.getInstance().add(test3);
            loadCounter++;
        }


    }
    @FXML
    void onAddClick(){
        try {
            LocalDateTime dateTimeInput = LocalDateTime.parse(showtimeTextField.getText(), formatter);
            Movie movieSelection = aMovieList.getIndex(movieChoiceBox.getSelectionModel().getSelectedIndex());
            ScreeningRoom screeningRoomSelection = aScreeningRoomList.getIndex(screeningRoomChoiceBox.getSelectionModel().getSelectedIndex());

            Showtime addedShowtime = new Showtime(dateTimeInput, movieSelection, screeningRoomSelection);
            aShowtimeList.add(addedShowtime);

            displayShowtimes();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Date must be formatted to this format: yyyy-MM-dd HH:mm
                    YOU MUST SELECT A MOVIE!!!!!!!!
                    you must select a screening room... or else...""", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    void onDeleteClick(){
        aShowtimeList.delete(showtimesListView.getSelectionModel().getSelectedIndex());
        displayShowtimes();
    }

    @FXML
    void onUpdateClick(){
        try {
            LocalDateTime dateTimeInput = LocalDateTime.parse(showtimeTextField.getText(), formatter);
            Movie movieSelection = aMovieList.getIndex(movieChoiceBox.getSelectionModel().getSelectedIndex());
            ScreeningRoom screeningRoomSelection = aScreeningRoomList.getIndex(screeningRoomChoiceBox.getSelectionModel().getSelectedIndex());

            Showtime updatedShowtime = new Showtime(dateTimeInput, movieSelection, screeningRoomSelection);

            ShowtimeList.getInstance().update(showtimesListView.getSelectionModel().getSelectedIndex(), updatedShowtime);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Date must be formatted to this format: yyyy-MM-dd HH:mm
                    YOU MUST SELECT A MOVIE!!!!!!!!
                    you must select a screening room... or else...""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }


}
