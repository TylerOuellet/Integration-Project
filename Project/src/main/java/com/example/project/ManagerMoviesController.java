package com.example.project;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller class for the movie view
 */
public class ManagerMoviesController implements Initializable {
    @FXML
    public ListView<String> moviesListView;

    @FXML
    public TextField movieNameTextfield;

    @FXML
    private ChoiceBox<Genre> genreChoiceBox;

    @FXML
    private Button addButton;

    @FXML
    private TextField runtimeTextField;


    public MovieList aMovieList = MovieList.getInstance();

    private ManagerMenuController aManagerMenuController;

    /**
     * used to bind the controller to its parent.
     * @param pManagerMenuController the controller to be bound to.
     */
    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    /**
     * displays the movies in the list view, also clears the list view
     */
    public void displayMovies(){
        moviesListView.getSelectionModel().selectFirst();
        moviesListView.getItems().clear();
        moviesListView.getItems().setAll(aMovieList.composeList());
        moviesListView.getSelectionModel().selectFirst();
        moviesListView.refresh();
    }

    /**
     * used to add a movie to the movie list.
     */
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

    /**
     * used to delete an existing movie.
     */
    @FXML
    void onDeleteClick(){
        aMovieList.delete(moviesListView.getSelectionModel().getSelectedIndex());
        moviesListView.getItems().clear();
        displayMovies();
    }

    /**
     * used to update an existing movie.
     */
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

    /**
     * calls the save method and closes the program
     */
    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }

    /**
     * populates the choice box and initializes the on selected index action for the issue, to display the selected
     * movie's content in their respective places.
     * @param url comes from implementation
     * @param resourceBundle comes from implementation
     */
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
