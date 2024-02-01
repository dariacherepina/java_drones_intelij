package gui;

import drone.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Implements the ActionListener of the `sortSpeed` Button
 * @author Afnan Ismail
 */

public class SortBySpeed implements ActionListener {
    private Convert helper;
    private MyFrame frame;
    private ArrayList<DroneTypes> droneTypesList;

    public SortBySpeed(MyFrame frame, ArrayList<DroneTypes> droneTypesList){
        this.frame = frame;
        this.droneTypesList = droneTypesList;
        this.helper = new Convert();
    }

    /**
     * When clicking the `droneTypesButton` the droneTypesList can be sorted by maximum speed
     * @param e the event to be processed
     * @author Afnan Ismail & Daria Cherepina
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data = helper.convertArrayListToObjectDroneType(DroneTypes.sortSpeed(droneTypesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        frame.getTable().revalidate();
        frame.getTable().repaint();
    }
}