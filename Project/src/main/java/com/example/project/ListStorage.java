package com.example.project;

public class ListStorage {
    public MovieList aMovieList = new MovieList();

    public ScreeningRoomList aScreeningRoomList = new ScreeningRoomList();

    public MovieList getMovieList(){
        return aMovieList;
    }

    public ScreeningRoomList getScreeningRoomList(){
        return aScreeningRoomList;
    }
}
