package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Event;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Anuphong_PC
 */
class ManageEventsFrame extends JFrame {
    private final JTable eventsTable;
    private final DefaultTableModel eventsTableModel;
    private final JButton addButton, editButton, deleteButton;
    private final ArrayList<Event> eventsList;

    public ManageEventsFrame() {
        eventsList = new ArrayList<>();

        // Sample data
        eventsList.add(new Event("Concert 1", "2025-03-01", "Arena 1", 1000, 50.00)); // Sample price added
        eventsList.add(new Event("Concert 2", "2025-04-01", "Arena 2", 500, 30.00)); // Sample price added

        setTitle("Manage Concert Events");
        setSize(600, 400); // Increased size for new column
        setLocationRelativeTo(null);

        // Column names for the events table, including price column
        String[] columnNames = { "Event Name", "Date", "Venue", "Tickets Available", "Price" };

        // Data for the events table
        String[][] data = new String[eventsList.size()][5]; // Adjusted for new column
        for (int i = 0; i < eventsList.size(); i++) {
            data[i][0] = eventsList.get(i).getName();
            data[i][1] = eventsList.get(i).getDate();
            data[i][2] = eventsList.get(i).getVenue();
            data[i][3] = String.valueOf(eventsList.get(i).getTicketsAvailable());
            data[i][4] = eventsList.get(i).getTicketPrice(); // Price added here
        }

        eventsTableModel = new DefaultTableModel(data, columnNames);
        eventsTable = new JTable(eventsTableModel);
        JScrollPane scrollPane = new JScrollPane(eventsTable);

        add(scrollPane, BorderLayout.CENTER);

        // Buttons for adding, editing, deleting events
        JPanel panel = new JPanel();
        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);

        // Action listeners for buttons
        addButton.addActionListener(e -> addEvent());
        editButton.addActionListener(e -> editEvent());
        deleteButton.addActionListener(e -> deleteEvent());
    }

    private void addEvent() {
        String eventName = JOptionPane.showInputDialog(this, "Enter event name:");
        String eventDate = JOptionPane.showInputDialog(this, "Enter event date (yyyy-mm-dd):");
        String eventVenue = JOptionPane.showInputDialog(this, "Enter event venue:");
        String ticketsAvailableStr = JOptionPane.showInputDialog(this, "Enter tickets available:");
        String eventPriceStr = JOptionPane.showInputDialog(this, "Enter event price:");

        try {
            int ticketsAvailable = Integer.parseInt(ticketsAvailableStr);
            double eventPrice = Double.parseDouble(eventPriceStr); // Parse price
            Event newEvent = new Event(eventName, eventDate, eventVenue, ticketsAvailable, eventPrice); // Pass price to Event constructor
            eventsList.add(newEvent);
            updateTable();
            JOptionPane.showMessageDialog(this, "Event added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number for tickets available or price.");
        }
    }

    private void editEvent() {
        int selectedRow = eventsTable.getSelectedRow();
        if (selectedRow != -1) {
            Event selectedEvent = eventsList.get(selectedRow);
            String newName = JOptionPane.showInputDialog(this, "Enter new event name:", selectedEvent.getName());
            String newDate = JOptionPane.showInputDialog(this, "Enter new event date (yyyy-mm-dd):", selectedEvent.getDate());
            String newVenue = JOptionPane.showInputDialog(this, "Enter new event venue:", selectedEvent.getVenue());
            String newTicketsStr = JOptionPane.showInputDialog(this, "Enter new number of tickets:", selectedEvent.getTicketsAvailable());
            String newPriceStr = JOptionPane.showInputDialog(this, "Enter new event price:", selectedEvent.getPrice()); // New price field

            try {
                int newTickets = Integer.parseInt(newTicketsStr);
                double newPrice = Double.parseDouble(newPriceStr); // Parse price
                selectedEvent.setName(newName);
                selectedEvent.setDate(newDate);
                selectedEvent.setVenue(newVenue);
                selectedEvent.setTicketsAvailable(newTickets);
                selectedEvent.setPrice(newPrice); // Update price
                updateTable();
                JOptionPane.showMessageDialog(this, "Event edited successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number for tickets or price.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to edit.");
        }
    }

    private void deleteEvent() {
        int selectedRow = eventsTable.getSelectedRow();
        if (selectedRow != -1) {
            eventsList.remove(selectedRow);
            updateTable();
            JOptionPane.showMessageDialog(this, "Event deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete.");
        }
    }

    private void updateTable() {
        // Update the table model with the current events list
        eventsTableModel.setRowCount(0); // Clear the existing rows
        for (Event event : eventsList) {
            eventsTableModel.addRow(
                    new Object[] { event.getName(), event.getDate(), event.getVenue(), event.getTicketsAvailable(), event.getTicketPrice() }); // Add price column data
        }
    }
}
















