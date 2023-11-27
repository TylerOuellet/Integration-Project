package com.example.project;

import java.time.LocalDateTime;

public class Showtime implements ManagementCollection {
    private LocalDateTime aShowTime;

    private Movie aShownMovie;

    private ScreeningRoom aScreeningRoom;

    public Showtime(LocalDateTime pShowTime, Movie pShownMovie, ScreeningRoom pScreeningRoom) {
        setShowTime(pShowTime);
        setShownMovie(pShownMovie);
        setScreeningRoom(pScreeningRoom);
    }

    public LocalDateTime getShowTime() {
        return aShowTime;
    }

    public void setShowTime(LocalDateTime pShowTime) {
        if (pShowTime != null) {
            this.aShowTime = pShowTime;
        } else {
            throw new IllegalArgumentException("The showtime cannot be null.");
        }
    }

    public Movie getShownMovie() {
        return aShownMovie;
    }

    public void setShownMovie(Movie pShownMovie) {
        if (pShownMovie != null) {
            this.aShownMovie = pShownMovie;
        } else {
            throw new IllegalArgumentException("The shown movie cannot be null.");
        }
    }

    public ScreeningRoom getScreeningRoom() {
        return aScreeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom pScreeningRoom) {
        if (pScreeningRoom != null) {
            this.aScreeningRoom = pScreeningRoom;
        } else {
            throw new IllegalArgumentException("The screening room cannot be null.");
        }
    }

}
