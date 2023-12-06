package com.example.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataInitializer {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void initializer() {
        File listBin = new File("Lists.bin");
        if (listBin.exists()){
            try(FileInputStream fs = new FileInputStream("Lists.bin")){
                ObjectInputStream os = new ObjectInputStream(fs);
                MovieList aMovieList = (MovieList) os.readObject();
                ScreeningRoomList aScreeningRoomList = (ScreeningRoomList)  os.readObject();
                ShowtimeList aShowtimeList = (ShowtimeList) os.readObject();
                MovieList.setInstance(aMovieList);
                ScreeningRoomList.setInstance(aScreeningRoomList);
                ShowtimeList.setInstance(aShowtimeList);


                System.out.println("Movies: " + aMovieList.composeList());
                System.out.println("ScreeningRooms: " + aMovieList.composeList());
                System.out.println("Showtimes: " + aShowtimeList.composeList());

            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            ScreeningRoom test1 = new ScreeningRoom(123, "QWE");
            ScreeningRoom test2 = new ScreeningRoom(123, "QWE");
            ScreeningRoom test3 = new ScreeningRoom(123, "QWE");

            ScreeningRoomList aScreeningRoomList = ScreeningRoomList.getInstance();
            aScreeningRoomList.add(test1);
            aScreeningRoomList.add(test2);
            aScreeningRoomList.add(test3);

            Movie mtest1 = new Movie("test1", Genre.Action, 123);
            Movie mtest2 = new Movie("test2", Genre.Action, 123);
            Movie mtest3 = new Movie("test3", Genre.Action, 123);

            MovieList aMovieList = MovieList.getInstance();
            aMovieList.add(mtest1);
            aMovieList.add(mtest2);
            aMovieList.add(mtest3);

            Showtime stest1 = new Showtime(LocalDateTime.parse("2023-12-04 16:00", formatter), MovieList.getInstance().getIndex(0), ScreeningRoomList.getInstance().getIndex(0));
            Showtime stest2 = new Showtime(LocalDateTime.parse("2023-12-04 12:00", formatter), MovieList.getInstance().getIndex(1), ScreeningRoomList.getInstance().getIndex(1));
            Showtime stest3 = new Showtime(LocalDateTime.parse("2023-12-04 01:30", formatter), MovieList.getInstance().getIndex(2), ScreeningRoomList.getInstance().getIndex(2));

            ShowtimeList aShowtimeList = ShowtimeList.getInstance();
            aShowtimeList.add(stest1);
            aShowtimeList.add(stest2);
            aShowtimeList.add(stest3);
        }
    }
}
