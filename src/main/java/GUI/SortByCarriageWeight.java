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
    private ArrayList<Drones> DronesList;

    public SortByCarriageWeight(MyFrame frame, ArrayList<Drones> DronesList){
        this.frame = frame;
        this.DronesList = DronesList;
        this.helper = new Convert();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.convertArrayListToObjectDrones(Sortable.sortCarriageWeight(DronesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));
        frame.getTable().repaint();


    }
}