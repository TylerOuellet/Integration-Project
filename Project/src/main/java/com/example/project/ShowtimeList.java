package com.example.project;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ShowtimeList implements Serializable, Iterable<Showtime> {
    private final List<Showtime> aShowTimeList = new ArrayList<>();

    private static ShowtimeList aInstance;

    /**
     * Singleton Constructor
     */
    private ShowtimeList (){}

    /**
     * used to return the showtime at a given index
     * @param pIndex the index that is the target of the return
     * @return a showtime
     */
    public Showtime getIndex(int pIndex){
        if (pIndex > -1){
            return aShowTimeList.get(pIndex);
        }
        return aShowTimeList.get(0);
    }

    /**
     * Composes a list of strings from the list of showtimes, used for GUI display.
     * @return a list of strings that is composed of the movie title and when it's played
     */
    public LinkedList<String> composeList(){
        LinkedList<String> listed = new LinkedList<>();
        for (Showtime currentShowtime : aShowTimeList){
            listed.add(currentShowtime.getShownMovie().getTitle()+ " " + "Plays At: " + currentShowtime.getShowTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        }
        return listed;
    }

    /**
     * Similar to compose list but also provides tickets sold
     * @return a string consisting of the movie title, show time and tickets sold
     */
    public LinkedList<String> composeSalesList(){
        LinkedList<String> listed = new LinkedList<>();
        for (Showtime currentShowtime : aShowTimeList){
            listed.add(currentShowtime.getShownMovie().getTitle()+ " " + "Plays At: " + currentShowtime.getShowTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "Tickets Sold: " + currentShowtime.getTicketsSold());

        }
        return listed;
    }

    /**
     * used for adding new showtimes to the list.
     * @param pShowtime the showtime to be added.
     */
    void add(Showtime pShowtime){
        aShowTimeList.add(pShowtime);
    }

    /**
     * Used to delete showtimes from the list
     * @param pIndex the index of the showtime to be deleted.
     */
    void delete(int pIndex){
        aShowTimeList.remove(pIndex);
    }

    /**
     * Used to update a showtime in the showtime list
     * @param pIndex the index of the update's target
     * @param pShowtime the new showtime
     */
    void update(int pIndex, Showtime pShowtime){
        aShowTimeList.set(pIndex, pShowtime);
    }

    /**
     * Singleton instance
     * @return the singleton instance of Showtime list.
     */
    public static ShowtimeList getInstance(){
        if(aInstance == null){
            aInstance = new ShowtimeList();
        }
        return aInstance;
    }

    private class ShowtimeIterator implements Iterator<Showtime>{

        private int index = 0;

        public boolean hasNext(){
            return index < aShowTimeList.size();
        }

        @Override
        public Showtime next(){
            if(hasNext()){
                return aShowTimeList.get(index++);
            }
            else {
                throw new NoSuchElementException("error in iterating");
            }
        }
    }

    @Override
    public Iterator<Showtime> iterator(){
        return new ShowtimeIterator();
    }

    public static void setInstance(ShowtimeList pShowtimeList){
        for (Showtime currentShowtime : pShowtimeList){
            getInstance().add(currentShowtime);
        }
    }
}
