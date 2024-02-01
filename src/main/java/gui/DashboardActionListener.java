package gui;

import drone.Drones;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Implements the ActionListener for the`dashboardButton`
 * @author Alina Winschel
 */

public class DashboardActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> dronesList;

    public DashboardActionListener(MyFrame frame, ArrayList<Drones> dronesList) {
        this.frame = frame;
        this.dronesList = dronesList;
    }

    /**
     * Removes the Sort-Buttons from the`dashboardButton`
     * and updates the GUI to display the 'DRONE OVERVIEW' label and populates the table
     * @param e the event to be processed
     * @author Alina Winschel
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE OVERVIEW");

        frame.getPanelSort().remove(frame.getSortByCarriageWeight());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().remove(frame.getSortByStatus());

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
        frame.getTable().setModel(new DefaultTableModel(data, columns));
    }
}