package com.example.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieList {
    String aName;

    private final List<Movie> aMovieList = new ArrayList<Movie>();

    public Movie getIndex(int selectedIndex){
        return this.aMovieList.get(selectedIndex);
    }
    public void setName(String pName){
        this.aName = pName;
    }

    String getName(){
        return this.aName;
    }
    public void add(Movie pMovie){
        this.aMovieList.add(pMovie);
    }

    public LinkedList<String> composeList(){
        LinkedList<String> listed = new LinkedList<>();
        for (Movie currentMovie : aMovieList){
            listed.add(currentMovie.getTitle());
        }
        return listed;
    }
}
