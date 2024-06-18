package com.eventmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateEventForm {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField locationField;
    private JTextArea descriptionArea;
    private JButton saveButton;

    public CreateEventForm() {
        frame = new JFrame("Create Event");
        panel = new JPanel();
        nameField = new JTextField(20);
        dateField = new JTextField(10);
        locationField = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        saveButton = new JButton("Save Event");

        panel.add(new JLabel("Event Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionArea);
        panel.add(saveButton);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEvent();
            }
        });
    }

    private void saveEvent() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:1433/event_management", "username", "password");
            String query = "INSERT INTO events (name, date, location, description) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, dateField.getText());
            preparedStatement.setString(3, locationField.getText());
            preparedStatement.setString(4, descriptionArea.getText());
            preparedStatement.executeUpdate();
            connection.close();
            JOptionPane.showMessageDialog(frame, "Event Saved Successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }
}
