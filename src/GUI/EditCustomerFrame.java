package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.Customer;

public class EditCustomerFrame extends JFrame {
    private JTextField usernameField, nameField, ageField, genderField, balanceField;
    private Customer customer;

    public EditCustomerFrame(Customer customer) {
        this.customer = customer;

        setTitle("Edit Customer");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        // Username
        add(new JLabel("Username: "));
        usernameField = new JTextField(customer.getUsername());
        usernameField.setEditable(false); // Username cannot be changed
        add(usernameField);

        // Name
        add(new JLabel("Name: "));
        nameField = new JTextField(customer.getName());
        add(nameField);

        // Age
        add(new JLabel("Age: "));
        ageField = new JTextField(String.valueOf(customer.getAge()));
        add(ageField);

        // Gender
        add(new JLabel("Gender: "));
        genderField = new JTextField(customer.getGender());
        add(genderField);

        // Balance
        add(new JLabel("Balance: "));
        balanceField = new JTextField(String.valueOf(customer.getBalance()));
        add(balanceField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCustomer();
            }
        });
        add(saveButton);

        setVisible(true);
    }

    private void saveCustomer() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        double balance = Double.parseDouble(balanceField.getText());

        // Update customer data
        customer.setName(name);
        customer.setAge(age);
        customer.setGender(gender);
        customer.setBalance(balance);

        // Update user in LoginFrame.users
        LoginFrame.users.put(customer.getUsername(), customer);

        JOptionPane.showMessageDialog(this, "Customer updated successfully!");
        dispose(); // Close the frame after saving
    }
}