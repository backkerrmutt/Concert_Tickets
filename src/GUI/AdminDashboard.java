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
class AdminDashboard extends JFrame {
    /**
     * 
     */
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1)); // Create a simple layout for buttons

        // Add buttons for various actions
        JButton manageEventsButton = new JButton("Manage Events");
        JButton manageCustomersButton = new JButton("Manage Customers");
        JButton viewReportsButton = new JButton("View Reports");

        // Add listeners to buttons
        manageEventsButton.addActionListener(e -> new ManageEventsFrame());
        manageCustomersButton.addActionListener(e -> new CustomerManagementFrame());
        viewReportsButton.addActionListener(e -> new ReportsFrame());

        // Add buttons to frame
        add(manageEventsButton);
        add(manageCustomersButton);
        add(viewReportsButton);

        // Add logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> logOut());
        add(logoutButton);

        setVisible(true);
    }

    private void logOut() {
        dispose(); // Close the current dashboard
        new LoginFrame(); // Open login screen
    }
}

