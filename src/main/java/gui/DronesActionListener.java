package gui;

import drone.Drones;
import drone.Convert;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Implements the ActionListener for the `dronesButton`
 * @author Alina Winschel & Daria Cherepina
 */

public class DronesActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> dronesList;
    private Convert helper;

    public DronesActionListener(MyFrame frame, ArrayList<Drones> dronesList) {
        this.frame = frame;
        this.dronesList = dronesList;
        this.helper = new Convert();
    }

    /**
     * Adjusts the Sort-Buttons and
     * updates the GUI to display the 'DRONES' label and populates the table
     * @param e the event to be processed
     * @author Alina Winschel & Daria Cherepina
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONES");

        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortByStatus());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().add(frame.getSortByCarriageWeight());

        frame.getImageTablePanel().revalidate();
        frame.getImageTablePanel().repaint();

        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.convertArrayListToObjectDrones(dronesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));
    }
}