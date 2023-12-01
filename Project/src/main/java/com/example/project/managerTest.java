package com.example.project;

public class managerTest {
    public static void main(String[] args) {
        MovieList movies = new MovieList();
        Movie movie1 = new Movie("helloworld",Genre.Action, 120);
        Movie movie2 = new Movie("helloworld2: back again!",Genre.Action, 120);
        movies.add(movie1);
        movies.add(movie2);
        System.out.println(movies.composeList());
    }
}
