package com.example.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieManager {
    private Map<String, List<Showtime>> movieShowTimes;

    /**
     * Adding the movies manually.
     */
    public MovieManager() {
        movieShowTimes = new HashMap<>();

        addMovie("Hunger Games", Genre.Action, 120, LocalDateTime.of(2023, 1, 1, 12, 0), 30, "Room A");
        addMovie("Hunger Games", Genre.Action, 120, LocalDateTime.of(2023, 1, 1, 15, 30), 50, "Room B");
        addMovie("Inception", Genre.Sciencefiction, 150, LocalDateTime.of(2023, 1, 1, 14, 0), 40, "Room C");
        addMovie("Titanic", Genre.Romance, 195, LocalDateTime.of(2023, 1, 1, 18, 30), 30, "Room A");
        addMovie("Titanic", Genre.Romance, 195, LocalDateTime.of(2023, 1, 1, 20, 30), 50, "Room B");
        addMovie("The Grinch", Genre.Comedy,104, LocalDateTime.of(2023, 12, 24, 13, 0), 80, "Room C");
        addMovie("The Grinch", Genre.Comedy,104, LocalDateTime.of(2023, 12, 24, 15, 30), 80, "Room C");
    }

    /**
     * Adding movies with the details needed.
     *
     * @param title Title of the movie.
     * @param genre Genre of the movie.
     * @param duration Duration of the movie.
     * @param showtime Showtime of the movie.
     * @param seatsRemaining Seats remaining of the screening room.
     * @param roomID Room ID of the screening room.
     */
    public void addMovie(String title, Genre genre, int duration, LocalDateTime showtime, int seatsRemaining, String roomID) {
        Movie movie = new Movie(title, genre, duration);
        ScreeningRoom screeningRoom = new ScreeningRoom(seatsRemaining, roomID);
        Showtime newShowtime = new Showtime(showtime, movie, screeningRoom);

        if (!movieShowTimes.containsKey(title)) {
            movieShowTimes.put(title, new ArrayList<>());
        }
        movieShowTimes.get(title).add(newShowtime);
    }

    /**
     * Get the showtime of the movie.
     *
     * @param selectedMovie The selected movie.
     * @return The list of showtime for the movie selected.
     */
    public List<Showtime> getShowTimesForMovie(String selectedMovie) {
        return movieShowTimes.getOrDefault(selectedMovie, new ArrayList<>());
    }

    /**
     * Get all the movies available.
     *
     * @return The list of the movies.
     */
    public List<String> getAllMovieTitles() {
        return new ArrayList<>(movieShowTimes.keySet());
    }
}

