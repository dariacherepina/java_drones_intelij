package gui;

import drone.Convert;
import drone.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Implements the ActionListener of the `sortCarriageWeight` Button
 * @author Afnan Ismail
 */

public class SortByCarriageWeight implements ActionListener {
    private Convert helper;
    private MyFrame frame;
    private ArrayList<Drones> dronesList;

    public SortByCarriageWeight(MyFrame frame, ArrayList<Drones> dronesList) {
        this.frame = frame;
        this.dronesList = dronesList;
        this.helper = new Convert();
    }

    /**
     * When clicking the `dronesButton` the dronesList can be sorted by carriage weight
     * @param e the event to be processed
     * @author Afnan Ismail & Daria Cherepina
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.convertArrayListToObjectDrones(Drones.sortCarriageWeight(dronesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        frame.getTable().revalidate();
        frame.getTable().repaint();
    }
}