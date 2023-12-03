package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerScreeningRoomController implements Initializable {

    @FXML
    public ListView<String> screeningRoomListView;

    @FXML
    private TextField roomIDTextField;

    private TextField seatsTextField;

    ListStorage aListStorage = new ListStorage();

    private ManagerMenuController aManagerMenuController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScreeningRoom test1 = new  ScreeningRoom(123, "QWE");
        ScreeningRoom test2 = new  ScreeningRoom(123, "QWE");
        ScreeningRoom test3 = new  ScreeningRoom(123, "QWE");

        aListStorage.getScreeningRoomList().add(test1);
        aListStorage.getScreeningRoomList().add(test2);
        aListStorage.getScreeningRoomList().add(test3);

    }

    public void bindToManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    public void displayScreeningRooms(){
        screeningRoomListView.getSelectionModel().selectFirst();
        screeningRoomListView.getItems().clear();
        screeningRoomListView.getItems().setAll(aListStorage.getScreeningRoomList().composeList());
    }
}
