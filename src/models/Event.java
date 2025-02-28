package models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private String date;
    private String venue;
    private int ticketsAvailable;
    private double price;
    private List<Ticket> bookedTickets;

    // Constructor
    public Event(String name, String date, String venue, int ticketsAvailable, double price) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.ticketsAvailable = ticketsAvailable;
        this.price = price;
        this.bookedTickets = new ArrayList<>();
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    // Add booked ticket
    public void addBookedTicket(Ticket ticket) {
        bookedTickets.add(ticket);
        ticketsAvailable--; // Decrease available tickets by 1
    }
}