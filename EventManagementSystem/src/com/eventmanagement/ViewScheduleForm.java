package com.eventmanagement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewScheduleForm {
    private JFrame frame;
    private JPanel panel;
    private JTextArea scheduleArea;

    public ViewScheduleForm() {
        frame = new JFrame("View Schedule");
        panel = new JPanel();
        scheduleArea = new JTextArea(20, 40);

        panel.add(new JScrollPane(scheduleArea));
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        loadSchedule();
    }

    private void loadSchedule() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EventManagement;user=your_username;password=your_password");
            String query = "SELECT * FROM Events";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String event = "Event: " + resultSet.getString("name") + "\nDate: " + resultSet.getDate("date") +
                        "\nLocation: " + resultSet.getString("location") + "\nDescription: " + resultSet.getString("description") + "\n\n";
                scheduleArea.append(event);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

}
