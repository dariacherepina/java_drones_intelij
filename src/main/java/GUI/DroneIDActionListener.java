package GUI;

import Drone.Convert;
import Drone.DroneDynamics;
import Drone.Drones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroneIDActionListener implements ActionListener {
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
        Drones droneInfo = helper.findDrone(DronesList, droneId);
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

        // Add the information to the JTextArea
        String infoText =
                "Drone ID: " + droneInfo.getId() +
                        "\nDroneType: " + droneInfo.getDroneType() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() +
                        "\nCarriage Weight: " + droneInfo.getCarriageWeight() +
                        "\nCarriage Type: " + droneInfo.getCarriageType();
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
}
