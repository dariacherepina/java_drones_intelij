package GUI;

import Drone.Drones;
import Drone.Convert;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
     * adjust Sort-Buttons
     * updates GUI to display 'DRONES' label and populates table
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONES");

        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortByStatus());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().add(frame.getSortByCarriageWeight());
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();

        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.convertArrayListToObjectDrones(dronesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }
}