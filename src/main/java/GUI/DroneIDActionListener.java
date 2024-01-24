package GUI;

import Drone.Convert;
import Drone.DroneDynamics;
import Drone.Drones;
import Exception.InvalidIdInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class DroneIDActionListener implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());

    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    private Convert helper;

    public DroneIDActionListener(MyFrame frame, ArrayList<Drones> DronesList){
        this.frame = frame;
        this.DronesList = DronesList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Use input dialog to get drone ID from the user
        String droneIdString = JOptionPane.showInputDialog("Enter Drone ID: (71-95)");

        // Check if the user clicked Cancel or entered an empty value
        if (droneIdString == null || droneIdString.trim().isEmpty()) {
            return; // Exit if no input or canceled
        }


        // Convert the input to an integer
        int droneId = Integer.parseInt(droneIdString);

        if (droneId < 71 || droneId > 95) {
            JFrame textFrame1 = new JFrame("ATTENTION!");
            textFrame1.setSize(350,90);
            textFrame1.setLocation(810,450);
            // Create a JTextArea to display the information
            JTextArea droneInfoTextArea1 = new JTextArea();
            droneInfoTextArea1.setEditable(false);
            // Add the information to the JTextArea
            String infoText1 = "             !!Please enter a Drone ID between 71 and 95!!" ;
            droneInfoTextArea1.setForeground(Color.RED);
            droneInfoTextArea1.setText(infoText1);
            // Add the JTextArea to the textFrame
            textFrame1.add(new JScrollPane(droneInfoTextArea1));
            // Set the default close operation and make the frame visible
            textFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textFrame1.setVisible(true);
            textFrame1.setResizable(false);

            return; // Exit if no input or canceled
        }



        // Use helper method to get information for the specific drone ID
        Drones droneInfo = null;
        try {
            droneInfo = helper.findDrone(DronesList, droneId);
        } catch (InvalidIdInput ex) {
            LOGGER.warning("Id is in out of range");
            throw new RuntimeException(ex);
        }
        ;

        // Create a new JFrame for displaying text
        JFrame textFrame = new JFrame("Drone Information");

        // Set the size to match the JTable and JScrollPane dimensions
        textFrame.setSize(950, 350);

        // Set the location to match the JTable and JScrollPane position
        textFrame.setLocation(500, 400);
        textFrame.setResizable(false);


        // Create a JTextArea to display the information
        JTextArea droneInfoTextArea = new JTextArea();
        droneInfoTextArea.setEditable(false);
        int userNumber = 5;
        String nextTimeStempPlus = getDroneDynamicPlus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
        String nextTimeStempMinus = getDroneDynamicMinus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());


        // Add the information to the JTextArea
        String infoText =
                "Drone ID: " + droneInfo.getId() +
                        "\nDroneType: " + droneInfo.getDroneType() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() +
                        "\nCarriage Weight: " + droneInfo.getCarriageWeight() +
                        "\nCarriage Type: " + droneInfo.getCarriageType();
        // das in bereich UserNummer  von 0 bis 1436
                        findDroneDyn5min(nextTimeStempPlus, droneInfo).toString();
        // das in bereich UserNummer  von 5 bis 1441
        findDroneDyn5min(nextTimeStempMinus, droneInfo).toString();
        droneInfoTextArea.setText(infoText);
        // DroneDynamics for Drone as Object[][]
        //Object[][] data = helper.ArrayList2ObjectDroneDynamics(droneInfo.getDroneDynamicsList());
        // Add the JTextArea to the textFrame
        textFrame.add(new JScrollPane(droneInfoTextArea));

        // Set the default close operation and make the frame visible
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setVisible(true);

        // Hide the JTable and JScrollPane
               /* table.setVisible(false);
                scrollPane.setVisible(false);*/

    }
    private String getDroneDynamicPlus5(String timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.plusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }
    private String getDroneDynamicMinus5(String timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        ZonedDateTime zoneDateTime = ZonedDateTime.parse(timestamp, formatter);
        zoneDateTime = zoneDateTime.minusMinutes(5);
        System.out.println(zoneDateTime.format(formatter));
        return zoneDateTime.format(formatter);
    }
    private DroneDynamics findDroneDyn5min(String nextTimeStemp, Drones droneInfo){
        for(DroneDynamics droneDyn : droneInfo.getDroneDynamicsList()){
            if (Objects.equals(droneDyn.getTimestamp(), nextTimeStemp)){
                return droneDyn;
            } // TODO:Button history 5 min should be in the DroneID window
        }
        return null;
    }
}

