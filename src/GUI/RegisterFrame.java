package GUI;

import java.awt.*;
import javax.swing.*;
import models.Customer;

public class RegisterFrame extends JFrame {
    private final JTextField usernameField, nameField, lastnameField, ageField, genderField, balanceField;
    private final JPasswordField passwordField;
    private final JButton registerButton;

    public RegisterFrame() {
        setTitle("Register New Customer");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel[] labels = {
            new JLabel("Username:"), new JLabel("Password:"),
            new JLabel("Name:"), new JLabel("Lastname:"),
            new JLabel("Age:"), new JLabel("Gender:"), new JLabel("Balance:")
        };

        JTextField[] textFields = {
            usernameField = new JTextField(),
            passwordField = new JPasswordField(),
            nameField = new JTextField(),
            lastnameField = new JTextField(),
            ageField = new JTextField(),
            genderField = new JTextField(),
            balanceField = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            panel.add(textFields[i], gbc);
        }

        registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        add(panel);
        setVisible(true);

        registerButton.addActionListener(e -> registerCustomer());
    }

    private void registerCustomer() {
        try {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String name = nameField.getText().trim();
            String lastname = lastnameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderField.getText().trim();
            double balance = Double.parseDouble(balanceField.getText().trim());

            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || lastname.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!LoginFrame.users.containsKey(username)) {
                Customer newCustomer = new Customer(username, password, name, lastname, age, gender, balance);
                LoginFrame.users.put(username, newCustomer);
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for age and balance!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}