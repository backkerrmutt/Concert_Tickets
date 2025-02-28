package GUI;

import java.awt.BorderLayout;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Customer;
import models.User;

public class CustomerManagementFrame extends JFrame {
    private final JTable customerTable;
    private final JButton addCustomerButton, editCustomerButton, deleteCustomerButton;
    private final DefaultTableModel customerTableModel;

    public CustomerManagementFrame() {
        setTitle("Customer Management");
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Column names
        String[] columnNames = {"Username", "Name", "Age", "Gender", "Balance"};
        customerTableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(customerTableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();
        addCustomerButton = new JButton("Add Customer");
        editCustomerButton = new JButton("Edit Customer");
        deleteCustomerButton = new JButton("Delete Customer");

        panel.add(addCustomerButton);
        panel.add(editCustomerButton);
        panel.add(deleteCustomerButton);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);

        // Load customer data
        loadCustomers();

        // Action Listeners
        addCustomerButton.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            registerFrame.setVisible(true);
            registerFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    loadCustomers(); // Refresh table after adding a new customer
                }
            });
        });

        editCustomerButton.addActionListener(e -> editCustomer());
        deleteCustomerButton.addActionListener(e -> deleteCustomer());
    }

    private void loadCustomers() {
        customerTableModel.setRowCount(0); // Clear table
        for (Entry<String, User> entry : LoginFrame.users.entrySet()) {
            if (entry.getValue() instanceof Customer) {
                Customer customer = (Customer) entry.getValue();
                customerTableModel.addRow(new Object[]{
                    customer.getUsername(),
                    customer.getName(),
                    customer.getAge(),
                    customer.getGender(),
                    customer.getBalance()
                });
            }
        }
    }

    private void editCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) {
            String username = (String) customerTable.getValueAt(selectedRow, 0);
            Customer customer = (Customer) LoginFrame.users.get(username);
            EditCustomerFrame editFrame = new EditCustomerFrame(customer);
            editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            editFrame.setVisible(true);
            editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    loadCustomers(); // Refresh table after editing a customer
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit!");
        }
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) {
            String username = (String) customerTable.getValueAt(selectedRow, 0);
            LoginFrame.users.remove(username);
            customerTableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Deleted customer: " + username);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete!");
        }
    }
}