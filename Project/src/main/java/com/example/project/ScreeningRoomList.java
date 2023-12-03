package com.example.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScreeningRoomList {

    private final List<ScreeningRoom> aScreeningRooms = new ArrayList<>();

    public ScreeningRoom getIndex(int pIndex){
        if(pIndex > 1){
            return this.aScreeningRooms.get(pIndex);
        }
        return this.aScreeningRooms.get(0);
    }

    public void add (ScreeningRoom pScreeningRoom){
        aScreeningRooms.add(pScreeningRoom);
    }

    public void delete (int pIndex){
        aScreeningRooms.remove(pIndex);
    }

    public void update (int pIndex, ScreeningRoom pScreeningRoom){
        aScreeningRooms.set(pIndex,pScreeningRoom);
    }

    public LinkedList<String> composeList(){
        LinkedList<String> listed = new LinkedList<>();

        for (ScreeningRoom currentScreeningRoom : aScreeningRooms){
            listed.add(currentScreeningRoom.getRoomID());
        }
        return listed;

    }
}
