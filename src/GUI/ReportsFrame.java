package GUI;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Customer;
import models.Event;
import models.EventManager;
import models.Ticket;
import models.User;

public class ReportsFrame extends JFrame {
    private final JTable reportTable;
    private final DefaultTableModel reportTableModel;

    public ReportsFrame() {
        setTitle("Concert Reports");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allows closing without exiting the app

        String[] columnNames = {"Event Name", "Booked Tickets", "Total Tickets", "Remaining Tickets"};

        // Generate report data dynamically
        String[][] data = generateReportData();
        if (data.length == 0) {
            JOptionPane.showMessageDialog(this, "No tickets booked for any events.", "No Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        reportTableModel = new DefaultTableModel(data, columnNames);
        reportTable = new JTable(reportTableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        add(scrollPane, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> setVisible(true)); // Ensure UI thread safety
    }

    private String[][] generateReportData() {
        List<Event> events = EventManager.getInstance().getAllEvents();
        HashMap<String, Integer> eventBookings = new HashMap<>();
        HashMap<String, Integer> eventTotalTickets = new HashMap<>();

        // Collect total tickets available for each event
        for (Event event : events) {
            eventTotalTickets.put(event.getName(), event.getTicketsAvailable());
        }

        // Collect booking data from all customers
        for (User user : LoginFrame.users.values()) {
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                for (Ticket ticket : customer.getBookedTickets()) {
                    String eventName = ticket.getEventName();
                    eventBookings.put(eventName, eventBookings.getOrDefault(eventName, 0) + 1);
                }
            }
        }

        // Prepare report data
        String[][] reportData = new String[eventBookings.size()][4];
        int row = 0;
        for (Map.Entry<String, Integer> entry : eventBookings.entrySet()) {
            String eventName = entry.getKey();
            int bookedTickets = entry.getValue();
            int totalTickets = eventTotalTickets.getOrDefault(eventName, 0);
            int remainingTickets = totalTickets - bookedTickets;

            reportData[row][0] = eventName;
            reportData[row][1] = String.valueOf(bookedTickets);
            reportData[row][2] = String.valueOf(totalTickets);
            reportData[row][3] = String.valueOf(remainingTickets);

            row++;
        }

        return reportData;
    }
}