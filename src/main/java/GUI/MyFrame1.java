package GUI;

import Drone.DroneTypes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//JFrame = a GUI window to add components to
public class MyFrame1 extends JFrame { //MyFrame1 is the child class of JFrame

    private JTable table;
    private JPanel LeftPanel;
    private JLabel label;
    private JButton dronesButton;
    private JButton droneTypesButton;
    private JButton droneDynamicsButton;

    public MyFrame1() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits out of application
        this.setResizable(false); //prevents frame from being resized
        this.setExtendedState(MAXIMIZED_BOTH); //shows the Gui in full screen
        this.setTitle("Drones simulator"); //sets title of the frame
        this.setLayout(new BorderLayout()); //Layout of the frame
        this.getContentPane().setBackground(Color.BLACK); //background of the frame


        ImageIcon image = new ImageIcon("drone.jpg"); //creates an ImageIcon
        this.setIconImage(image.getImage()); //changes Icon of the frame

        LeftPanel = new JPanel(); //creates panel in the frame
        LeftPanel.setBackground(Color.PINK); //background of the panel
        LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));//puts the components in the panel from top to bottom
        this.add(LeftPanel, BorderLayout.WEST); //puts the panel on the left of the frame

        JButton dashboardButton = createButton("DASHBOARD"); //adds buttons to the panel
        JButton droneCatalogButton = createButton("DRONE CATALOG");
        dronesButton = createButton("DRONE");
        droneTypesButton = createButton("DRONE TYPES");
        droneDynamicsButton = createButton("DRONE DYNAMICS");

        JButton RefreshButton = new JButton("REFRESH");
        RefreshButton.setBackground(Color.white);
        RefreshButton.setSize(140, 40);

        JButton ReturnButton = new JButton("RETURN5MINUTES");
        ReturnButton.setBackground(Color.white);
        ReturnButton.setSize(140, 40);
        LeftPanel.add(ReturnButton);

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredSize(new Dimension(800, 200));
        this.getContentPane().add(scrollPane);
        this.add(scrollPane);

        createLabel("DASHBOARD");
        this.add(LeftPanel);
        this.setVisible(true); //makes frame visible


        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "DroneType", "Created", "SerialNumber", "CarraigeWeight", "CarraigeType"}; //JTable Dashboard
                Object[][] data = {};
//            for (int i = 0; i < DronesList.size(); i++){
//                data[i][0] = DronesList.get(i).getId();
//                data[i][1] = DronesList.get(i).getDronetype().getManufacturer();
//                data[i][2] = DronesList.get(i).getCreated();
//                data[i][3] = DronesList.get(i).getSerialnumber();
//                data[i][4] = DronesList.get(i).getCarriage_weight();
//                data[i][5] = DronesList.get(i).getCarriage_type();
//            }
                table.setModel(new DefaultTableModel(data, columns));
                int j = 0;
            }
        });

        droneDynamicsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // include Buttons Drones, DroneTypes und DroneDynamics to panel
                LeftPanel.add(dronesButton);
                MyFrame1.this.add(dronesButton);


                LeftPanel.add(droneTypesButton);
                MyFrame1.this.add(droneTypesButton);


                LeftPanel.add(droneDynamicsButton);
                MyFrame1.this.add(droneDynamicsButton);

                //update MyFrame to show included buttons

                MyFrame1.this.LeftPanel.revalidate();
                MyFrame1.this.LeftPanel.repaint();
                setLabel("Drone Catalog");
                droneCatalogButton.setVisible(false); //after one click button disappears


            }

        });

        dronesButton.addActionListener(new ActionListener() {    // ActionListener für dronesButton
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "DroneType", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
                setLabel("Drones");
            }

        });

        droneTypesButton.addActionListener(new ActionListener() {  // ActionListener für droneTypesButton
            public void actionPerformed(ActionEvent e) {
                setLabel("DroneTypes");
                String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));


            }

        });

        droneDynamicsButton.addActionListener(new ActionListener() {  // ActionListener für droneDynamicsButton
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"ID", "TimeStamp", "Drone", "Speed", "AlignmentRoll", "ControlRange", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
                Object[][] data = {};
                table.setModel(new DefaultTableModel(data, columns));
                setLabel("Drone Dynamics");
            }

        });

    }

    private JButton createButton(String text){ //container = panel
        JButton button = new JButton(text);
        button.setBackground(Color.white); //color of button
        Dimension dimension = new Dimension(140,40);
        button.setPreferredSize(dimension);
        return  button;
    }

    private  void createLabel(String text){
       label = new JLabel(text);
       ImageIcon image2 = new ImageIcon("drones.jpg");
       label.setIcon(image2);
       label.setHorizontalTextPosition(JLabel.CENTER); //sets text left, center, right of ImageIcon
       label.setVerticalTextPosition(JLabel.TOP); //sets text top, center, bottom of ImageIcon
       label.setForeground(Color.ORANGE); //sets font color of label
       label.setIconTextGap(30); //sets gap of text to image
       label.setVerticalAlignment(JLabel.CENTER);//sets vertical position of icon + text within frame
       label.setHorizontalAlignment(JLabel.CENTER); //sets horizontal position of icon +text within frame
        this.add(label,BorderLayout.AFTER_LAST_LINE);

    }

    private void setLabel(String labelText){

        label.setText(labelText);
    }
    public static void main(String[]args){
        MyFrame1 myFrame1 = new MyFrame1(); //creates an instance of the MyFrame class-> with this. you address myFrame
    }




}
