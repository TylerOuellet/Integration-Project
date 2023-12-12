package com.example.project;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the screening room view
 */
public class ManagerScreeningRoomController implements Initializable {

    @FXML
    public ListView<String> screeningRoomListView;

    @FXML
    private TextField roomIDTextField;

    @FXML
    private TextField seatsTextField;


    private ManagerMenuController aManagerMenuController;


    ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();


    /**
     * initializes the on change list box event to set data on item switch.
     * @param url comes with implementation
     * @param resourceBundle comes with implementation
     */
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

    /**
     * Used to bind the controller to its parent.
     * @param pManagerMenuController the controller to be bound to.
     */
    public void bindToManagerMenuController(ManagerMenuController pManagerMenuController){
        this.aManagerMenuController = pManagerMenuController;
    }

    /**
     * used to display the contents of the screening room list in the list view.
     */
    public void displayScreeningRooms(){
        screeningRoomListView.getItems().clear();
        screeningRoomListView.getItems().setAll(aScreeningRoomList.composeList());
        screeningRoomListView.getSelectionModel().selectFirst();
    }

    /**
     * Used to add another screening room to the list, re-displays the list.
     */
    @FXML
    public void onAddClick(){
        try {
            String id = roomIDTextField.getText();
            int seats = Integer.parseInt(seatsTextField.getText());
            ScreeningRoom addedScreeningRoom = new ScreeningRoom(seats, id);
            aScreeningRoomList.add(addedScreeningRoom);
            displayScreeningRooms();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Seats cannot be negative
                    ID also cannot be empty""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * used to update an existing screening room, re-displays the list
     */
    @FXML
    public void onUpdateClick(){
        try {
            String id = roomIDTextField.getText();
            int seats = Integer.parseInt(seatsTextField.getText());
            ScreeningRoom update = new ScreeningRoom(seats, id);
            aScreeningRoomList.update(screeningRoomListView.getSelectionModel().getSelectedIndex(), update);
            displayScreeningRooms();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.NONE, """
                    Seats cannot be negative bozo
                    ID also cannot be empty silly""", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * used to delete an existing screening room, re-displays the list.
     */
    @FXML
    public void onDeleteClick(){
        aScreeningRoomList.delete(screeningRoomListView.getSelectionModel().getSelectedIndex());
        displayScreeningRooms();
    }

    /**
     * calls upon the save method and closes the application.
     */
    @FXML
    void onSaveClick(){
        aManagerMenuController.save();
        Platform.exit();
        System.exit(0);
    }

}
