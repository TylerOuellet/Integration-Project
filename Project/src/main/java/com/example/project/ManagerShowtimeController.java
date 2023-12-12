package com.example.project;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * controller class for the showtime view.
 */
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

    private final ShowtimeList aShowtimeList = ShowtimeList.getInstance();

    private final MovieList aMovieList = MovieList.getInstance();

    private final ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Initializes the on selected index change event for the list view to display data on change.
     * @param url comes with implementation
     * @param resourceBundle comes with implementation
     */
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

    /**
     * used to bind this controller to its parent.
     * @param pManagerMenuController the controller that will be bound.
     */
    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    /**
     * displays the content in the list view
     */
    void displayShowtimes(){
        showtimesListView.getItems().clear();
        showtimesListView.getItems().setAll(aShowtimeList.composeList());
        showtimesListView.getSelectionModel().selectFirst();
    }

    /**
     * fills the choice box with the movies from the list
     */
    void populateChoices(){
        LinkedList<String> moviesToDisplay;
        moviesToDisplay = MovieList.getInstance().composeList();
        movieChoiceBox.getItems().setAll(moviesToDisplay);

        LinkedList<String> screeningRoomsToDisplay;
        screeningRoomsToDisplay = ScreeningRoomList.getInstance().composeList();
        screeningRoomChoiceBox.getItems().setAll(screeningRoomsToDisplay);
    }

    /**
     * used to add a new showtime to the list, re-displays the list.
     */
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

    /**
     * used to delete a showtime from the list, re-displays the list
     */
    @FXML
    void onDeleteClick(){
        aShowtimeList.delete(showtimesListView.getSelectionModel().getSelectedIndex());
        displayShowtimes();
    }

    /**
     * used to update an existing showtime, re-displays the list
     */
    @FXML
    void onUpdateClick(){
        try {
            LocalDateTime dateTimeInput = LocalDateTime.parse(showtimeTextField.getText(), formatter);
            Movie movieSelection = aMovieList.getIndex(movieChoiceBox.getSelectionModel().getSelectedIndex());
            ScreeningRoom screeningRoomSelection = aScreeningRoomList.getIndex(screeningRoomChoiceBox.getSelectionModel().getSelectedIndex());

            Showtime updatedShowtime = new Showtime(dateTimeInput, movieSelection, screeningRoomSelection);

            ShowtimeList.getInstance().update(showtimesListView.getSelectionModel().getSelectedIndex(), updatedShowtime);
            displayShowtimes();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Date must be formatted to this format: yyyy-MM-dd HH:mm
                    YOU MUST SELECT A MOVIE!!!!!!!!
                    you must select a screening room...""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * calls upon the save method and exits the program.
     */
    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }
}
