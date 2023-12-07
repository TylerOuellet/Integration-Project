package com.example.project;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * controller for the manager reports view.
 */
public class ManagerReportsController {
    @FXML
    public ListView<String> showtimesListView;
    @FXML
    ListView<String> moviesListView;

    private ManagerMenuController aManagerMenuController;

    /**
     * Method used to bind to the parent controller.
     * @param pManagerMenuController the controller to be bound to.
     */
    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }
    /**
     * Simply, displays the sales lists by first clearing the view then adding them.
     */
    void displaySalesLists(){
        moviesListView.getItems().clear();
        showtimesListView.getItems().clear();
        moviesListView.getItems().setAll(MovieList.getInstance().composeSalesList());
        showtimesListView.getItems().setAll(ShowtimeList.getInstance().composeSalesList());
    }

    /**
     * calls upon the save method and closes the program.
     */
    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }
}
