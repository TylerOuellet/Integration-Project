package com.example.project;

import java.io.Serializable;
import java.util.*;

public class MovieList implements Serializable, Iterable<Movie> {


    private final List<Movie> aMovieList = new ArrayList<>();

    private static MovieList aInstance;

    /**
     * Empty constructor for singleton pattern
     */
    private MovieList() {}

    /**
     * used to retrieve a movie at a given index
     * @param selectedIndex the target index of the retrieval
     * @return the Movie that was retrieved
     */
    public Movie getIndex(int selectedIndex){
        if (selectedIndex > -1) {
            return this.aMovieList.get(selectedIndex);
        }
        else
            return this.aMovieList.get(0);
    }

    /**
     * used to add movies to the movie list
     * @param pMovie the movie that will be added
     */
    public void add(Movie pMovie){
        this.aMovieList.add(pMovie);
    }

    /**
     * use to compose a list of strings from the movie titles. Used for GUI display.
     * @return A list of sitings made up of the movie titles.
     */
    public LinkedList<String> composeList(){
        LinkedList<String> listed = new LinkedList<>();
        for (Movie currentMovie : aMovieList){
            listed.add(currentMovie.getTitle());
        }
        return listed;
    }

    /**
     * Similar to Compose list except it returns the amount of tickets sold along with the title
     * @return a list of strings composed of the movie title and tickets sold.
     */
    public LinkedList<String> composeSalesList(){
        LinkedList<String> listed = new LinkedList<>();
        for (Movie currentMovie : aMovieList){
            listed.add(currentMovie.getTitle() + "Tickets Sold: "+ currentMovie.getTicketSales());
        }
        return listed;
    }

    /**
     * used to delete movies from the list
     * @param pIndex the index to target the movie that will be deleted
     */
    public void delete(int pIndex){
        this.aMovieList.remove(pIndex);
    }

    /**
     * Used to update a movie in the list.
     * @param pIndex the target of the update.
     * @param pMovie the new value of the update.
     */
    public void update(int pIndex, Movie pMovie){
        this.aMovieList.set(pIndex,pMovie);
    }

    /**
     * Singleton instance
     * @return the singleton instance.
     */
    public static MovieList getInstance(){
        if (aInstance == null){
            aInstance = new MovieList();
        }
        return aInstance;
    }

    private class MovieIterator implements Iterator<Movie>{

        private int index =0;
        @Override
        public boolean hasNext(){
            return index < aMovieList.size();
        }

        @Override
        public Movie next(){
            if (hasNext()){
                return aMovieList.get(index++);
            } else {
                throw new NoSuchElementException("error in iterating");
            }
        }

    }
    @Override
    public Iterator<Movie> iterator(){
        return new MovieIterator();
    }

    public void addAll(MovieList pMovieList){
        for (Movie currentMovie : pMovieList){
            aMovieList.add(currentMovie);
        }
    }



}
