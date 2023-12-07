package com.example.project;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMoviesController implements Initializable {
    @FXML
    public ListView<String> moviesListView;

    @FXML
    public TextField movieNameTextfield;


    @FXML
    private Tab movieTab;

    @FXML
    private ChoiceBox<Genre> genreChoiceBox;

    @FXML
    private Button addButton;

    @FXML
    private TextField runtimeTextField;


    public MovieList aMovieList = MovieList.getInstance();

    private ManagerMenuController aManagerMenuController;


    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }


    public void displayMovies(){
        moviesListView.getSelectionModel().selectFirst();
        moviesListView.getItems().clear();

       // moviesListView.getItems().removeAll();
        moviesListView.getItems().setAll(aMovieList.composeList());
        moviesListView.getSelectionModel().selectFirst();
       // moviesListView.getSelectionModel().selectFirst();
        moviesListView.refresh();
    }

    @FXML
    void onAddClick(){
        try {


            String movieTitle = movieNameTextfield.getText();
            Genre selectedGenre = genreChoiceBox.getValue();
            int runtime = Integer.parseInt(runtimeTextField.getText());
            Movie newMovie = new Movie(movieTitle, selectedGenre, runtime);
            aMovieList.add(newMovie);
            moviesListView.getItems().clear();
            displayMovies();

        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Movie title cannot exceed 255 characters or be 0
                    Runtime is in minutes and cannot exceed 1440 minutes (1 day) or be 0 or less
                    You must select a genre""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onDeleteClick(){
        aMovieList.delete(moviesListView.getSelectionModel().getSelectedIndex());
        moviesListView.getItems().clear();
        displayMovies();
    }
    @FXML
    void onUpdateClick(){
        try {
            int runtime = Integer.parseInt(runtimeTextField.getText());
            Movie updatedMovie = new Movie(movieNameTextfield.getText(), genreChoiceBox.getValue(), runtime);
            aMovieList.update(moviesListView.getSelectionModel().getSelectedIndex(), updatedMovie);
            moviesListView.getItems().clear();
            displayMovies();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Movie title cannot exceed 255 characters or be empty
                    Runtime is in minutes and cannot exceed 1440 minutes (1 day) or be 0 or less
                    You must select a genre""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Genre genre : Genre.values()){
            genreChoiceBox.getItems().add(genre);
        }
        moviesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                movieNameTextfield.setText(aMovieList.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getTitle());
                runtimeTextField.setText(String.valueOf(aMovieList.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getRuntime()));
                genreChoiceBox.setValue(aMovieList.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getGenre());
            }
        });
    }
}
