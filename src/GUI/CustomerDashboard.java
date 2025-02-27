/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import models.Customer;
import models.Event;
import models.Ticket;

public class CustomerDashboard extends JFrame {
    private Customer customer;
    private JLabel balanceLabel;
    private JComboBox<String> eventComboBox;
    private JButton bookTicketButton;

    // เพิ่ม constructor ที่รับ Customer และ List<Event>
    public CustomerDashboard(Customer customer, List<Event> eventList) {
        this.customer = customer;

        setTitle("Customer Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome " + customer.getName() + "!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Balance display
        balanceLabel = new JLabel("Balance: " + customer.getBalance() + " USD", JLabel.CENTER);
        add(balanceLabel, BorderLayout.CENTER);

        // Event selection dropdown
        JPanel eventPanel = new JPanel();
        eventPanel.add(new JLabel("Select Event: "));
        eventComboBox = new JComboBox<>();
        for (Event event : eventList) {
            eventComboBox.addItem(event.getName() + " - " + event.getPrice() + " USD");
        }
        eventPanel.add(eventComboBox);
        add(eventPanel, BorderLayout.WEST);

        // Book ticket button
        bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.addActionListener(e -> bookTicket(eventList));
        add(bookTicketButton, BorderLayout.SOUTH);

        // Logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> logOut());
        add(logoutButton, BorderLayout.EAST);

        setVisible(true);
    }

    // ฟังก์ชันการจองตั๋ว
    private void bookTicket(List<Event> eventList) {
        int selectedIndex = eventComboBox.getSelectedIndex();
        if (selectedIndex >= 0) { 
            Event selectedEvent = eventList.get(selectedIndex); 
            if (customer.getBalance() >= selectedEvent.getPrice()) { 
                customer.setBalance(customer.getBalance() - selectedEvent.getPrice());
                balanceLabel.setText("Balance: " + customer.getBalance() + " USD");

                // Save booking
                Ticket ticket = new Ticket(customer, selectedEvent);
                JOptionPane.showMessageDialog(this, "Ticket booked successfully for " + selectedEvent.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logOut() {
        dispose(); 
        new LoginFrame(); 
    }
}