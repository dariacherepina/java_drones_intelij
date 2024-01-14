package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Drone.DroneDynamics;
import  Drone.DroneTypes;
import API.APIEndpoints;
import API.APIConnection;
import Drone.Convert;
import Drone.Drones;


//JFrame = a GUI window to add components to

public class MyFrame extends JFrame {
    static Convert helper = new Convert();
    APIEndpoints droneIndivData = new APIEndpoints();

    public MyFrame(ArrayList<Object> droneTypesList) {


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setResizable(true); // prevent frame from being resized
        this.setSize(1220, 820); // sets the x-dimension & the y-dimension of the frame
        this.setTitle("Drones simulator"); //sets title of the frame

        ImageIcon image = new ImageIcon("drone.jpg"); //creates an ImageIcon
        this.setIconImage(image.getImage()); //change Icon of the frame
        this.getContentPane().setBackground(Color.BLACK); //change color of background
        this.setLayout(null); //Layout of the frame


        String[] columns = {"ID", "TypeName", "Status"};
        Object[][] data = {};
        DefaultTableModel defaultModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        scrollPane.setBounds(180, 400, 950, 350);
        this.add(scrollPane);


        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setLayout(null);
        dashboardButton.setBounds(0, 20, 140, 40);
        dashboardButton.setBackground(Color.white);
        this.add(dashboardButton);

        JButton droneCatalogButton = new JButton("DroneCatalog");
        droneCatalogButton.setLayout(null);
        droneCatalogButton.setBounds(0, 50, 140, 40);
        droneCatalogButton.setBackground(Color.white);
        this.add(droneCatalogButton);

        JButton dronesButton = new JButton("Drones");
        dronesButton.setLayout(null);
        dronesButton.setBounds(0, 90, 140, 40);
        dronesButton.setBackground(Color.white);


        JButton droneTypesButton = new JButton("DroneTypes");
        droneTypesButton.setLayout(null);
        droneTypesButton.setBounds(0, 130, 140, 40);
        droneTypesButton.setBackground(Color.white);


        JButton droneDynamicsButton = new JButton("DroneDynamics");
        droneDynamicsButton.setLayout(null);
        droneDynamicsButton.setBounds(0, 170, 140, 40);
        droneDynamicsButton.setBackground(Color.white);

        JButton RefreshButton = new JButton("Refresh");
        RefreshButton.setLayout(null);
        RefreshButton.setBounds(1050, 20, 100, 40);
        RefreshButton.setBackground(Color.white);
        this.add(RefreshButton);

        JButton ReturnButton = new JButton("RETURN5MINUTES");
        ReturnButton.setLayout(null);
        ReturnButton.setBounds(0, 500, 140, 40);
        ReturnButton.setBackground(Color.white);
        this.add(ReturnButton);

        JButton droneIDButton = new JButton("DroneID");
        droneIDButton.setLayout(null);
        droneIDButton.setBounds(0, 210, 140, 40);
        droneIDButton.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 800, 140, 820);
        panel.setBackground(Color.ORANGE);


        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "TypeName", "Status"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
            }
        });


        // ActionListener für droneCatalogButton
        droneCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                droneCatalogButton.setBackground(Color.gray);

                // Buttons für Drones, DroneTypes und DroneDynamics zum leftPanel hinzufügen
                panel.add(dronesButton);
                MyFrame.this.add(dronesButton);

                panel.add(droneTypesButton);
                MyFrame.this.add(droneTypesButton);

                panel.add(droneDynamicsButton);
                MyFrame.this.add(droneDynamicsButton);

                panel.add(droneIDButton);
                MyFrame.this.add(droneIDButton);

                // Fenster aktualisieren, um die hinzugefügten Buttons anzuzeigen

                MyFrame.this.revalidate();
                MyFrame.this.repaint();
            }

        });

        // ActionListener für dronesButton
        dronesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
                ArrayList<Drones> DronesList = helper.Input2DronesObject(helper.dataStreamOut("outputDrones"));
                Object[][] data = helper.ArrayList2ObjectDrones(DronesList);
                table.setModel(new DefaultTableModel(data, columns));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            }
        });

      /*  // Erstellen Sie ein JLabel, um die Drone-Informationen anzuzeigen
        JLabel droneInfoLabel = new JLabel();
        droneInfoLabel.setBounds(0, 250, 140, 40); // Passen Sie die Position und Größe nach Bedarf an
        this.add(droneInfoLabel); */

        droneIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming you want to get information for a specific drone ID, e.g., droneId = 85
                int droneId = 85;

                // Use helper method to get information for the specific drone ID
                Drones droneInfo = helper.Input2DronesObjectIndiv(droneIndivData.getDronesIndivData(droneId));

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
                String infoText = "Drone ID: " + droneInfo.getId() +
                        "\nCreation Time: " + droneInfo.getCreated() +
                        "\nSerial Number: " + droneInfo.getSerialNumber() + "\n";
                droneInfoTextArea.setText(infoText);

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



  /*      droneIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int droneId = 85;
                Drones DronesListFull = helper.Input2DronesObjectIndiv(droneIndivData.getDronesIndivData(droneId));
            }
        }); */


// ActionListener für droneTypesButton
        droneTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
                ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(helper.dataStreamOut("outputDroneTypes"));
                Object[][] data = helper.ArrayList2ObjectDroneType(DroneTypesList);
                table.setModel(new DefaultTableModel(data, columns));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // ActionListener für droneDynamicsButton
        droneDynamicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
                ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(droneIndivData.getDroneDynamics());
                Object[][] data = helper.ArrayList2ObjectDroneDynamics(DroneDynamicsList);
                table.setModel(new DefaultTableModel(data, columns));
            }
        });

        this.add(panel);
        createLabel();
        this.setVisible(true); // makes frame visible, in the end in order to see every component
    }

    private void createLabel() {
        JLabel label1 = new JLabel("DRONE OVERVIEW");
        ImageIcon image2 = new ImageIcon("drones.jpg");
        label1.setIcon(image2);
        label1.setHorizontalTextPosition(JLabel.CENTER); //sets text left, center, right of Imageicon
        label1.setVerticalTextPosition(JLabel.TOP); //sets text top, center, bottom of Imageicon
        label1.setForeground(Color.ORANGE); //sets font color of text
        label1.setIconTextGap(30); //sets gap of text to image
        label1.setVerticalAlignment(JLabel.CENTER); //sets vertical position of icon + text within label
        label1.setHorizontalAlignment(JLabel.CENTER); //sets horizontal position of icon + text within label
        label1.setBounds(380, 40, 600, 350);//sets x, y & dimension of the label within frame
        this.add(label1); //add label1 to frame

    }


    public static void main(String[] args) {
        ArrayList<Object> droneTypesList = new ArrayList<>();
        new MyFrame(droneTypesList);
    }
}