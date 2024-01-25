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
import java.awt.Container;
import java.util.Objects;

import Exception.*;

public class DroneIDActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    private Convert helper;
    private int userNumber;
    private JTextArea droneInfoTextArea1;
    private JTextArea infoTextD;
   private JPanel panel;
   private JTextArea droneInfoTextArea2;
   private Drones droneInfo;

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

        try {
            droneInfo = helper.findDrone(DronesList, droneId);
        } catch (InvalidIdInput ex) {
            throw new RuntimeException(ex);
        }


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

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        //user eingibt 81 -> 50 droned
        // Add the information to the JTextArea
        String infoText =
                "Drone " + droneInfo.getId() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() +
                        "\nCarriage Weight: " + droneInfo.getCarriageWeight() +
                        "\nCarriage Type " + droneInfo.getCarriageType() +
                         "\n\nDroneType:" + droneInfo.getDroneType().toString();



        droneInfoTextArea.setText(infoText);
        // DroneDynamics for Drone as Object[][]
        //Object[][] data = helper.ArrayList2ObjectDroneDynamics(droneInfo.getDroneDynamicsList());
        // Add the JTextArea to the textFrame
        panel.add(droneInfoTextArea);

//        // Set the default close operation and make the frame visible
//        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        textFrame.setVisible(true);

        // Hide the JTable and JScrollPane
               /* table.setVisible(false);
                scrollPane.setVisible(false);*/

// Prompt the user to enter a number

        // Create a JTextField for user input
        JTextField userInputField = new JTextField();
        userInputField.setPreferredSize(new Dimension(150, 30));

// Create a JButton to submit the input
        JButton submitButton = new JButton("Submit");

        droneInfoTextArea2 = new JTextArea();
        droneInfoTextArea2.setEditable(false);



        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String userInput = userInputField.getText();

                // Check if the user entered a number
                try {
                     userNumber = Integer.parseInt(userInput);
                } catch (NumberFormatException ex) {
                    // Handle the case where the user did not enter a valid number
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Now 'userNumber' contains the entered number, and you can use it as needed.
                // ...
                //textFrame.dispose(); // Close the frame after processing the input
//                    JTextArea droneInfoTextArea1 = new JTextArea();
//                    droneInfoTextArea1.setEditable(false);

                String infoText2 = "Drone Dynamic\n " + droneInfo.getDroneDynamicsList().get(userNumber);
                droneInfoTextArea2.setText(infoText2);
                panel.add(droneInfoTextArea2);
                panel.add(frame.getReturnPlus5Button());
                panel.add(frame.getReturnMinus5Button());
//                textFrame.add(frame.getReturnMinus5Button(),BorderLayout.EAST);
//                textFrame.add(frame.getReturnMinus5Button(),BorderLayout.EAST);


                textFrame.revalidate();
                textFrame.repaint();


            }
        });
        textFrame.add(panel,BorderLayout.NORTH);




// Create a JPanel to hold the input components
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Please enter a number between 1 and 1441 for a specific DroneDynamic of the Drone:\n"));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);

// Add the inputPanel to the textFrame
        textFrame.add(inputPanel, BorderLayout.SOUTH);
//        textFrame.add(droneInfoTextArea);
//        textFrame.add(droneInfoTextArea2);
        // Set the default close operation and make the frame visible

       // JTextArea droneDynTextArea = new JTextArea();
        frame.getReturnMinus5Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // das in bereich UserNummer  von 5 bis 1441
                String nextTimeStempMinus = getDroneDynamicMinus5(droneInfo.getDroneDynamicsList().get(userNumber).getTimestamp());
               // new String die wir in 2TextArea erstellen sollen
                String infoTextD = findDroneDyn5min(nextTimeStempMinus, droneInfo).toString();
                //droneDynTextArea.setText(infoTextD);

                System.out.println(infoTextD);
                droneInfoTextArea2.setText(infoTextD);
                panel.removeAll();

                panel.add(droneInfoTextArea);
                panel.add(droneInfoTextArea2);
                panel.add(frame.getReturnPlus5Button());
                panel.add(frame.getReturnMinus5Button());

                panel.setVisible(true);
                textFrame.revalidate();
                textFrame.repaint();

            }
        });
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setVisible(true);

    }

    private String getDroneDynamicMinus5(String timestamp){ // minus 5 minuten
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
