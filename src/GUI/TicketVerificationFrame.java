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

class TicketVerificationFrame extends JFrame {
    private final JTextField ticketIDField;
    private final JButton verifyButton;

    public TicketVerificationFrame() {
        setTitle("Ticket Verification");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Enter Ticket ID:"));
        ticketIDField = new JTextField();
        panel.add(ticketIDField);

        verifyButton = new JButton("Verify Ticket");
        panel.add(verifyButton);

        add(panel);

        setVisible(true);
    }
}
