package models;

import java.text.NumberFormat;
import java.util.Locale;

public class Ticket {
    private Customer customer; // เก็บข้อมูลลูกค้า
    private Event event; // เก็บข้อมูลเหตุการณ์

    public Ticket(Customer customer, Event selectedEvent) {
        this.customer = customer;  // เก็บข้อมูลลูกค้า
        this.event = selectedEvent; // เก็บข้อมูลเหตุการณ์
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); // Change Locale as needed
        return String.format("Ticket for %s, Price: %s", event.getName(), currencyFormat.format(event.getPrice()));
    }

    public String getEventName() {
        return event.getName();
    }

    public double getPrice() {
        return event.getPrice();
    }
}