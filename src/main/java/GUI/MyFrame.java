package GUI;

import API.Stream;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
    private JPanel panel;
    private JTable table = new JTable();
    JButton droneCatalogButton;
    private JButton dronesButton;
    private JButton droneTypesButton;
    private JButton droneDynamicsButton;
    private JButton droneIDButton;


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
        String[] columns = {"ID", "TypeName", "Manufacturer"};

        // Determine the number of rows needed based on the ArrayList with the smallest size
        int numRows = DronesList.size();

        // Create a 2D array to hold the data for the table
        Object[][] data = new Object[numRows][columns.length];

        int minSize = DronesList.size();

        for (int i = 0; i < minSize; i++) {
            Drones drone = DronesList.get(i);
            data[i][0] = drone.getId();
            data[i][1] = drone.getDroneType().getTypeName();

            DroneDynamics droneDynamics = DroneDynamicsList.get(i);
            data[i][2] = drone.getDroneType().getManufacturer();
        }


        // Set the new data model for the table
        table.setModel(new DefaultTableModel(data, columns));
        //background for JTable
        table.setBackground(Color.DARK_GRAY);
        table.setForeground(Color.white);

        //TableCellRenderer for whole table
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(Color.DARK_GRAY);
        cellRenderer.setForeground(Color.WHITE);
        //TableCellRenderer for header od columns
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY); // Hintergrundfarbe für TableHeader ändern
        header.setForeground(Color.white);

        //header.setDefaultRenderer(cellRenderer);
        //TableCellRenderer for every column
        int columnCount = table.getColumnModel().getColumnCount();
        for(int i = 0; i < columnCount; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }


        // Object[][] data = {};
        //DefaultTableModel defaultModel = new DefaultTableModel(data, columns);
        //table = new JTable(defaultModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //table.setBorder((Border) Color.cyan);
        JScrollPane scrollPane = new JScrollPane(table);
        // scrollPane.setBackground(Color.DARK_GRAY);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        panel = new JPanel();            //includes the buttons Dashboard, Drone Catalog, Drones, DroneTypes, Drone Dynamics, DroneID
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBackground(Color.white);
        panel.add(dashboardButton);

        droneCatalogButton = new JButton("DroneCatalog");
        droneCatalogButton.setBackground(Color.white);
        panel.add(droneCatalogButton);

        dronesButton = new JButton("Drones");
        dronesButton.setBackground(Color.white);

        droneTypesButton = new JButton("DroneTypes");
        droneTypesButton.setBackground(Color.white);

        droneDynamicsButton = new JButton("DroneDynamics");
        droneDynamicsButton.setBackground(Color.white);

        droneIDButton = new JButton("DroneID");
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
        dashboardButton.addActionListener(new DashboardActionListener(this, DronesList, DroneDynamicsList));   //ActionListener for dashboardButton
        droneCatalogButton.addActionListener(new DroneCatalogActionListener(this));
        dronesButton.addActionListener(new DronesActionListener(this, DronesList));
        droneTypesButton.addActionListener(new DroneTypesActionListener(this, DroneTypesList));
        droneDynamicsButton.addActionListener(new DroneDynamicsActionListener(this,DroneDynamicsList));    // ActionListener für droneDynamicsButton
        droneIDButton.addActionListener(new DroneIDActionListener(this,DronesList));     // ActionListener für droneIDButton


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
        ImageIcon image2 = new ImageIcon("drones.png");
        label1.setIcon(image2);
        label1.setHorizontalTextPosition(JLabel.CENTER); //sets text left, center, right of Imageicon
        label1.setVerticalTextPosition(JLabel.TOP); //sets text top, center, bottom of Imageicon
        label1.setForeground(Color.WHITE); //sets font color of text
        label1.setIconTextGap(40); //sets gap of text to image
        label1.setVerticalAlignment(JLabel.CENTER); //sets vertical position of icon + text within label
        label1.setHorizontalAlignment(JLabel.CENTER); //sets horizontal position of icon + text within label
        mainPanel.add(label1,BorderLayout.CENTER); //add label1 to mainPanel
    }

    public void setLabel(String labelText) {  //changes title ot the page when you click the different buttons, Drones, Drone Type & Drone Dynamics
        label1.setText(labelText);
    }

    public JTable getTable() {
        return table;
    }

    public JPanel getPanel(){
        return panel;
    }
    public JButton getDroneCatalogButton(){
        return droneCatalogButton;
    }

    public JButton getDronesButton(){
        return dronesButton;
    }

    public JButton getDroneTypesButton(){
        return droneTypesButton;
    }

    public  JButton getDroneDynamicsButton(){
        return droneDynamicsButton;
    }

    public JButton getDroneIDButton(){
        return droneIDButton;
    }

}