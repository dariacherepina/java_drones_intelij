package gui;

import drone.Convert;
import drone.DroneDynamics;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * implements ActionListener for DroneDynamicsButton
 * @author Alina Winschel & Daria Cherepina
 */

public class DroneDynamicsActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<DroneDynamics> droneDynamicsList;
    private Convert helper;

    public DroneDynamicsActionListener(MyFrame frame, ArrayList<DroneDynamics> droneDynamicsList) {
        this.frame = frame;
        this.droneDynamicsList = droneDynamicsList;
        this.helper = new Convert();
    }

    /**
     * adjust Sort-Buttons
     * updates GUI to display 'DRONE DYNAMICS' label and populates table
     * @param e the event to be processed
     * @author Alina Winschel & Daria Cherepina
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE DYNAMICS");

        frame.getPanelSort().add(frame.getSortByStatus());
        frame.getPanelSort().remove(frame.getSortByCarriageWeight());
        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortBySpeed());

        frame.getImageTablePanel().revalidate();
        frame.getImageTablePanel().repaint();

        String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data = helper.convertArrayListToObjectDroneDynamics(droneDynamicsList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));
    }
}