package GUI;

import Drone.DroneDynamics;
import Drone.Sortable;
import org.main.Main;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SortByStatus implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Convert helper;


    private MyFrame frame;
    private ArrayList<DroneDynamics> DroneDynamicsList;

    public SortByStatus(MyFrame frame, ArrayList<DroneDynamics> DroneDynamicsList){
        this.frame = frame;
        this.DroneDynamicsList = DroneDynamicsList;
        this.helper = new Convert();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data = helper.ArrayList2ObjectDroneDynamics(Sortable.sortStatus(DroneDynamicsList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));
        frame.getTable().repaint();



    }
}