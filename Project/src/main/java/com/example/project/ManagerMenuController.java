package com.example.project;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManagerMenuController implements Initializable {
    @FXML
    private AnchorPane nestedShowtimeView;

    @FXML
    private AnchorPane nestedScreeningRoomView;

    @FXML
    private AnchorPane nestedMoviesView;

    @FXML
    private AnchorPane nestedStatsView;

    @FXML
    private Tab showtimeTab;

    @FXML
    private Tab screeningRoomTab;

    @FXML
    private Tab movieTab;

    @FXML
    private Tab statsTab;

    @FXML
    private ManagerMoviesController nestedMoviesViewController;

    @FXML
    private ManagerScreeningRoomController nestedScreeningRoomViewController;

    @FXML
    private ManagerShowtimeController nestedShowtimeViewController;

    @FXML
    private ManagerReportsController nestedStatsViewController;

    private MovieList aMovieList = MovieList.getInstance();

    private ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();

    private ShowtimeList aShowtimeList = ShowtimeList.getInstance();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @FXML
    void screeningRoomSwitch (Event event){
        if (screeningRoomTab.isSelected()){
            if (nestedScreeningRoomViewController != null){
                this.nestedScreeningRoomViewController.bindToManagerMenuController(this);
                nestedScreeningRoomViewController.displayScreeningRooms();
            }

        }
    }

    @FXML
    void test (Event event){
        if (movieTab.isSelected()){
            if(nestedMoviesViewController != null){
                this.nestedMoviesViewController.bindTOManagerMenuController(this);
                System.out.println("ManagerMoviesController set successfully");
                nestedMoviesViewController.displayMovies();
            }
        }
    }

    @FXML
    void onShowtimeSwitch(Event event){
        if (showtimeTab.isSelected()){
            if(nestedShowtimeViewController != null){
                this.nestedShowtimeViewController.bindTOManagerMenuController(this);
                nestedShowtimeViewController.populateChoices();
               // nestedShowtimeViewController.loadData();
                nestedShowtimeViewController.displayShowtimes();
            }

        }
    }

    @FXML
    void onStatsSwich(Event event){
        if (statsTab.isSelected()){
            if(nestedStatsViewController != null){
                nestedStatsViewController.bindTOManagerMenuController(this);
                nestedStatsViewController.displaySalesLists();
                nestedStatsViewController.bindTest();
            }
        }
    }
    void save(){
        System.out.println("Saved!");
        try(FileOutputStream fs = new FileOutputStream("Lists.bin")){
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(aMovieList);
            os.writeObject(aScreeningRoomList);
            os.writeObject(aShowtimeList);

            os.close();

        } catch (IOException e){
            e.fillInStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File listBin = new File("Lists.bin");
        if (listBin.exists()){
            try(FileInputStream fs = new FileInputStream("Lists.bin")){
                ObjectInputStream os = new ObjectInputStream(fs);
                aMovieList = (MovieList) os.readObject();
                aScreeningRoomList = (ScreeningRoomList)  os.readObject();
                aShowtimeList = (ShowtimeList) os.readObject();
                MovieList.setInstance(aMovieList);
                ScreeningRoomList.setInstance(aScreeningRoomList);
                ShowtimeList.setInstance(aShowtimeList);


                nestedMoviesViewController.displayMovies();
                System.out.println("Movies: " + aMovieList.composeList());

            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            ScreeningRoom test1 = new ScreeningRoom(123, "QWE");
            ScreeningRoom test2 = new ScreeningRoom(123, "QWE");
            ScreeningRoom test3 = new ScreeningRoom(123, "QWE");

            aScreeningRoomList.add(test1);
            aScreeningRoomList.add(test2);
            aScreeningRoomList.add(test3);

            Movie mtest1 = new Movie("test1", Genre.Action, 123);
            Movie mtest2 = new Movie("test2", Genre.Action, 123);
            Movie mtest3 = new Movie("test3", Genre.Action, 123);

            aMovieList.add(mtest1);
            aMovieList.add(mtest2);
            aMovieList.add(mtest3);

            Showtime stest1 = new Showtime(LocalDateTime.parse("2023-12-04 16:00", formatter), MovieList.getInstance().getIndex(0), ScreeningRoomList.getInstance().getIndex(0));
            Showtime stest2 = new Showtime(LocalDateTime.parse("2023-12-04 12:00", formatter), MovieList.getInstance().getIndex(1), ScreeningRoomList.getInstance().getIndex(1));
            Showtime stest3 = new Showtime(LocalDateTime.parse("2023-12-04 01:30", formatter), MovieList.getInstance().getIndex(2), ScreeningRoomList.getInstance().getIndex(2));

            aShowtimeList.add(stest1);
            aShowtimeList.add(stest2);
            aShowtimeList.add(stest3);

            nestedMoviesViewController.displayMovies();
        }

    }


}
