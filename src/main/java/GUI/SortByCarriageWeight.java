package GUI;

import Drone.Convert;
import Drone.Drones;
import Drone.Sortable;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SortByCarriageWeight implements ActionListener {
    private Convert helper;
    private MyFrame frame;
    private ArrayList<Drones> dronesList;

    public SortByCarriageWeight(MyFrame frame, ArrayList<Drones> dronesList){
        this.frame = frame;
        this.dronesList = dronesList;
        this.helper = new Convert();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.ArrayList2ObjectDrones(Sortable.sortCarriageWeight(dronesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));
        frame.getTable().repaint();
    }
}
