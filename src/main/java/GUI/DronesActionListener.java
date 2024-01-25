package GUI;

import Drone.Drones;
import Drone.Convert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DronesActionListener implements ActionListener{
    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    private Convert helper;
    private JPanel panelSort;

    public DronesActionListener(MyFrame frame, ArrayList<Drones> DronesList){
        this.frame = frame;
        this.DronesList = DronesList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONES");
        // frame.getEastPanel().add(frame.getRefreshButton());

        panelSort = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSort.setBackground(Color.BLACK);
        frame.getMainPanel().add(panelSort,BorderLayout.CENTER);
        panelSort.add(frame.getSortByCarriageWeight());

        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();





        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.ArrayList2ObjectDrones(DronesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }

}