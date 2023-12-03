package com.example.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieList {
    String aName;

    private final List<Movie> aMovieList = new ArrayList<Movie>();

    public Movie getIndex(int selectedIndex){
        if (selectedIndex > -1) {
            return this.aMovieList.get(selectedIndex);
        }
        else
            return this.aMovieList.get(0);
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
    public void delete(int pIndex){
        this.aMovieList.remove(pIndex);
    }
    public void update(int pIndex, Movie pMovie){
        this.aMovieList.set(pIndex,pMovie);
    }
}
