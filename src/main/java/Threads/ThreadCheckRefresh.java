package Threads;

import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.*;
import java.io.IOException;

public class ThreadCheckRefresh implements Runnable {
    private Drones drone = new Drones();
    private DroneTypes droneType = new DroneTypes();
    private DroneDynamics droneDynamic = new DroneDynamics();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(2000);
                if (drone.checkRefresh()) {
                    JOptionPane.showMessageDialog(null, "Please click on Refresh button if you want to get new data for Drones", "Update", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(60000);
                } else if (droneType.checkRefresh()) {
                    JOptionPane.showMessageDialog(null, "Please click on Refresh button if you want to get new data for DroneTypes", "Update", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(60000);
                } else if (droneDynamic.checkRefresh()) {
                    JOptionPane.showMessageDialog(null, "Please click on Refresh button if you want to get new data for DroneDynamics", "Update", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(60000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
