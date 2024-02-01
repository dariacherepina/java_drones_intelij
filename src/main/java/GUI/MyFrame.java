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
import java.io.IOException;
import java.util.ArrayList;


//JFrame = a GUI window to add components to

public class MyFrame extends JFrame {
    static Convert helper = new Convert();
    private JLabel label1;
    private JPanel mainPanel;
    private JPanel eastPanel;
    private JPanel panel;
    private JTable table = new JTable();
    private JButton droneCatalogButton;
    private JButton dronesButton;
    private JButton droneTypesButton;
    private JButton droneDynamicsButton;
    private JButton droneIDButton;
    private JButton refreshButton;
    private JButton sortByCarriageWeight;
    private JButton sortByMaximumCarriage;
    private JButton sortBySpeed;
    private JButton sortByStatus;
    private JButton returnPlus5Button;
    private JButton returnMinus5Button;
    private JPanel panelSort;

    /**
     * Constructs a new instance of MyFrame, a JFrame for the Drones Simulator application
     *
     * @param dronesList the list of Drones to be displayed in the frame
     * @param droneTypesList the list of DroneTypes to be used in the frame
     * @param droneDynamicsList the list of DroneDynamics to be used in the frame
     * @throws IOException if an I/O error occurs
     */
    public MyFrame(ArrayList<Drones> dronesList, ArrayList<DroneTypes> droneTypesList, ArrayList<DroneDynamics> droneDynamicsList) throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setResizable(false); // prevent frame from being resized
        this.setExtendedState(MAXIMIZED_BOTH); //shows the Gui in full screen
        this.setTitle("Drones simulator"); //sets title of the frame

        ImageIcon image = new ImageIcon("drone.jpg"); //creates an ImageIcon
        this.setIconImage(image.getImage()); //change Icon of the frame
        this.getContentPane().setBackground(Color.BLACK); //change color of background
        this.setLayout(new BorderLayout()); //Layout of the frame

