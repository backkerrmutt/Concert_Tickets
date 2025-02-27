package GUI;

import java.awt.GridLayout;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Anuphong_PC
 */
class TicketBookingFrame extends JFrame {
    private final JComboBox<String> eventComboBox;
    private final JTextField ticketCountField;
    private final JButton bookButton;

    public TicketBookingFrame() {
        setTitle("Ticket Booking");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Select Event:"));
        eventComboBox = new JComboBox<>(new String[] { "Concert 1", "Concert 2" });
        panel.add(eventComboBox);

        panel.add(new JLabel("Number of Tickets:"));
        ticketCountField = new JTextField();
        panel.add(ticketCountField);

        bookButton = new JButton("Book Tickets");
        panel.add(bookButton);

        add(panel);

        setVisible(true);
    }
}