package com.eventmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ManageAttendeesForm {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton addButton;

    public ManageAttendeesForm() {
        frame = new JFrame("Manage Attendees");
        panel = new JPanel();
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(15);
        addButton = new JButton("Add Attendee");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(addButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttendee();
            }
        });
    }

    private void addAttendee() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EventManagement;user=your_username;password=your_password");
            String query = "INSERT INTO Attendees (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, emailField.getText());
            preparedStatement.setString(3, phoneField.getText());
            preparedStatement.executeUpdate();
            connection.close();
            JOptionPane.showMessageDialog(frame, "Attendee Added Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

}
