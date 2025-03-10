package models;

public class Event {
    private String name;
    private String date;
    private String venue;
    private int initialTickets; // จำนวนตั๋วตอนแรก
    private int ticketsAvailable;
    private double price;

    // Constructor
    public Event(String name, String date, String venue, int ticketsAvailable, double price) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.initialTickets = ticketsAvailable; // เก็บจำนวนตั๋วตอนแรก
        this.ticketsAvailable = ticketsAvailable;
        this.price = price;
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

    public int getInitialTickets() { // Getter สำหรับจำนวนตั๋วตอนแรก
        return initialTickets;
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

    public void addBookedTicket(Ticket ticket) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}