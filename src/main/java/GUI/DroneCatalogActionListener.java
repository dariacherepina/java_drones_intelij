package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DroneCatalogActionListener implements ActionListener {
    private MyFrame frame;
    public DroneCatalogActionListener(MyFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getDroneCatalogButton().setBackground(Color.gray);  //droneCatalogButton turns gray when you click it once

        frame.getPanel().add(frame.getDronesButton());// include buttons for Drones, DroneTypes and DroneDynamics to the Panel
        frame.getPanel().add(frame.getDroneTypesButton());
        frame.getPanel().add(frame.getDroneDynamicsButton());
        frame.getPanel().add(frame.getDroneIDButton());

        frame.revalidate();   //update frame in order to include the added buttons
        frame.repaint();
    }
}
