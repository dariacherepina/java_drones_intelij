package gui;

import drone.Convert;
import drone.DroneDynamics;
import drone.Drones;
import exception.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Implements the  ActionListener for the `droneIDButton`
 * @author Afnan Ismail & Alina Winschel & Daria Cherepina
 */

public class DroneIDActionListener implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());
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
     * Displays the individual drone information by asking the user for an id and
     * gives an error when the user enters a wrong id
     * @param e the event to be processed
     * @author Alina Winschel & Afnan Ismail
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String droneIdString = JOptionPane.showInputDialog("Enter Drone ID: (71-95)");
        if (droneIdString == null || droneIdString.trim().isEmpty()) {
            return;
        }

        int droneId = Integer.parseInt(droneIdString);
        if (droneId < 71 || droneId > 95) {
            JOptionPane.showMessageDialog(frame,
                    " !! Please enter a Drone ID between 71 and 95 !!",
                    "Attention!",
                    JOptionPane.INFORMATION_MESSAGE);
            LOGGER.info("Invalid number!");
            return;
        }

        try {
            droneInfo = helper.findDrone(dronesList, droneId);
        } catch (InvalidIdInput ex) {
            throw new RuntimeException(ex);
        }

        JFrame textFrame = new JFrame("Drone Information");
        textFrame.setSize(950, 350);
        textFrame.setLocationRelativeTo(null);
        textFrame.setResizable(false);

        JTextArea droneInfoTextArea = new JTextArea();
        droneInfoTextArea.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        String infoText = "Drone: " + droneInfo.getId() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() +
                        "\nCarriage Weight: " + droneInfo.getCarriageWeight() +
                        "\nCarriage Type " + droneInfo.getCarriageType() +
                        "\n\nDroneType:" + droneInfo.getDroneType().toString();
        droneInfoTextArea.setText(infoText);
        panel.add(droneInfoTextArea);

        JTextField userInputField = new JTextField();
        userInputField.setPreferredSize(new Dimension(150, 30));

        JButton submitButton = new JButton("Submit");
        droneInfoTextArea2 = new JTextArea();
        droneInfoTextArea2.setEditable(false);

        submitButton.addActionListener(new ActionListener() {

            /**
             *Checks the valid user input to display the DroneDynamics
             * @param e the event to be processed
             * @author Afnan Ismail & Daria Cherepina
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getReturnPlus5Button().setBackground(Color.WHITE);
                frame.getReturnMinus5Button().setBackground(Color.WHITE);
                String userInput = userInputField.getText();

                try {
                    userNumber = Integer.parseInt(userInput) - 1;
                } catch (NumberFormatException ex) {
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

        JPanel southInputPanel = new JPanel();
        southInputPanel.add(new JLabel("Please enter a number between 1 and 1441 for a specific DroneDynamic of the Drone:\n"));
        southInputPanel.add(userInputField);
        southInputPanel.add(submitButton);
        textFrame.add(southInputPanel, BorderLayout.SOUTH);

        frame.getReturnMinus5Button().addActionListener(new ActionListener() {

            /**
             * Subtracts 5 minutes from the timestamp
             * @param e the event to be processed
             * @author Afnan Ismail & Daria Cherepina
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNumber <= 1440 && userNumber >= 5) {
                    String nextTimeStempMinus = getDroneDynamicMinus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
                    String infoTextDroneDynamics = Objects.requireNonNull(findDroneDyn5min(nextTimeStempMinus, droneInfo)).toString();
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
                    if (userNumber <= 1436 && userNumber >= 0) {
                        frame.getReturnPlus5Button().setBackground(Color.WHITE);
                    }
                } else {
                    frame.getReturnMinus5Button().setBackground(Color.GRAY);
                }
            }
        });

        frame.getReturnPlus5Button().addActionListener(new ActionListener() {

            /**
             * Adds 5 minutes to the timestamp
             * @param e the event to be processed
             * @author Afnan Ismail & Daria Cherepina
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNumber <= 1436 && userNumber >= 0) {
                    frame.getReturnPlus5Button().setBackground(Color.WHITE);
                    String nextTimeStempPlus = getDroneDynamicPlus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
                    String infoTextDroneDynamics1 = Objects.requireNonNull(findDroneDyn5min(nextTimeStempPlus, droneInfo)).toString();
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
                    if (userNumber <= 1440 && userNumber >= 5) {
                        frame.getReturnMinus5Button().setBackground(Color.WHITE);
                    }
                } else {
                    frame.getReturnPlus5Button().setBackground(Color.GRAY);
                }
            }
        });
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setVisible(true);
    }

    /**
     * Converts the timestamp in ZonedDateTime with help of the DateTimeFormatter
     * and subtracts 5 minutes from the timestamp
     * @param timestamp parameter from DroneDynamics
     * @return String new timestamp (-5 minutes)
     * @author Daria Cherepina
     */

    private String getDroneDynamicMinus5(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.minusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }

    /**
     * Converts the timestamp in ZonedDateTime with the help of the DateTimeFormatter
     * and adds 5 minutes to the timestamp
     * @param timestamp parameter from DroneDynamics
     * @return String new timestamp (+5 minutes)
     * @author Daria Cherepina
     */

    private String getDroneDynamicPlus5(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.plusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }

    /**
     * Finds the DroneDynamics through the timestamp
     * @param nextTimeStemp parameter from DroneDynamics
     * @param droneInfo drone that was chosen by user
     * @return the found DroneDynamic
     * @author Daria Cherepina
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