package models;

import java.text.NumberFormat;
import java.util.Locale;

public final class Ticket {
    private final Customer customer; // เก็บข้อมูลลูกค้า
    private final Event event; // เก็บข้อมูลเหตุการณ์
    private String eventName; // ประกาศตัวแปร eventName
    private double price;     // ประกาศตัวแปร price

    public Ticket(Customer customer, Event selectedEvent) {
        this.customer = customer;  // เก็บข้อมูลลูกค้า
        this.event = selectedEvent; // เก็บข้อมูลเหตุการณ์
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US); // Change Locale as needed
        return String.format("Ticket for %s, Price: %s", eventName, currencyFormat.format(price));
    }

    public String getEventName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEventName'");
    }
}
