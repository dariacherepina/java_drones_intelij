package GUI;

import Drone.Convert;
import Drone.DroneDynamics;
import Drone.Drones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import Exception.*;

public class DroneIDActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> dronesList;
    private Convert helper;
    private int userNumber;
    private JPanel panel;
    private JTextArea droneInfoTextArea2;
    private Drones droneInfo;

    public DroneIDActionListener(MyFrame frame, ArrayList<Drones> dronesList) {
        this.frame = frame;
        this.dronesList = dronesList;
        this.helper = new Convert();
    }

    /**
     * display individual drone information by asking the user for an id
     * give error when user enters wrong id
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String droneIdString = JOptionPane.showInputDialog("Enter Drone ID: (71-95)"); // Use input dialog to get drone ID from the user
        if (droneIdString == null || droneIdString.trim().isEmpty()) { // Check if the user clicked Cancel or entered an empty value
            return; // Exit if no input or canceled
        }

        int droneId = Integer.parseInt(droneIdString); // Convert the input to an integer
        if (droneId < 71 || droneId > 95) {
            JFrame textFrame1 = new JFrame("ATTENTION!");
            textFrame1.setSize(350, 90);
            textFrame1.setLocationRelativeTo(null);
            // Create a JTextArea to display the information
            JTextArea droneInfoTextArea1 = new JTextArea();
            droneInfoTextArea1.setEditable(false);
            String infoText1 = "             !!Please enter a Drone ID between 71 and 95!!"; // Add the information to the JTextArea
            droneInfoTextArea1.setForeground(Color.RED);
            droneInfoTextArea1.setText(infoText1);
            textFrame1.add(new JScrollPane(droneInfoTextArea1)); // Add the JTextArea to the textFrame
            textFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // close frame when user clicks on x
            textFrame1.setVisible(true);
            textFrame1.setResizable(false);
            return; // Exit if no input or canceled
        }

        try { // Use helper method to get information for the specific drone ID
            droneInfo = helper.findDrone(dronesList, droneId);
        } catch (InvalidIdInput ex) {
            throw new RuntimeException(ex);
        }

        JFrame textFrame = new JFrame("Drone Information"); // Create a new JFrame for displaying text
        textFrame.setSize(950, 350); // Set the size to match the JTable and JScrollPane dimensions
        textFrame.setLocationRelativeTo(null);
        textFrame.setResizable(false);

        JTextArea droneInfoTextArea = new JTextArea(); // Create a JTextArea to display the information
        droneInfoTextArea.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        String infoText =         // Add the information to the JTextArea
                "Drone " + droneInfo.getId() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() +
                        "\nCarriage Weight: " + droneInfo.getCarriageWeight() +
                        "\nCarriage Type " + droneInfo.getCarriageType() +
                        "\n\nDroneType:" + droneInfo.getDroneType().toString();
        droneInfoTextArea.setText(infoText);
        panel.add(droneInfoTextArea);

        JTextField userInputField = new JTextField(); // Create a JTextField for user input
        userInputField.setPreferredSize(new Dimension(150, 30));

        JButton submitButton = new JButton("Submit"); // Create a JButton to submit the input
        droneInfoTextArea2 = new JTextArea();
        droneInfoTextArea2.setEditable(false);

        submitButton.addActionListener(new ActionListener() {
            /**
             *check valid user input to display the Drone Dynamics
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText();

                try {   // Check if the user entered a number

                    userNumber = Integer.parseInt(userInput);
                } catch (NumberFormatException ex) {
                    // Handle the case where the user did not enter a valid number
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                String infoText2 = droneInfo.getDroneDynamicsList().get(userNumber).toString();
                droneInfoTextArea2.setText(infoText2);
                panel.add(droneInfoTextArea2);
                panel.add(frame.getReturnPlus5Button());
                panel.add(frame.getReturnMinus5Button());

                textFrame.revalidate();
                textFrame.repaint();
            }
        });
        textFrame.add(panel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();// Create a JPanel to hold the input components
        inputPanel.add(new JLabel("Please enter a number between 1 and 1441 for a specific DroneDynamic of the Drone:\n"));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);
        textFrame.add(inputPanel, BorderLayout.SOUTH);// Add the inputPanel to the textFrame

        frame.getReturnMinus5Button().addActionListener(new ActionListener() {
            /**
             * subtract 5 minutes from the timestamp
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNumber <= 1441 && userNumber >= 4) {  //area userNumber from 5 to 1441
                    String nextTimeStempMinus = getDroneDynamicMinus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
                    String infoTextDroneDynamics = Objects.requireNonNull(findDroneDyn5min(nextTimeStempMinus, droneInfo)).toString();
                    System.out.println(infoTextDroneDynamics);
                    droneInfoTextArea2.setText(infoTextDroneDynamics);
                    panel.removeAll();

                    panel.add(droneInfoTextArea);
                    panel.add(droneInfoTextArea2);
                    panel.add(frame.getReturnPlus5Button());
                    panel.add(frame.getReturnMinus5Button());

                    panel.setVisible(true);
                    textFrame.revalidate();
                    textFrame.repaint();
                    userNumber = userNumber - 5;
                    if (userNumber <= 1437 && userNumber >= 0) {
                        frame.getReturnPlus5Button().setBackground(Color.WHITE);
                    }
                } else {
                    frame.getReturnMinus5Button().setBackground(Color.GRAY);  //button grey -> it doesn't work because id is out of range for this button

                }
            }
        });

        frame.getReturnPlus5Button().addActionListener(new ActionListener() {
            /**
             * add 5 minutes to the timestamp
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNumber <= 1437 && userNumber >= 0) { // area userNumber from 5 to 1441
                    frame.getReturnPlus5Button().setBackground(Color.WHITE);
                    String nextTimeStempPlus = getDroneDynamicPlus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
                    String infoTextDroneDynamics1 = Objects.requireNonNull(findDroneDyn5min(nextTimeStempPlus, droneInfo)).toString();
                    System.out.println(infoTextDroneDynamics1);
                    droneInfoTextArea2.setText(infoTextDroneDynamics1);
                    panel.removeAll();

                    panel.add(droneInfoTextArea);
                    panel.add(droneInfoTextArea2);
                    panel.add(frame.getReturnPlus5Button());
                    panel.add(frame.getReturnMinus5Button());

                    panel.setVisible(true);
                    textFrame.revalidate();
                    textFrame.repaint();
                    userNumber = userNumber + 5;
                    if (userNumber <= 1441 && userNumber >= 4) {
                        frame.getReturnMinus5Button().setBackground(Color.WHITE);
                    }
                } else {
                    frame.getReturnPlus5Button().setBackground(Color.GRAY); //button grey -> it doesn't work because id is out of range for this button
                }
            }
        });
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setVisible(true);

    }
//TODO Daria Funktionen kommentieren
    /**
     *
     * @param timestamp
     * @return
     */
    private String getDroneDynamicMinus5(String timestamp) { // minus 5 minuten
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.minusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }

    /**
     *
     * @param timestamp
     * @return
     */
    private String getDroneDynamicPlus5(String timestamp) { // plus 5 minuten
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.plusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }

    /**
     *
     * @param nextTimeStemp
     * @param droneInfo
     * @return
     */
    private DroneDynamics findDroneDyn5min(String nextTimeStemp, Drones droneInfo) {
        for (DroneDynamics droneDyn : droneInfo.getDroneDynamicsList()) {
            if (Objects.equals(droneDyn.getTimestamp(), nextTimeStemp)) {
                return droneDyn;
            }
        }
        return null;
    }
}