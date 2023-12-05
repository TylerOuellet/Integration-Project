package com.example.project;

import java.io.Serializable;
import java.util.*;

public class ScreeningRoomList implements Serializable, Iterable<ScreeningRoom> {

    private final List<ScreeningRoom> aScreeningRooms = new ArrayList<>();

    private static ScreeningRoomList aInstance;

    /**
     * Empty constructor for singleton pattern
     */
    private ScreeningRoomList() {}

    /**
     * Used to get the screening room at a given index
     * @param pIndex the targeting index
     * @return the target screening room.
     */
    public ScreeningRoom getIndex(int pIndex){
        if(pIndex > 1){
            return this.aScreeningRooms.get(pIndex);
        }
        return this.aScreeningRooms.get(0);
    }

    /**
     * Used to add a new screening room to the list
     * @param pScreeningRoom the screening room to be added.
     */
    public void add (ScreeningRoom pScreeningRoom){
        aScreeningRooms.add(pScreeningRoom);
    }

    /**
     * Used to delete a screening room to the list.
     * @param pIndex the targeting index for the deletion
     */
    public void delete (int pIndex){
        aScreeningRooms.remove(pIndex);
    }

    /**
     * Used to update a screening room from the list
     * @param pIndex the target of the update
     * @param pScreeningRoom the new result of the update
     */
    public void update (int pIndex, ScreeningRoom pScreeningRoom){
        aScreeningRooms.set(pIndex,pScreeningRoom);
    }

    /**
     * Used to convert the list of screening rooms into a list of strings, Used for GUI applications
     * @return a list of strings consisting of screening room IDs
     */
    public LinkedList<String> composeList(){
        LinkedList<String> listed = new LinkedList<>();

        for (ScreeningRoom currentScreeningRoom : aScreeningRooms){
            listed.add(currentScreeningRoom.getRoomID());
        }
        return listed;
    }

    /**
     * The singleton instance for the Screening room list.
     * @return the singleton instance.
     */
    public static ScreeningRoomList getInstance(){
        if (aInstance == null){
            aInstance = new ScreeningRoomList();
        }
        return aInstance;
    }

    private class ScreeningRoomIterator implements Iterator<ScreeningRoom>{
        private int index = 0;

        @Override
        public boolean hasNext(){
            return index < aScreeningRooms.size();
        }

        @Override
        public ScreeningRoom next(){
            if (hasNext()){
                return aScreeningRooms.get(index++);
            } else {
                throw new NoSuchElementException("error in iterating");
            }

        }
    }

    @Override
    public Iterator<ScreeningRoom> iterator(){
        return new ScreeningRoomIterator();
    }

    public static void setInstance(ScreeningRoomList pScreeningRoomList){
        for (ScreeningRoom currentScreeningRoom : pScreeningRoomList){
            aInstance.add(currentScreeningRoom);
        }
    }
}
