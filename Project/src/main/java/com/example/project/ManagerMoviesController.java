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


    public MovieList loadedMovies = new MovieList();

    private ManagerMenuController aManagerMenuController;

    ListStorage aListStorage = new ListStorage();

    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    public void importMovies(){
        System.out.println("Success");

        //For Testing, to be removed with serialized load


        Movie test1 = new Movie("test1", Genre.Action, 123 );
        Movie test2 = new Movie("test2", Genre.Action, 123 );
        Movie test3 = new Movie("test3", Genre.Action, 123 );



		
        aListStorage.getMovieList().add(test1);
        aListStorage.getMovieList().add(test2);
        aListStorage.getMovieList().add(test3);

        this.movieNameTextfield.setText("asdf");

        displayMovies();
    }
    int count = 1;
    public void displayMovies(){
     ObservableList<String> observableMovies = FXCollections.observableArrayList(aListStorage.getMovieList().composeList());
        moviesListView.getItems().removeAll();
        moviesListView.getItems().addAll(observableMovies);
        moviesListView.refresh();


        System.out.println(count);

        count++;
    }




    @FXML
    void test (Event event){

            System.out.println("Loaded!");


    }

    @FXML
    void onAddClick(){
        System.out.println("hello world");
        String movieTitle = movieNameTextfield.getText();
        Genre selectedGenre = genreChoiceBox.getValue();
        int runtime = Integer.parseInt(runtimeTextField.getText());
        Movie newMovie = new Movie(movieTitle, selectedGenre, runtime);
        aListStorage.getMovieList().add(newMovie);
        moviesListView.getItems().clear();
        displayMovies();
    }

    @FXML
    void onDeleteClick(){
        aListStorage.getMovieList().delete(moviesListView.getSelectionModel().getSelectedIndex());
        moviesListView.getItems().clear();
        displayMovies();
    }
    @FXML
    void onUpdateClick(){
        int runtime = Integer.parseInt(runtimeTextField.getText());
        Movie updatedMovie = new Movie(movieNameTextfield.getText(), genreChoiceBox.getValue(), runtime);
        aListStorage.getMovieList().update(moviesListView.getSelectionModel().getSelectedIndex(), updatedMovie);
        moviesListView.getItems().clear();
        displayMovies();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // importMovies();
        for(Genre genre : Genre.values()){
            genreChoiceBox.getItems().add(genre);
        }
        moviesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                movieNameTextfield.setText(aListStorage.getMovieList().getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getTitle());
                runtimeTextField.setText(String.valueOf(aListStorage.getMovieList().getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getRuntime()));
                genreChoiceBox.setValue(aListStorage.getMovieList().getIndex(moviesListView.getSelectionModel().getSelectedIndex()).getGenre());
                System.out.println("Selected item: " + newValue);
            }
        });



    }
}
