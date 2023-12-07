package com.example.project;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Used to hold a list of Showtimes, implements an iterator as well as the singleton design pattern
 * for a global reference.
 */
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

    /**
     * nested class to create iterator.
     */
    private class ShowtimeIterator implements Iterator<Showtime>{

        private int index = 0;

        /**
         * has next check
         * @return if there is a next element
         */
        public boolean hasNext(){
            return index < aShowTimeList.size();
        }

        /**
         * iteration code
         * @return the next Showtime
         */
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

    /**
     * Creates an iterator
     * @return the created iterator
     */
    @Override
    public Iterator<Showtime> iterator(){
        return new ShowtimeIterator();
    }

    /**
     * used tom set the instance of the list using the iterator, used exclusively on data load.
     * @param pShowtimeList the list to be set
     */
    public static void setInstance(ShowtimeList pShowtimeList){
        for (Showtime currentShowtime : pShowtimeList){
            getInstance().add(currentShowtime);
        }
    }
}
