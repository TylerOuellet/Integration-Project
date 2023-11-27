package com.example.project;

public class Movie implements ManagementCollection{
    private String aTitle;

    // maybe change this to string
    private Genre aGenre;

    private int aRuntime;

    public Movie(String pTitle, Genre pGenre, int pRuntime) {
        this.setTitle(pTitle);
        this.setGenre(pGenre);
        this.setRuntime(pRuntime);
    }

    void setTitle(String pTitle){
        if(pTitle.length() < 255){
            this.aTitle = pTitle;
        } else {
            throw new IllegalArgumentException("Title length cannot exceed 255 characters");
        }
    }
    void setRuntime(int pRuntime){
        if (pRuntime < 1440){
            this.aRuntime = pRuntime;
        } else {
            throw new IllegalArgumentException("Movie length should not exceed 1440 minuets");
        }
    }
    void setGenre(Genre pGenre){
        this.aGenre = pGenre;
    }
    String getTitle(){
        return aTitle;
    }
    Genre getGenre(){
        return aGenre;
    }
    int getRuntime(){
        return aRuntime;
    }

}
