package com.example.project;

import java.time.LocalDateTime;

/**
 * Represents the Ticket of the movie theater.
 * Used to store the information about the ticket.
 */
public class Ticket {

    /** The ticket ID. */
    private String aTicketID;

    /** The date and time of the purchase. */
    private LocalDateTime aPurchaseDateTime;

    /** The showtime of the ticket. */
    private Showtime aForShowtime;

    /**
     * Constructor.
     * @param pTicketID The ticket ID.
     * @param pPurchaseDateTime The date and time of the purchase.
     * @param pForShowtime The showtime of the ticket.
     */
    public Ticket(String pTicketID, LocalDateTime pPurchaseDateTime, Showtime pForShowtime) {
        setTicketID(pTicketID);
        setPurchaseDateTime(pPurchaseDateTime);
        setShowtime(pForShowtime);
    }

    /**
     * Getter of the ticket ID.
     * @return the ticket ID.
     */
    public String getTicketID() {
        return aTicketID;
    }

    /**
     * Setter of the ticket ID.
     * @param pTicketID the ticket ID.
     * @throws IllegalArgumentException if the ticket ID is null or empty.
     */
    public void setTicketID(String pTicketID) {
        if (pTicketID != null && !pTicketID.isEmpty()) {
            this.aTicketID = pTicketID;
        } else {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty.");
        }
    }

    /**
     * Getter of the purchase date and time.
     * @return the purchase date and time.
     */
    public LocalDateTime getPurchaseDateTime() {
        return aPurchaseDateTime;
    }

    /**
     * The setter of the date and time of the purchase.
     * @param pPurchaseDateTime The purchase date and time.
     * @throws IllegalArgumentException if the purchase date and time is null.
     */
    public void setPurchaseDateTime(LocalDateTime pPurchaseDateTime) {
        if (pPurchaseDateTime != null) {
            this.aPurchaseDateTime = pPurchaseDateTime;
        } else {
            throw new IllegalArgumentException("Purchase DateTime cannot be null.");
        }
    }

    /**
     * Getter of the showtime for the ticket.
     * @return the showtime of the ticket.
     */
    public Showtime getForShowtime() {
        return aForShowtime;
    }

    /**
     * Setter of the showtime for the ticket.
     * @param pForShowtime showtime for the ticket.
     * @throws IllegalArgumentException of the showtime is null.
     */
    public void setShowtime(Showtime pForShowtime) {
        if (pForShowtime != null) {
            this.aForShowtime = pForShowtime;
        } else {
            throw new IllegalArgumentException("Showtime cannot be null.");
        }
    }
}
