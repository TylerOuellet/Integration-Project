package com.example.project;

public class ScreeningRoom implements ManagementCollection {

    private int aRemainingSeats;
    private String aRoomID;

    public ScreeningRoom(int pRemainingSeats, String pRoomID) {
        setRemainingSeats(pRemainingSeats);
        setRoomID(pRoomID);
    }

    public int getRemainingSeats() {
        return aRemainingSeats;
    }

    public void setRemainingSeats(int pRemainingSeats) {
        if (pRemainingSeats >= 0) {
            this.aRemainingSeats = pRemainingSeats;
        } else {
            throw new IllegalArgumentException("The remaining seats cannot be negative.");
        }
    }

    public String getRoomID() {
        return aRoomID;
    }

    public void setRoomID(String pRoomID) {
        if (pRoomID != null && !pRoomID.isEmpty()) {
            this.aRoomID = pRoomID;
        } else {
            throw new IllegalArgumentException("The Room ID cannot be null or empty.");
        }
    }
}
