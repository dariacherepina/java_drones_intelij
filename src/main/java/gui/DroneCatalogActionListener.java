package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the ActionListener for the `droneCatalogButton`
 * @author Alina Winschel
 */

public class DroneCatalogActionListener implements ActionListener {
    private MyFrame frame;

    public DroneCatalogActionListener(MyFrame frame) {
        this.frame = frame;
    }

    /**
     * Displays the other buttons and changes the colour of the 'droneCatalogButton'
     * @param e the event to be processed
     * @author Alina Winschel
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getDroneCatalogButton().setBackground(Color.gray);

        frame.getLeftPanel().add(frame.getDronesButton());
        frame.getLeftPanel().add(frame.getDroneTypesButton());
        frame.getLeftPanel().add(frame.getDroneDynamicsButton());
        frame.getLeftPanel().add(frame.getDroneIDButton());

        frame.revalidate();
        frame.repaint();
    }
}