package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.LinkedList;
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


    public MovieList loadedMovies = MovieList.getInstance();

    private ManagerMenuController aManagerMenuController;


    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }


    public void displayMovies(){
        moviesListView.getSelectionModel().selectFirst();
        moviesListView.getItems().clear();

       // moviesListView.getItems().removeAll();
        moviesListView.getItems().setAll(loadedMovies.composeList());
        moviesListView.getSelectionModel().selectFirst();
       // moviesListView.getSelectionModel().selectFirst();
        moviesListView.refresh();
    }

    @FXML
    void onAddClick(){
        String movieTitle = movieNameTextfield.getText();
        Genre selectedGenre = genreChoiceBox.getValue();
        int runtime = Integer.parseInt(runtimeTextField.getText());
        Movie newMovie = new Movie(movieTitle, selectedGenre, runtime);
        loadedMovies.add(newMovie);
        moviesListView.getItems().clear();
        displayMovies();
    }

    @FXML
    void onDeleteClick(){
        loadedMovies.delete(moviesListView.getSelectionModel().getSelectedIndex());
        moviesListView.getItems().clear();
        displayMovies();
    }
    @FXML
    void onUpdateClick(){
        int runtime = Integer.parseInt(runtimeTextField.getText());
        Movie updatedMovie = new Movie(movieNameTextfield.getText(), genreChoiceBox.getValue(), runtime);
        loadedMovies.update(moviesListView.getSelectionModel().getSelectedIndex(), updatedMovie);
        moviesListView.getItems().clear();
        displayMovies();
    }

    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Genre genre : Genre.values()){
            genreChoiceBox.getItems().add(genre);
        }
        moviesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                movieNameTextfield.setText(loadedMovies.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getTitle());
                runtimeTextField.setText(String.valueOf(loadedMovies.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getRuntime()));
                genreChoiceBox.setValue(loadedMovies.getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getGenre());
            }
        });
    }
}
