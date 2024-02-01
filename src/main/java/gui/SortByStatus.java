package gui;

import drone.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * implements the ActionListener of the sortByStatusButton
 * @author Afnan Ismail
 */

public class SortByStatus implements ActionListener {
    private Convert helper;
    private MyFrame frame;
    private ArrayList<DroneDynamics> droneDynamicsList;

    public SortByStatus(MyFrame frame, ArrayList<DroneDynamics> droneDynamicsList){
        this.frame = frame;
        this.droneDynamicsList = droneDynamicsList;
        this.helper = new Convert();
    }

    /**
     * when clicking the droneDynamicsButton the droneDynamicsList can be sorted by status
     * @param e the event to be processed
     * @author Afnan Ismail & Daria Cherepina
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data = helper.convertArrayListToObjectDroneDynamics(DroneDynamics.sortStatus(droneDynamicsList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        frame.getTable().revalidate();
        frame.getTable().repaint();
    }
}