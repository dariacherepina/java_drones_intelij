package GUI;

import API.Stream;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


//JFrame = a GUI window to add components to

public class MyFrame extends JFrame {
    static Convert helper = new Convert();
    private JLabel label1;
    private JPanel mainPanel;
    private JTable table = new JTable();

    public MyFrame(ArrayList<Drones> DronesList, ArrayList<DroneTypes> DroneTypesList, ArrayList<DroneDynamics> DroneDynamicsList) throws IOException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setResizable(false); // prevent frame from being resized
        this.setExtendedState(MAXIMIZED_BOTH); //shows the Gui in full screen
        this.setTitle("Drones simulator"); //sets title of the frame


        ImageIcon image = new ImageIcon("drone.jpg"); //creates an ImageIcon
        this.setIconImage(image.getImage()); //change Icon of the frame
        this.getContentPane().setBackground(Color.BLACK); //change color of background
        this.setLayout(new BorderLayout()); //Layout of the frame

        mainPanel = new JPanel();                 //includes the label1 and the table
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());

        // Define the columns for the table
        String[] columns = {"ID", "TypeName", "Status"};

        // Determine the number of rows needed based on the ArrayList with the largest size
        int numRows = Math.max(DronesList.size(), Math.max(DroneTypesList.size(), DroneDynamicsList.size()));

        // Create a 2D array to hold the data for the table
        Object[][] data = new Object[numRows][columns.length];

        // Populate the data array with the specific information you need
        for (int i = 0; i < numRows; i++) {
            if (i < DronesList.size()) {
                Drones drone = DronesList.get(i);
                data[i][0] = drone.getId();

                // Assuming you have a method to get TypeName from Drone
                data[i][1] = drone.getDroneType().getTypeName();

                if (i < DroneDynamicsList.size()) {
                    DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                    // Assuming you have a method to get Status from DroneDynamics
                    data[i][2] = droneDynamics.getStatus();
                } else {
                    data[i][2] = ""; // Placeholder for Status, modify accordingly
                }
            } else {
                // Populate remaining rows with empty data or placeholders
                data[i][0] = "";
                data[i][1] = "";
                data[i][2] = "";
            }


            if (i < DroneTypesList.size()) {
                DroneTypes droneType = DroneTypesList.get(i);
                // You need to decide how to map DroneTypes data to the columns
            }

            if (i < DroneDynamicsList.size()) {
                DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                // You need to decide how to map DroneDynamics data to the columns
            }
        }

        // Set the new data model for the table
        table.setModel(new DefaultTableModel(data, columns));


        // Object[][] data = {};
        //DefaultTableModel defaultModel = new DefaultTableModel(data, columns);
        //table = new JTable(defaultModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //table.setBorder((Border) Color.cyan);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        JPanel panel = new JPanel();            //includes the buttons Dashboard, Drone Catalog, Drones, DroneTypes, Drone Dynamics, DroneID
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBackground(Color.white);
        panel.add(dashboardButton);

        JButton droneCatalogButton = new JButton("DroneCatalog");
        droneCatalogButton.setBackground(Color.white);
        panel.add(droneCatalogButton);

        JButton dronesButton = new JButton("Drones");
        dronesButton.setBackground(Color.white);

        JButton droneTypesButton = new JButton("DroneTypes");
        droneTypesButton.setBackground(Color.white);

        JButton droneDynamicsButton = new JButton("DroneDynamics");
        droneDynamicsButton.setBackground(Color.white);

        JButton droneIDButton = new JButton("DroneID");
        droneIDButton.setBackground(Color.white);

        JPanel eastPanel = new JPanel();               //includes refreshButton
        eastPanel.setBackground(Color.BLACK);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(Color.white);
        eastPanel.add(refreshButton);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));   //includes southPanel
        southPanel.setBackground(Color.BLACK);

        JButton returnButton = new JButton("RETURN5MINUTES");
        returnButton.setBackground(Color.white);
        southPanel.add(returnButton);


        //makes all buttons have the same size
        Dimension maxButtonSize = new Dimension(140, 40);
        dashboardButton.setMaximumSize(maxButtonSize);
        droneCatalogButton.setMaximumSize(maxButtonSize);
        dronesButton.setMaximumSize(maxButtonSize);
        droneTypesButton.setMaximumSize(maxButtonSize);
        droneDynamicsButton.setMaximumSize(maxButtonSize);
        droneIDButton.setMaximumSize(maxButtonSize);
        refreshButton.setMaximumSize(maxButtonSize);
        returnButton.setPreferredSize(maxButtonSize);

        //ActionListeners for the buttons
        dashboardButton.addActionListener(new ActionListener() {   //ActionListener for dashboardButton
            @Override
            public void actionPerformed(ActionEvent e) {

                // Define the columns for the table
                String[] columns = {"ID", "TypeName", "Status"};

                // Determine the number of rows needed based on the ArrayList with the largest size
                int numRows = Math.max(DronesList.size(), Math.max(DroneTypesList.size(), DroneDynamicsList.size()));

                // Create a 2D array to hold the data for the table
                Object[][] data = new Object[numRows][columns.length];

                // Populate the data array with the specific information you need
                for (int i = 0; i < numRows; i++) {
                    if (i < DronesList.size()) {
                        Drones drone = DronesList.get(i);
                        data[i][0] = drone.getId();

                        // Assuming you have a method to get TypeName from Drone
                        data[i][1] = drone.getDroneType().getTypeName();

                        if (i < DroneDynamicsList.size()) {
                            DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                            // Assuming you have a method to get Status from DroneDynamics
                            data[i][2] = droneDynamics.getStatus();
                        } else {
                            data[i][2] = ""; // Placeholder for Status, modify accordingly
                        }
                    } else {
                        // Populate remaining rows with empty data or placeholders
                        data[i][0] = "";
                        data[i][1] = "";
                        data[i][2] = "";
                    }


                    if (i < DroneTypesList.size()) {
                        DroneTypes droneType = DroneTypesList.get(i);
                        // You need to decide how to map DroneTypes data to the columns
                    }

                    if (i < DroneDynamicsList.size()) {
                        DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                        // You need to decide how to map DroneDynamics data to the columns
                    }
                }

                // Set the new data model for the table
                table.setModel(new DefaultTableModel(data, columns));

            }
        });

        droneCatalogButton.addActionListener(new ActionListener() {   // ActionListener for droneCatalogButton
            @Override
            public void actionPerformed(ActionEvent e) {

                droneCatalogButton.setBackground(Color.gray);  //droneCatalogButton turns gray when you click it once

                // include buttons for Drones, DroneTypes and DroneDynamics to the Panel
                panel.add(dronesButton);
                panel.add(droneTypesButton);
                panel.add(droneDynamicsButton);
                panel.add(droneIDButton);

                MyFrame.this.revalidate();   //update frame in order to include the added buttons
                MyFrame.this.repaint();
            }

        });
        dronesButton.addActionListener(new ActionListener() {          // ActionListener für dronesButton
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel("DRONES");

                String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
                Object[][] data = helper.ArrayList2ObjectDrones(DronesList);
                table.setModel(new DefaultTableModel(data, columns));

            }
        });
        droneTypesButton.addActionListener(new ActionListener() {  // ActionListener für droneTypesButton
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel("DRONE TYPES");

                String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
                Object[][] data = helper.ArrayList2ObjectDroneType(DroneTypesList);
                table.setModel(new DefaultTableModel(data, columns));

            }
        });
        droneDynamicsButton.addActionListener(new ActionListener() {    // ActionListener für droneDynamicsButton
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel("DRONE DYNAMICS");

                String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
                Object[][] data = helper.ArrayList2ObjectDroneDynamics(DroneDynamicsList);
                table.setModel(new DefaultTableModel(data, columns));

            }
        });
        //the Object[][] data with sorted DroneDynamics by Status
        // Object[][] data = helper.ArrayList2ObjectDroneDynamics(Sort.sortStatus(DroneDynamicsList));


        droneIDButton.addActionListener(new ActionListener() {      // ActionListener für droneIDButton
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

                // Use helper method to get information for the specific drone ID
                Drones droneInfo = helper.findDrone(DronesList, droneId);
                ;

                // Create a new JFrame for displaying text
                JFrame textFrame = new JFrame("Drone Information");

                // Set the size to match the JTable and JScrollPane dimensions
                textFrame.setSize(950, 350);

                // Set the location to match the JTable and JScrollPane position
                textFrame.setLocation(180, 400);

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
        });


        this.getContentPane().add(panel, BorderLayout.WEST);  //add panel to frame
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);  //add mainPanel to frame
        this.getContentPane().add(eastPanel, BorderLayout.EAST);   //add eastPanel to the frame
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);   //add southPanel to the frame
        createLabel(); //calls the method createLabel
        this.setVisible(true); // makes frame visible, in the end in order to see every component
    }

    public static void main(String[] args) {
        try {
            ArrayList<Drones> DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            new MyFrame(DronesList, DroneTypesList, DroneDynamicsList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createLabel() {   //includes the title of the site and the image
        label1 = new JLabel("DRONE OVERVIEW");
        ImageIcon image2 = new ImageIcon("drones.jpg");
        label1.setIcon(image2);
        label1.setHorizontalTextPosition(JLabel.CENTER); //sets text left, center, right of Imageicon
        label1.setVerticalTextPosition(JLabel.TOP); //sets text top, center, bottom of Imageicon
        label1.setForeground(Color.CYAN); //sets font color of text
        label1.setIconTextGap(30); //sets gap of text to image
        label1.setVerticalAlignment(JLabel.CENTER); //sets vertical position of icon + text within label
        label1.setHorizontalAlignment(JLabel.CENTER); //sets horizontal position of icon + text within label
        mainPanel.add(label1, BorderLayout.CENTER); //add label1 to mainPanel
    }

    private void setLabel(String labelText) {  //changes title ot the page when you click the different buttons, Drones, Drone Type & Drone Dynamics
        label1.setText(labelText);
    }
}