// class ManageEventsFrame extends JFrame {
//     private final JTable eventsTable;
//     private final DefaultTableModel eventsTableModel;
//     private final JButton addButton, editButton, deleteButton;
//     private final ArrayList<Event> eventsList;

//     public ManageEventsFrame() {
//         eventsList = new ArrayList<>();

//         // Sample data
//         eventsList.add(new Event("Concert 1", "2025-03-01", "Arena 1", 1000));
//         eventsList.add(new Event("Concert 2", "2025-04-01", "Arena 2", 500));

//         setTitle("Manage Concert Events");
//         setSize(500, 400);
//         setLocationRelativeTo(null);

//         // Column names for the events table
//         String[] columnNames = { "Event Name", "Date", "Venue", "Tickets Available" };

//         // Data for the events table
//         String[][] data = new String[eventsList.size()][4];
//         for (int i = 0; i < eventsList.size(); i++) {
//             data[i][0] = eventsList.get(i).getName();
//             data[i][1] = eventsList.get(i).getDate();
//             data[i][2] = eventsList.get(i).getVenue();
//             data[i][3] = String.valueOf(eventsList.get(i).getTicketsAvailable());
//         }

//         eventsTableModel = new DefaultTableModel(data, columnNames);
//         eventsTable = new JTable(eventsTableModel);
//         JScrollPane scrollPane = new JScrollPane(eventsTable);

//         add(scrollPane, BorderLayout.CENTER);

//         // Buttons for adding, editing, deleting events
//         JPanel panel = new JPanel();
//         addButton = new JButton("Add Event");
//         editButton = new JButton("Edit Event");
//         deleteButton = new JButton("Delete Event");

//         panel.add(addButton);
//         panel.add(editButton);
//         panel.add(deleteButton);
//         add(panel, BorderLayout.SOUTH);

//         setVisible(true);

//         // Action listeners for buttons
//         addButton.addActionListener(e -> addEvent());
//         editButton.addActionListener(e -> editEvent());
//         deleteButton.addActionListener(e -> deleteEvent());
//     }

//     private void addEvent() {
//         String eventName = JOptionPane.showInputDialog(this, "Enter event name:");
//         String eventDate = JOptionPane.showInputDialog(this, "Enter event date (yyyy-mm-dd):");
//         String eventVenue = JOptionPane.showInputDialog(this, "Enter event venue:");
//         String ticketsAvailableStr = JOptionPane.showInputDialog(this, "Enter tickets available:");

//         try {
//             int ticketsAvailable = Integer.parseInt(ticketsAvailableStr);
//             Event newEvent = new Event(eventName, eventDate, eventVenue, ticketsAvailable);
//             eventsList.add(newEvent);
//             updateTable();
//             JOptionPane.showMessageDialog(this, "Event added successfully!");
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Invalid number for tickets available.");
//         }
//     }

//     private void editEvent() {
//         int selectedRow = eventsTable.getSelectedRow();
//         if (selectedRow != -1) {
//             Event selectedEvent = eventsList.get(selectedRow);
//             String newName = JOptionPane.showInputDialog(this, "Enter new event name:", selectedEvent.getName());
//             String newDate = JOptionPane.showInputDialog(this, "Enter new event date (yyyy-mm-dd):",
//                     selectedEvent.getDate());
//             String newVenue = JOptionPane.showInputDialog(this, "Enter new event venue:", selectedEvent.getVenue());
//             String newTicketsStr = JOptionPane.showInputDialog(this, "Enter new number of tickets:",
//                     selectedEvent.getTicketsAvailable());

//             try {
//                 int newTickets = Integer.parseInt(newTicketsStr);
//                 selectedEvent.setName(newName);
//                 selectedEvent.setDate(newDate);
//                 selectedEvent.setVenue(newVenue);
//                 selectedEvent.setTicketsAvailable(newTickets);
//                 updateTable();
//                 JOptionPane.showMessageDialog(this, "Event edited successfully!");
//             } catch (NumberFormatException e) {
//                 JOptionPane.showMessageDialog(this, "Invalid number for tickets.");
//             }
//         } else {
//             JOptionPane.showMessageDialog(this, "Please select an event to edit.");
//         }
//     }

//     private void deleteEvent() {
//         int selectedRow = eventsTable.getSelectedRow();
//         if (selectedRow != -1) {
//             eventsList.remove(selectedRow);
//             updateTable();
//             JOptionPane.showMessageDialog(this, "Event deleted successfully!");
//         } else {
//             JOptionPane.showMessageDialog(this, "Please select an event to delete.");
//         }
//     }

//     private void updateTable() {
//         // Update the table model with the current events list
//         eventsTableModel.setRowCount(0); // Clear the existing rows
//         for (Event event : eventsList) {
//             eventsTableModel.addRow(
//                     new Object[] { event.getName(), event.getDate(), event.getVenue(), event.getTicketsAvailable() });
//         }
//     }
// }