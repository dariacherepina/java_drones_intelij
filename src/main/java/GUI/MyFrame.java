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

public class MyFrame extends JFrame {
    static Convert helper = new Convert();
    private JLabel label1;
    private JPanel imageTablePanel;
    private JPanel refreshPanel;
    private JPanel leftPanel;
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Drones simulator");

        ImageIcon imageLeftTitle = new ImageIcon("drone.jpg");
        this.setIconImage(imageLeftTitle.getImage());
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        imageTablePanel = new JPanel();
        imageTablePanel.setBackground(Color.BLACK);
        imageTablePanel.setLayout(new BorderLayout());

        panelSort = new JPanel();
        panelSort.setLayout(new BoxLayout(panelSort, BoxLayout.Y_AXIS));
        panelSort.setBackground(Color.BLACK);
        panelSort.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        imageTablePanel.add(panelSort, BorderLayout.WEST);

        JPanel panelSortCenter = new JPanel();
        panelSortCenter.setLayout(new BorderLayout());
        panelSortCenter.setBackground(Color.BLACK);
        panelSort.add(panelSortCenter, BorderLayout.CENTER);

        String[] columns = {"ID", "TypeName", "Manufacturer"};
        int numRows = dronesList.size();
        Object[][] data = new Object[numRows][columns.length];

        int minSize = dronesList.size();
        for (int i = 0; i < minSize; i++) {
            Drones drone = dronesList.get(i);
            data[i][0] = drone.getId();
            data[i][1] = drone.getDroneType().getTypeName();
            data[i][2] = drone.getDroneType().getManufacturer();
        }

        table.setModel(new DefaultTableModel(data, columns));
        table.setBackground(Color.DARK_GRAY);
        table.setForeground(Color.white);

        DefaultTableCellRenderer cellRendererForWholeTable = new DefaultTableCellRenderer();
        cellRendererForWholeTable.setBackground(Color.DARK_GRAY);
        cellRendererForWholeTable.setForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);

        int columnCount = table.getColumnModel().getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRendererForWholeTable);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        imageTablePanel.add(scrollPane, BorderLayout.SOUTH);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.black);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.BLACK);
        this.add(emptyPanel, BorderLayout.NORTH);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBackground(Color.white);
        leftPanel.add(dashboardButton);

        droneCatalogButton = new JButton("DroneCatalog");
        droneCatalogButton.setBackground(Color.white);
        leftPanel.add(droneCatalogButton);

        dronesButton = new JButton("Drones");
        dronesButton.setBackground(Color.white);

        droneTypesButton = new JButton("DroneTypes");
        droneTypesButton.setBackground(Color.white);

        droneDynamicsButton = new JButton("DroneDynamics");
        droneDynamicsButton.setBackground(Color.white);

        droneIDButton = new JButton("DroneID");
        droneIDButton.setBackground(Color.white);

        refreshPanel = new JPanel(); //includes refreshButton
        refreshPanel.setBackground(Color.BLACK);
        refreshPanel.setLayout(new BoxLayout(refreshPanel, BoxLayout.Y_AXIS));

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(Color.white);
        refreshPanel.add(refreshButton);

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

        Dimension maxButtonSize = new Dimension(240, 40);
        dashboardButton.setMaximumSize(maxButtonSize);
        droneCatalogButton.setMaximumSize(maxButtonSize);
        dronesButton.setMaximumSize(maxButtonSize);
        droneTypesButton.setMaximumSize(maxButtonSize);
        droneDynamicsButton.setMaximumSize(maxButtonSize);
        droneIDButton.setMaximumSize(maxButtonSize);
        refreshButton.setMaximumSize(maxButtonSize);
        returnPlus5Button.setMaximumSize(maxButtonSize);
        returnMinus5Button.setMaximumSize(maxButtonSize);

        /**
         * sets up the ActionListener for various buttons
         * initializes the layout of the frame for the Drones Simulator application
         * each button is associated with a specific ActionListener to handle user interactions
         * the frame includes panels for navigation buttons, the main content, and additional components
         */
        dashboardButton.addActionListener(new DashboardActionListener(this, dronesList, droneTypesList, droneDynamicsList));
        droneCatalogButton.addActionListener(new DroneCatalogActionListener(this));
        dronesButton.addActionListener(new DronesActionListener(this, dronesList));
        droneTypesButton.addActionListener(new DroneTypesActionListener(this, droneTypesList));
        droneDynamicsButton.addActionListener(new DroneDynamicsActionListener(this, droneDynamicsList));
        droneIDButton.addActionListener(new DroneIDActionListener(this, dronesList));
        sortByCarriageWeight.addActionListener(new SortByCarriageWeight(this, dronesList));
        sortByMaximumCarriage.addActionListener(new SortByMaximumCarriage(this, droneTypesList));
        sortBySpeed.addActionListener(new SortBySpeed(this, droneTypesList));
        sortByStatus.addActionListener(new SortByStatus(this, droneDynamicsList));
        refreshButton.addActionListener(new RefreshActionListener(this, dronesList, droneTypesList, droneDynamicsList));

        this.getContentPane().add(leftPanel, BorderLayout.WEST);
        this.getContentPane().add(imageTablePanel, BorderLayout.CENTER);
        this.getContentPane().add(refreshPanel, BorderLayout.EAST);
        createLabel();
        this.setVisible(true);
    }
//TODO Daria fragen
    /**
     *
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
    private void createLabel() {
        label1 = new JLabel("DRONE OVERVIEW");
        ImageIcon image2 = new ImageIcon("drones.png");
        label1.setIcon(image2);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setForeground(Color.WHITE);
        label1.setIconTextGap(40);
        label1.setVerticalAlignment(JLabel.CENTER);
        label1.setHorizontalAlignment(JLabel.CENTER);
        imageTablePanel.add(label1, BorderLayout.CENTER);
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

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getImageTablePanel() {
        return imageTablePanel;
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