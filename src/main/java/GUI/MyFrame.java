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
            System.out.println(drone);
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


        int columnCount = table.getColumnModel().getColumnCount();
        for(int i = 0; i < columnCount; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }


        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        panel = new JPanel();            //includes the buttons Dashboard, Drone Catalog, Drones, DroneTypes, Drone Dynamics, DroneID
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);
        this.add(emptyPanel,BorderLayout.NORTH);


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

        eastPanel = new JPanel();               //includes refreshButton
        eastPanel.setBackground(Color.BLACK);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        refreshButton = new JButton("Refresh Drones");
        refreshButton.setBackground(Color.white);
        eastPanel.add(refreshButton);


        sortByCarriageWeight = new JButton("Sort Carriage Weight");
        sortByCarriageWeight.setBackground(Color.white);
        sortByCarriageWeight.setSize(180,40);

        sortByMaximumCarriage = new JButton("Sort Maximum Carriage");
        sortByMaximumCarriage.setBackground(Color.white);

        sortBySpeed = new JButton("Sort Maximum Speed");
        sortBySpeed.setBackground(Color.white);

        sortByStatus = new JButton("Sort Status");
        sortByStatus.setBackground(Color.white);

        returnPlus5Button = new JButton("+5");
        returnPlus5Button.setBackground(Color.white);

        returnMinus5Button = new JButton("-5");
        returnMinus5Button.setBackground(Color.white);


        //makes all buttons have the same size
        Dimension maxButtonSize = new Dimension(180, 40);
        dashboardButton.setMaximumSize(maxButtonSize);
        droneCatalogButton.setMaximumSize(maxButtonSize);
        dronesButton.setMaximumSize(maxButtonSize);
        droneTypesButton.setMaximumSize(maxButtonSize);
        droneDynamicsButton.setMaximumSize(maxButtonSize);
        droneIDButton.setMaximumSize(maxButtonSize);
        refreshButton.setMaximumSize(maxButtonSize);
        returnPlus5Button.setPreferredSize(maxButtonSize);
        returnMinus5Button.setPreferredSize(maxButtonSize);

        //sortByCarriageWeight.setPreferredSize(maxButtonSize);
        sortByMaximumCarriage.setPreferredSize(maxButtonSize);
        sortBySpeed.setPreferredSize(maxButtonSize);
        sortByStatus.setPreferredSize(maxButtonSize);

        //ActionListeners for the buttons
        dashboardButton.addActionListener(new DashboardActionListener(this, DronesList, DroneTypesList,DroneDynamicsList));   //ActionListener for dashboardButton
        droneCatalogButton.addActionListener(new DroneCatalogActionListener(this));
        dronesButton.addActionListener(new DronesActionListener(this, DronesList));
        droneTypesButton.addActionListener(new DroneTypesActionListener(this, DroneTypesList));
        droneDynamicsButton.addActionListener(new DroneDynamicsActionListener(this,DroneDynamicsList));    // ActionListener für droneDynamicsButton
        droneIDButton.addActionListener(new DroneIDActionListener(this,DronesList));     // ActionListener für droneIDButton
        sortByCarriageWeight.addActionListener(new SortByCarriageWeight(this,DronesList));
        sortByMaximumCarriage.addActionListener(new SortByMaximumCarriage(this,DroneTypesList));
        sortBySpeed.addActionListener(new SortBySpeed(this,DroneTypesList));
        sortByStatus.addActionListener(new SortByStatus(this,DroneDynamicsList));
        refreshButton.addActionListener(new RefreshActionListener(this, DronesList, DroneTypesList,DroneDynamicsList ));
        // returnMinus5Button.addActionListener(new ReturnMinus5MinutesActionListener(this, DroneDynamicsList));

        this.getContentPane().add(panel, BorderLayout.WEST);  //add panel to frame
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);  //add mainPanel to frame
        this.getContentPane().add(eastPanel, BorderLayout.EAST);   //add eastPanel to the frame
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
        mainPanel.add(label1,BorderLayout.PAGE_START); //add label1 to mainPanel
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
    public JPanel getEastPanel(){
        return eastPanel;
    }
    public JPanel getMainPanel(){
        return mainPanel;
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
    public JButton getSortByCarriageWeight(){return sortByCarriageWeight;}
    public JButton getSortByMaximumCarriage(){return sortByMaximumCarriage;}
    public JButton getSortBySpeed(){return sortBySpeed;}
    public JButton getSortByStatus(){return sortByStatus;}
    public JButton getRefreshButton(){return refreshButton;}
    public JButton getReturnPlus5Button(){return returnPlus5Button;}
    public JButton getReturnMinus5Button(){return returnMinus5Button;}




}