package com.example.project;

import java.time.LocalDateTime;

public class Ticket {
    private String aTicketID;

    private LocalDateTime aPurchaseDateTime;

    private Showtime aForShowtime;

    public Ticket(String pTicketID, LocalDateTime pPurchaseDateTime, Showtime pForShowtime) {
        setTicketID(pTicketID);
        setPurchaseDateTime(pPurchaseDateTime);
        setShowtime(pForShowtime);
    }

    public String getTicketID() {
        return aTicketID;
    }

    public void setTicketID(String pTicketID) {
        if (pTicketID != null && !pTicketID.isEmpty()) {
            this.aTicketID = pTicketID;
        } else {
            throw new IllegalArgumentException("Ticket ID cannot be null or empty.");
        }
    }

    public LocalDateTime getPurchaseDateTime() {
        return aPurchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime pPurchaseDateTime) {
        if (pPurchaseDateTime != null) {
            this.aPurchaseDateTime = pPurchaseDateTime;
        } else {
            throw new IllegalArgumentException("Purchase DateTime cannot be null.");
        }
    }

    public Showtime getForShowtime() {
        return aForShowtime;
    }

    public void setShowtime(Showtime pForShowtime) {
        if (pForShowtime != null) {
            this.aForShowtime = pForShowtime;
        } else {
            throw new IllegalArgumentException("Showtime cannot be null.");
        }
    }
}
