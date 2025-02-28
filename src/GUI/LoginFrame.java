package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import models.Admin;
import models.Customer;
import models.Event; //
import models.User;
/**
 *
 * @author Anuphong_PC
 */

public class LoginFrame extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton, registerButton;
    static public  Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new Admin("admin", "admin", "Admin", "User", 35, "Male"));
        users.put("1", new Customer("1", "1", "1", "1", 1, "male", 1000));
    }

    public LoginFrame() {
        setTitle("Concert Ticket System - Login");
        setSize(400, 600);

        // Get screen size to calculate position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Set the position to the right side of the screen, centered vertically with
        // some padding
        int x = screenWidth - 400 - 10; // 10 px padding from the right
        int y = (screenHeight - 600) / 2; // Vertically centered with padding

        setLocation(x, y); // Se

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);

        loginButton.addActionListener(e -> authenticateUser());
        registerButton.addActionListener(e -> new RegisterFrame());
    }

    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        User user = users.get(username);

        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome " + user.getName() + ".");

            SwingUtilities.invokeLater(() -> {
                if (user instanceof Admin) {
                    new AdminDashboard();
                } else if (user instanceof Customer) {
                    // สร้างรายการอีเวนต์จำลอง
                    List<Event> eventList = new ArrayList<>();
                    new CustomerDashboard((Customer) user); // ส่ง Customer และ event list
                }
                dispose(); // ปิดหน้าต่าง login หลังจากเปิดหน้าต่างใหม่
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
