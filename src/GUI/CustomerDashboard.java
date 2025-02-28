package GUI;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import models.Customer;
import models.Event;
import models.EventManager;
import models.Ticket;

public class CustomerDashboard extends JFrame {
    private final Customer customer;
    private final JLabel balanceLabel;
    private final JComboBox<String> eventComboBox;
    private final JButton bookTicketButton;

    // เพิ่ม constructor ที่รับ Customer
    public CustomerDashboard(Customer customer) {
        this.customer = customer;

        setTitle("Customer Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome " + customer.getName() + "!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Create a JPanel for Balance display and Event selection dropdown
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Balance display
        this.balanceLabel = new JLabel(String.format("Balance: %.2f USD", customer.getBalance()), JLabel.CENTER);
        centerPanel.add(balanceLabel);

        // Event selection dropdown
        JPanel eventPanel = new JPanel();
        eventPanel.add(new JLabel("Select Event: "));
        eventComboBox = new JComboBox<>();
        refreshEvents(); // เรียกใช้ refreshEvents เพื่อดึงข้อมูลจาก EventManager
        eventPanel.add(eventComboBox);
        centerPanel.add(eventPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Book ticket button
        bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.addActionListener(e -> bookTicket());
        add(bookTicketButton, BorderLayout.SOUTH);

        // สร้าง JPanel สำหรับปุ่มด้านขวา
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // เรียงปุ่มในแนวตั้ง

        // Logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> logOut());
        rightPanel.add(logoutButton);

        // ปุ่ม Refresh เพื่อโหลด Event ใหม่
        JButton refreshButton = new JButton("Refresh Events");
        refreshButton.addActionListener(e -> refreshEvents());
        rightPanel.add(refreshButton);

        // เพิ่ม panel เข้าไปทางขวาของ BorderLayout
        add(rightPanel, BorderLayout.EAST);

        // อัปเดตยอดเงินหลังจากกลับมาที่หน้า CustomerDashboard
        refreshBalance();

        setVisible(true);
    }

    // ฟังก์ชันการจองตั๋ว
    private void bookTicket() {
        int selectedIndex = eventComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            List<Event> eventList = EventManager.getInstance().getAllEvents();
            Event selectedEvent = eventList.get(selectedIndex);
            if (customer.getBalance() >= selectedEvent.getPrice()) {
                customer.setBalance(customer.getBalance() - selectedEvent.getPrice());
                refreshBalance(); // อัปเดตยอดเงินหลังจากทำการจองตั๋ว

                // Save booking
                Ticket ticket = new Ticket(customer, selectedEvent);
                customer.addBookedTicket(ticket); // Add ticket to customer's booked tickets
                selectedEvent.addBookedTicket(ticket); // Add ticket to event's booked tickets

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

    // ฟังก์ชันโหลดข้อมูล Event ใหม่
    private void refreshEvents() {
        eventComboBox.removeAllItems();

        // ดึงข้อมูล Events จาก EventManager
        List<Event> updatedEvents = EventManager.getInstance().getAllEvents();
        for (Event event : updatedEvents) {
            eventComboBox.addItem(event.getName() + " - " + event.getPrice() + " USD");
        }
    }

    // ฟังก์ชันเพื่อรีเฟรชยอดเงิน
    private void refreshBalance() {
        balanceLabel.setText("Balance: " + customer.getBalance() + " USD");
    }
}