package com.example.project;

import java.io.Serializable;

/**
 * Movie class that represents a movie into the app.
 * It adds the ManagementCollection interface.
 */
public class Movie implements Serializable {
    private String aTitle;

    // maybe change this to string
    private Genre aGenre;

    private int aRuntime;

    private int aTicketSales = 0;

    /**
     * Making the new Movie object with the title, genre, and runtime.
     *
     * @param pTitle The title of the movie.
     * @param pGenre The genre of the movie.
     * @param pRuntime The runtime of the movie in minutes.
     */
    public Movie(String pTitle, Genre pGenre, int pRuntime) {
        this.setTitle(pTitle);
        this.setGenre(pGenre);
        this.setRuntime(pRuntime);
    }

    /**
     * Sets the title of the movie.
     *
     * @param pTitle The title set on the movie.
     * @throws IllegalArgumentException If the title length exceeds 255.
     */
    void setTitle(String pTitle){
        if(pTitle.length() < 255 && !pTitle.isEmpty()){
            this.aTitle = pTitle;
        } else {
            throw new IllegalArgumentException("Title length cannot exceed 255 characters");
        }
    }

    /**
     * Sets the runtime of the movie.
     *
     * @param pRuntime The runtime to set the movie.
     * @throws IllegalArgumentException If the runtime length exceeds 1440 minutes.
     */
    void setRuntime(int pRuntime){
        if (pRuntime < 1440 && pRuntime > 0){
            this.aRuntime = pRuntime;
        } else {
            throw new IllegalArgumentException("Movie length should not exceed 1440 minuets");
        }
    }

    /**
     * Sets the genre for the movie.
     *
     * @param pGenre The genre to set for the movie.
     */
    void setGenre(Genre pGenre){
        this.aGenre = pGenre;
    }

    /**
     * Returns the title of the movie.
     * @return the titles.
     */
    String getTitle(){
        return aTitle;
    }

    /**
     * Returns the genre of the movie
     * @return the genres
     */
    Genre getGenre(){
        return aGenre;
    }

    /**
     * Getter for ticket sales.
     * @return ticket sales.
     */
    int getTicketSales(){
        return aTicketSales;
    }

    /**
     * Returns the runtime of the movie
     * @return The runtime in minutes
     */
    int getRuntime(){
        return aRuntime;
    }

    /**
     * Method use to add sales to a movie's ticket sales.
     * @param pSales the # of tickets to be added
     */
    void addSales(int pSales){
        aTicketSales = aTicketSales + pSales;
    }

}
