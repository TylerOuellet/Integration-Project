package com.example.project;


/**
 * Represents the screening room in the movie theater.
 * Used to store information about the screening room.
 */
public class ScreeningRoom implements ManagementCollection {

    /** The remaining seats in the screening room. */
    private int aRemainingSeats;

    /** The room ID of the screening room. */
    private String aRoomID;

    /**
     * Constructor
     * @param pRemainingSeats The remaining seats.
     * @param pRoomID The room ID.
     */
    public ScreeningRoom(int pRemainingSeats, String pRoomID) {
        setRemainingSeats(pRemainingSeats);
        setRoomID(pRoomID);
    }

    /**
     * Getter of the Remaining seats.
     * @return the remaining seats.
     */
    public int getRemainingSeats() {
        return aRemainingSeats;
    }

    /**
     * Setter of the remaining seats.
     * @param pRemainingSeats the remaining seats.
     * @throws IllegalArgumentException if the remaining seats is a negative number.
     */
    public void setRemainingSeats(int pRemainingSeats) {
        if (pRemainingSeats >= 0) {
            this.aRemainingSeats = pRemainingSeats;
        } else {
            throw new IllegalArgumentException("The remaining seats cannot be negative.");
        }
    }

    /**
     * Getter of the room ID.
     * @return the room ID.
     */
    public String getRoomID() {
        return aRoomID;
    }

    /**
     * Setter of the room ID.
     * @param pRoomID the room ID.
     * @throws IllegalArgumentException if the room ID is null or empty.
     */
    public void setRoomID(String pRoomID) {
        if (pRoomID != null && !pRoomID.isEmpty()) {
            this.aRoomID = pRoomID;
        } else {
            throw new IllegalArgumentException("The Room ID cannot be null or empty.");
        }
    }
}
