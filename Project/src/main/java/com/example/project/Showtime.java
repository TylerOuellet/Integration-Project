package com.example.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the showtime in the movie theater for the application.
 * Used to store information about the showtime.
 */
public class Showtime implements ManagementCollection {

    /** The date and the time. */
    private LocalDateTime aShowTime;

    /** The movie being shown during the showtime. */
    private Movie aShownMovie;

    /** Which screening room used during the showtime. */
    private ScreeningRoom aScreeningRoom;


    /**
     * Constructor.
     * @param pShowTime The date and time.
     * @param pShownMovie The movie shown.
     * @param pScreeningRoom The screening room used.
     */
    public Showtime(LocalDateTime pShowTime, Movie pShownMovie, ScreeningRoom pScreeningRoom) {
        setShowTime(pShowTime);
        setShownMovie(pShownMovie);
        setScreeningRoom(pScreeningRoom);
    }

    /**
     * Getter of the date and time.
     * @return the date and the time.
     */
    public LocalDateTime getShowTime() {
        return aShowTime;
    }

    /**
     * Setter of the date and time.
     * @param pShowTime the date and time.
     * @throws IllegalArgumentException if showtime is null.
     */
    public void setShowTime(LocalDateTime pShowTime) {
        if (pShowTime != null) {
            this.aShowTime = pShowTime;
        } else {
            throw new IllegalArgumentException("The showtime cannot be null.");
        }
    }

    /**
     * Getter of the shown movie.
     * @return the shown movie.
     */
    public Movie getShownMovie() {
        return aShownMovie;
    }

    /**
     * Setter of the shown movie.
     * @param pShownMovie The movie shown.
     * @throws IllegalArgumentException if the movie shown is null.
     */
    public void setShownMovie(Movie pShownMovie) {
        if (pShownMovie != null) {
            this.aShownMovie = pShownMovie;
        } else {
            throw new IllegalArgumentException("The shown movie cannot be null.");
        }
    }

    /**
     * Getter of the screening room.
     * @return the screening room used for the specific showtime.
     */
    public ScreeningRoom getScreeningRoom() {
        return aScreeningRoom;
    }

    /**
     * Setter of the screening room.
     * @param pScreeningRoom the screening room used fot the specific showtime.
     * @throws IllegalArgumentException if the screening room is null.
     */
    public void setScreeningRoom(ScreeningRoom pScreeningRoom) {
        if (pScreeningRoom != null) {
            this.aScreeningRoom = pScreeningRoom;
        } else {
            throw new IllegalArgumentException("The screening room cannot be null.");
        }
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return getShowTime().format(formatter);
    }
}
