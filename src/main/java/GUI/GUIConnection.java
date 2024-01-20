package GUI;

import API.APIEndpoints;
import API.APIConnection;
import Drone.Convert;
import Drone.Drones;
import Drone.DroneTypes;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIConnection {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Drone Overview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main panel to hold the table and buttons
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table data and columns
        String[] columns = {"ID", "TypeName", "Status"};
        Object[][] data = {};

        DefaultTableModel defaultModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Left panel with buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        dashboardButton.setPreferredSize(new Dimension(150, 40));
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "TypeName", "Status"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
            }
        });

        JButton droneCatalogButton = new JButton("DroneCatalog");
        droneCatalogButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton dronesButton = new JButton("Drones");
        JButton droneTypesButton = new JButton("DroneTypes");
        JButton droneDynamicsButton = new JButton("DroneDynamics");

        // ActionListener für droneCatalogButton
        droneCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buttons für Drones, DroneTypes und DroneDynamics zum leftPanel hinzufügen
                leftPanel.add(dronesButton);
                leftPanel.add(droneTypesButton);
                leftPanel.add(droneDynamicsButton);

                // Fenster aktualisieren, um die hinzugefügten Buttons anzuzeigen
                frame.revalidate();
                frame.repaint();
            }
        });

        // ActionListener für dronesButton
        dronesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "DroneType", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
            }
        });

        // ActionListener für droneTypesButton
        droneTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
            }
        });

        // ActionListener für droneDynamicsButton
        droneDynamicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "TimeStamp", "Drone", "Speed", "AlignmentRoll", "ControlRange", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
            }
        });

        leftPanel.add(dashboardButton); // Dashboard-Button ganz oben links hinzufügen
        leftPanel.add(droneCatalogButton);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
/*
    // Create an object of the APIEndpoints class
    APIEndpoints apiEndpoints = new APIEndpoints();
    // Fetch drone data from the API
    String dronesDataJson = apiEndpoints.getDrones();

    // Convert the JSON data into a list of objects
    List<Object> dronesList = Convert.Input2Object(dronesDataJson, Convert.Type.DRONES);

    // Populate the table with the data
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // Clear the existing table data
    for (Object obj : dronesList) {
        if (obj instanceof Drones) {
            Drones drone = (Drones) obj;
            model.addRow(new Object[]{drone.getId(), drone.getDroneType(), drone.getStatus()});
        }
    }*/
}