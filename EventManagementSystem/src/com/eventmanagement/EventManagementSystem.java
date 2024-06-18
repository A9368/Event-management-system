package com.eventmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private JButton createEventButton;
    private JButton manageAttendeesButton;
    private JButton viewScheduleButton;

    public EventManagementSystem() {
        frame = new JFrame("Event Management System");
        panel = new JPanel();
        createEventButton = new JButton("Create Event");
        manageAttendeesButton = new JButton("Manage Attendees");
        viewScheduleButton = new JButton("View Schedule");

        panel.add(createEventButton);
        panel.add(manageAttendeesButton);
        panel.add(viewScheduleButton);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateEventForm();
            }
        });

        manageAttendeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageAttendeesForm();
            }
        });

        viewScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewScheduleForm();
            }
        });
    }

    public static void main(String[] args) {
        new EventManagementSystem();
    }
}

