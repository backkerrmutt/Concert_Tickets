package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final ArrayList<Ticket> bookedTickets = new ArrayList<>();
    private double balance;

    public Customer(String username, String password, String name, String lastname, int age, String gender, double balance) {
        super(username, password, name, lastname, age, gender);
        this.balance = balance;
    }


    // Add booked ticket
    public void addBookedTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets.clear();
        this.bookedTickets.addAll(bookedTickets);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}