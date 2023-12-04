package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieSelectionController {

    @FXML
    Button buyTicketsButton;

    @FXML
    Button closeButton;

    @FXML
    TextField numberOfTicketsTextfield;

    @FXML
    ListView<String> moviesListView;

    @FXML
    ListView<Showtime> showtimesListView;

    private MovieManager movieManager;

    /**
     * When the Controller opens
     */
    public void initialize() {
        movieManager = new MovieManager();
        moviesListView.getItems().addAll(movieManager.getAllMovieTitles());
        moviesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateShowTimesListView(newValue);
        });
    }

    /**
     * Updates the showtime listview when selecting a movie.
     * @param selectedMovie The title of the selected movie.
     */
    private void updateShowTimesListView(String selectedMovie) {
        showtimesListView.getItems().clear();
        List<Showtime> showTimesForSelectedMovie = movieManager.getShowTimesForMovie(selectedMovie);
        showtimesListView.getItems().addAll(showTimesForSelectedMovie);
    }

    /**
     * Closing the controller
     */
    public void closeButton() {

    }

    /**
     * Button to buy ticket(s).
     */
    public void buyTicketsButton() {
        try {
            int quantity = Integer.parseInt(numberOfTicketsTextfield.getText());
            String selectedMovie = moviesListView.getSelectionModel().getSelectedItem();
            Showtime selectedShowtime = showtimesListView.getSelectionModel().getSelectedItem();

            if (selectedMovie == null || selectedShowtime == null) {
                showSelectionErrorDialog();
            } else {
                showPurchaseTicketsDialog(quantity, selectedMovie, selectedShowtime);
            }
        } catch (NumberFormatException e) {
            showErrorDialog();
        }
    }

    /**
     * Displaying and formatting the information of the ticket(s) bought.
     * @param quantity The quantity of ticket(s) bought by the client.
     */
    void showPurchaseTicketsDialog(int quantity, String selectedMovie, Showtime selectedShowtime) {
        List<Ticket> purchasedTickets = ticketsGenerator(quantity, selectedShowtime);

        StringBuilder message = new StringBuilder("Thank you for purchasing " + quantity + " ticket(s) for " + selectedMovie + "!\n\n");
        for (Ticket ticket : purchasedTickets) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = ticket.getPurchaseDateTime().format(timeFormatter);

            message.append("Ticket ID: ").append(ticket.getTicketID())
                    .append("\nPurchase Time: ").append(formattedTime)
                    .append("\nShowtime: ").append(ticket.getForShowtime().getShowTime().format(timeFormatter))
                    .append("\nScreening Room: ").append(ticket.getForShowtime().getScreeningRoom().getRoomID()) // Add screening room information
                    .append("\n\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ticket Purchase");
        alert.setHeaderText(null);
        alert.setContentText(message.toString());
        alert.showAndWait();
        numberOfTicketsTextfield.setText("");
    }

    /**
     * Generating the ticket(s)
     * @param quantity The quantity of tickets bought by the client.
     * @return Returning the tickets.
     */
    private List<Ticket> ticketsGenerator(int quantity, Showtime selectedShowtime) {
        List<Ticket> tickets = new ArrayList<>();

        if (selectedShowtime != null) {
            for (int i = 0; i < quantity; i++) {
                String ticketID = generateTicketID();
                LocalDateTime purchaseDateTime = LocalDateTime.now();

                Ticket ticket = new Ticket(ticketID, purchaseDateTime, selectedShowtime);
                tickets.add(ticket);
            }
        }

        return tickets;
    }


    /**
     * Generating the ticket(s) ID.
     * @return Returning the ticket(s) ID.
     */
    private String generateTicketID() {
        int randomNumber = new Random().nextInt(90000) + 10000;
        return "TICKET-" + randomNumber;
    }

    /**
     * Error message in case the client entered an invalid ticket number.
     */
    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("The number of tickets you entered is not valid.");
        alert.showAndWait();
        numberOfTicketsTextfield.setText("");
    }

    /**
     * Error message in case client did not select a movie or a showtime.
     */
    private void showSelectionErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select both a movie and a showtime before buying tickets.");
        alert.showAndWait();
        numberOfTicketsTextfield.setText("");
    }
}

