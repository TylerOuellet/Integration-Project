package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    @FXML
    private TextField seatsTextField;


    private ManagerMenuController aManagerMenuController;


    ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        screeningRoomListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                seatsTextField.setText(String.valueOf(aScreeningRoomList.getIndex(screeningRoomListView.getSelectionModel().getSelectedIndex()).getRemainingSeats()));
                roomIDTextField.setText(aScreeningRoomList.getIndex(screeningRoomListView.getSelectionModel().getSelectedIndex()).getRoomID());
            }
        });

    }

    public void bindToManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    public void displayScreeningRooms(){
        screeningRoomListView.getSelectionModel().selectFirst();
        screeningRoomListView.getItems().clear();
        screeningRoomListView.getItems().setAll(aScreeningRoomList.composeList());
    }
    @FXML
    public void onAddClick(){
        String id = roomIDTextField.getText();
        int seats = Integer.parseInt(seatsTextField.getText());
        ScreeningRoom addedScreeningRoom = new ScreeningRoom(seats, id);
        aScreeningRoomList.add(addedScreeningRoom);
        displayScreeningRooms();
    }

    @FXML
    public void onUpdateClick(){
        String id = roomIDTextField.getText();
        int seats = Integer.parseInt(seatsTextField.getText());
        ScreeningRoom update = new ScreeningRoom(seats, id);
        aScreeningRoomList.update(screeningRoomListView.getSelectionModel().getSelectedIndex(), update);
        displayScreeningRooms();

    }

    @FXML
    public void onDeleteClick(){
        aScreeningRoomList.delete(screeningRoomListView.getSelectionModel().getSelectedIndex());
        displayScreeningRooms();
    }

}
