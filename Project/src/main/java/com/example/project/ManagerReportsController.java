package com.example.project;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ManagerReportsController {
    @FXML
    public ListView<String> showtimesListView;
    @FXML
    ListView<String> moviesListView;

    private ManagerMenuController aManagerMenuController;
    public void bindTOManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    public void bindTest(){
        System.out.println("Bound");
    }

    void displaySalesLists(){
        moviesListView.getItems().clear();
        showtimesListView.getItems().clear();
        moviesListView.getItems().setAll(MovieList.getInstance().composeSalesList());
        showtimesListView.getItems().setAll(ShowtimeList.getInstance().composeSalesList());
    }

    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }


}
