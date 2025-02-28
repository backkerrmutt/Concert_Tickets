package GUI;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Event;
import models.EventManager;

class ManageEventsFrame extends JFrame {
    private final JTable eventsTable;
    private final DefaultTableModel eventsTableModel;
    private final JButton addButton, editButton, deleteButton;
    //private final ArrayList<Event> eventsList; // ไม่จำเป็นต้องมี เพราะเราใช้ EventManager

    public ManageEventsFrame() {
        // ใช้ EventManager แทน ArrayList ธรรมดา
        EventManager eventManager = EventManager.getInstance();

        // เพิ่มตัวอย่าง Event ถ้ายังไม่มี
        if (eventManager.getAllEvents().isEmpty()) {
            eventManager.addEvent(new Event("Rock Night", "2025-06-15", "Stadium A", 5000, 49.99));
        }

        setTitle("Manage Concert Events");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ปิดหน้าต่างโดยไม่หยุดโปรแกรมหลัก

        // สร้างตาราง
        String[] columnNames = {"Name", "Date", "Venue", "Tickets", "Price"};
        eventsTableModel = new DefaultTableModel(columnNames, 0);
        eventsTable = new JTable(eventsTableModel);

        // ปุ่มควบคุม
        addButton = new JButton("Add Event");
        editButton = new JButton("Edit Event");
        deleteButton = new JButton("Delete Event");

        // Panel สำหรับปุ่ม
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // เพิ่ม Listener ให้ปุ่ม
        addButton.addActionListener(e -> addEvent());
        editButton.addActionListener(e -> editEvent()); // เชื่อมโยงกับฟังก์ชัน editEvent()
        deleteButton.addActionListener(e -> deleteEvent());

        // Layout
        add(new JScrollPane(eventsTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // อัปเดตตารางให้ดึงข้อมูลล่าสุดจาก EventManager
        updateTable();

        setVisible(true);
    }

    private void editEvent() {
        int selectedRow = eventsTable.getSelectedRow();
        if (selectedRow != -1) {
            Event event = EventManager.getInstance().getAllEvents().get(selectedRow);
            EditEventFrame editFrame = new EditEventFrame(event, selectedRow);
            editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editFrame.setVisible(true);
            editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    updateTable(); // Refresh table after editing an event
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to edit.");
        }
    }

    private void addEvent() {
        String eventName = JOptionPane.showInputDialog(this, "Enter event name:");
        String eventDate = JOptionPane.showInputDialog(this, "Enter event date (yyyy-mm-dd):");
        String eventVenue = JOptionPane.showInputDialog(this, "Enter event venue:");
        String ticketsAvailableStr = JOptionPane.showInputDialog(this, "Enter tickets available:");
        String eventPriceStr = JOptionPane.showInputDialog(this, "Enter event price:");

        try {
            int ticketsAvailable = Integer.parseInt(ticketsAvailableStr);
            double eventPrice = Double.parseDouble(eventPriceStr);

            Event newEvent = new Event(eventName, eventDate, eventVenue, ticketsAvailable, eventPrice);
            EventManager.getInstance().addEvent(newEvent);

            updateTable();
            JOptionPane.showMessageDialog(this, "Event added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number for tickets available or price.");
        }
    }

    private void deleteEvent() {
        int selectedRow = eventsTable.getSelectedRow();
        if (selectedRow != -1) {
            EventManager.getInstance().removeEvent(selectedRow);
            updateTable();
            JOptionPane.showMessageDialog(this, "Event deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete.");
        }
    }

    private void updateTable() {
        eventsTableModel.setRowCount(0);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        for (Event event : EventManager.getInstance().getAllEvents()) {
            eventsTableModel.addRow(new Object[]{
                    event.getName(),
                    event.getDate(),
                    event.getVenue(),
                    event.getTicketsAvailable(),
                    currencyFormat.format(event.getPrice())
            });
        }
    }
}