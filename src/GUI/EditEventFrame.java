package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import models.Event;
import models.EventManager;

public class EditEventFrame extends JFrame {
    private JTextField nameField, dateField, venueField;
    private JSpinner ticketsAvailableSpinner;
    private JFormattedTextField priceField;
    private Event event;
    private int eventIndex;

    public EditEventFrame(Event event, int eventIndex) {
        this.event = event;
        this.eventIndex = eventIndex;

        setTitle("Edit Event");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        // Name
        add(new JLabel("Name: "));
        nameField = new JTextField(event.getName());
        add(nameField);

        // Date
        add(new JLabel("Date: "));
        dateField = new JTextField(event.getDate());
        add(dateField);

        // Venue
        add(new JLabel("Venue: "));
        venueField = new JTextField(event.getVenue());
        add(venueField);

        // Tickets Available
        add(new JLabel("Tickets Available: "));
        ticketsAvailableSpinner = new JSpinner(new SpinnerNumberModel(event.getTicketsAvailable(), 0, Integer.MAX_VALUE, 1));
        add(ticketsAvailableSpinner);

        // Price
        add(new JLabel("Price: "));
        priceField = new JFormattedTextField(NumberFormat.getCurrencyInstance(Locale.US));
        priceField.setValue(event.getPrice());
        add(priceField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEvent();
            }
        });
        add(saveButton);

        setVisible(true);
    }

    private void saveEvent() {
        String name = nameField.getText();
        String date = dateField.getText();
        String venue = venueField.getText();
        int ticketsAvailable = (Integer) ticketsAvailableSpinner.getValue();
        double price = ((Number) priceField.getValue()).doubleValue();

        // Update event data
        Event updatedEvent = new Event(name, date, venue, ticketsAvailable, price);

        // Update event in EventManager
        EventManager.getInstance().updateEvent(eventIndex, updatedEvent);

        JOptionPane.showMessageDialog(this, "Event updated successfully!");
        dispose(); // Close the frame after saving
    }
}