        mainPanel = new JPanel(); //includes the label1 and the table
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());

        panelSort = new JPanel(); //includes sort Buttons
        panelSort.setLayout(new BoxLayout(panelSort, BoxLayout.Y_AXIS));
        panelSort.setBackground(Color.BLACK);
        panelSort.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10)); //empty border
        mainPanel.add(panelSort, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();  //includes sort Buttons
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        panelSort.add(centerPanel, BorderLayout.CENTER);

        String[] columns = {"ID", "TypeName", "Manufacturer"};// Define the columns for the table
        int numRows = dronesList.size();// Determine the number of rows needed based on the ArrayList with the smallest size
        Object[][] data = new Object[numRows][columns.length];// Create a 2D array to hold the data for the table

        int minSize = dronesList.size();
        for (int i = 0; i < minSize; i++) {
            Drones drone = dronesList.get(i);
            data[i][0] = drone.getId();
            data[i][1] = drone.getDroneType().getTypeName();
            data[i][2] = drone.getDroneType().getManufacturer();
        }

        table.setModel(new DefaultTableModel(data, columns));// Set the new data model for the table
        table.setBackground(Color.DARK_GRAY);  //background for JTable
        table.setForeground(Color.white);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(); //TableCellRenderer for whole table
        cellRenderer.setBackground(Color.DARK_GRAY);
        cellRenderer.setForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();//TableCellRenderer for header od columns
        header.setBackground(Color.DARK_GRAY); // background color for TableHeader
        header.setForeground(Color.white);

        int columnCount = table.getColumnModel().getColumnCount(); //table that is seen when starting the GUI
        for (int i = 0; i < columnCount; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        panel = new JPanel(); //includes the buttons Dashboard, Drone Catalog, Drones, DroneTypes, Drone Dynamics, DroneID
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);
        this.add(emptyPanel, BorderLayout.NORTH);

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

        eastPanel = new JPanel(); //includes refreshButton
        eastPanel.setBackground(Color.BLACK);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(Color.white);
        eastPanel.add(refreshButton);

        sortByCarriageWeight = new JButton("Sort Carriage Weight");
        sortByCarriageWeight.setBackground(Color.white);
        sortByCarriageWeight.setPreferredSize(new Dimension(300, 40));

        sortByMaximumCarriage = new JButton("Sort Maximum Carriage");
        sortByMaximumCarriage.setBackground(Color.white);
        sortByMaximumCarriage.setPreferredSize(new Dimension(300, 40));

        sortBySpeed = new JButton("Sort Maximum Speed");
        sortBySpeed.setBackground(Color.white);
        sortBySpeed.setPreferredSize(new Dimension(300, 40));

        sortByStatus = new JButton("Sort Status");
        sortByStatus.setBackground(Color.white);
        sortByStatus.setPreferredSize(new Dimension(300, 40));

        returnPlus5Button = new JButton("TimeStamp+5");
        returnPlus5Button.setBackground(Color.white);

        returnMinus5Button = new JButton("TimeStamp-5");
        returnMinus5Button.setBackground(Color.white);

        Dimension maxButtonSize = new Dimension(240, 40);  //makes all buttons have the same size
        dashboardButton.setMaximumSize(maxButtonSize);
        droneCatalogButton.setMaximumSize(maxButtonSize);
        dronesButton.setMaximumSize(maxButtonSize);
        droneTypesButton.setMaximumSize(maxButtonSize);
        droneDynamicsButton.setMaximumSize(maxButtonSize);
        droneIDButton.setMaximumSize(maxButtonSize);
        refreshButton.setMaximumSize(maxButtonSize);
        returnPlus5Button.setMaximumSize(maxButtonSize);
        returnMinus5Button.setMaximumSize(maxButtonSize);

        //ActionListeners for the buttons
        /**
         * sets up the ActionListener for various buttons
         * initializes the layout of the frame for the Drones Simulator application
         * each button is associated with a specific ActionListener to handle user interactions
         * the frame includes panels for navigation buttons, the main content, and additional components
         */
        dashboardButton.addActionListener(new DashboardActionListener(this, dronesList, droneTypesList, droneDynamicsList));   //ActionListener for dashboardButton
        droneCatalogButton.addActionListener(new DroneCatalogActionListener(this));
        dronesButton.addActionListener(new DronesActionListener(this, dronesList));
        droneTypesButton.addActionListener(new DroneTypesActionListener(this, droneTypesList));
        droneDynamicsButton.addActionListener(new DroneDynamicsActionListener(this, droneDynamicsList));    // ActionListener für droneDynamicsButton
        droneIDButton.addActionListener(new DroneIDActionListener(this, dronesList));     // ActionListener für droneIDButton
        sortByCarriageWeight.addActionListener(new SortByCarriageWeight(this, dronesList));
        sortByMaximumCarriage.addActionListener(new SortByMaximumCarriage(this, droneTypesList));
        sortBySpeed.addActionListener(new SortBySpeed(this, droneTypesList));
        sortByStatus.addActionListener(new SortByStatus(this, droneDynamicsList));
        refreshButton.addActionListener(new RefreshActionListener(this, dronesList, droneTypesList, droneDynamicsList));

        this.getContentPane().add(panel, BorderLayout.WEST);  //add panel to frame
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);  //add mainPanel to frame
        this.getContentPane().add(eastPanel, BorderLayout.EAST);   //add eastPanel to the frame
        createLabel(); //calls the method createLabel
        this.setVisible(true); // makes frame visible, in the end in order to see every component
    }
    /**
     * method initialised three ArrayList for Drones, DroneTypes and DroneDynamics
     * and after that calls the method to add all additional data from DroneTypes and DroneDynamics to Drone
     * and create a new instance of class MyFrame
     * @param args
     */
    public static void main(String[] args) {
        try {
            ArrayList<Drones> dronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> droneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> droneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(dronesList, droneTypesList, droneDynamicsList);
            new MyFrame(dronesList, droneTypesList, droneDynamicsList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *displays the drone image and the label 'DRONE OVERVIEW'
     */
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
        mainPanel.add(label1, BorderLayout.CENTER); //add label1 to mainPanel
    }

    /**
     * changes title ot the page when you click the different buttons, Drones, Drone Type & Drone Dynamics
     * @param labelText the text to set for label1
     */
    public void setLabel(String labelText) {
        label1.setText(labelText);
    }

    public JTable getTable() {
        return table;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getDroneCatalogButton() {
        return droneCatalogButton;
    }

    public JButton getDronesButton() {
        return dronesButton;
    }

    public JButton getDroneTypesButton() {
        return droneTypesButton;
    }

    public JButton getDroneDynamicsButton() {
        return droneDynamicsButton;
    }

    public JButton getDroneIDButton() {
        return droneIDButton;
    }

    public JButton getSortByCarriageWeight() {
        return sortByCarriageWeight;
    }

    public JButton getSortByMaximumCarriage() {
        return sortByMaximumCarriage;
    }

    public JButton getSortBySpeed() {
        return sortBySpeed;
    }

    public JButton getSortByStatus() {
        return sortByStatus;
    }

    public JButton getReturnPlus5Button() {
        return returnPlus5Button;
    }

    public JButton getReturnMinus5Button() {
        return returnMinus5Button;
    }

    public JPanel getPanelSort() {
        return panelSort;
    }
}