package threads;

import drone.DroneDynamics;
import drone.DroneTypes;
import drone.Drones;
import org.main.Main;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ThreadCheckRefresh checks whether the online count of the objects
 * and offline count of objects is different and then based on the result
 * it asks user to push refresh button if needed
 *
 * @author Daria Cherepina
 */

public class ThreadCheckRefresh implements Runnable {
    private Drones drone = new Drones();
    private DroneTypes droneType = new DroneTypes();
    private DroneDynamics droneDynamic = new DroneDynamics();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * To check if there is any updates for Drones, DroneTypes and DroneDynamicsn
     * if yes then we will see the pop out window that willa sk us to click refresh button
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(2000);
                if (drone.isRefreshChecked()) {
                    showRefreshWindow("Drone");
                    Thread.sleep(60000);
                } else if (droneType.isRefreshChecked()) {
                    showRefreshWindow("DroneTypes");
                    Thread.sleep(60000);
                } else if (droneDynamic.isRefreshChecked()) {
                    showRefreshWindow("DroneDynamics");
                    Thread.sleep(60000);
                }
            } catch (InterruptedException | IOException e) {
                LOGGER.log(Level.SEVERE, "Exception was interrupted or there was IOException ");
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to show pop out window with the message to push the refresh button
     *
     * @param dataType String The datatype of the data which needs to be refreshed
     */
    private void showRefreshWindow(String dataType) {
        JOptionPane.showMessageDialog(null,
                "Please click on Refresh button if you want to get new data for" + dataType,
                "Update", JOptionPane.INFORMATION_MESSAGE);
    }

}