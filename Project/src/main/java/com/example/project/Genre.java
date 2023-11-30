package com.example.project;

public enum Genre {
    ACTION("Action"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    SCIENCEFICTION("Science Fiction"),
    FANTASY("Fantasy");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

