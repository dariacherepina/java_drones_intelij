package Threads;

import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.*;
import java.io.IOException;

public class ThreadCheckRefresh  implements Runnable{
    private Drones drone = new Drones();
    private DroneTypes droneType = new DroneTypes();
    private DroneDynamics droneDynamic = new DroneDynamics();

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                if(drone.checkRefresh() ){
                    //refresh drone //TODO: popout window to push refresh button
                }else if (droneType.checkRefresh()){
                    //refresh droneType
                }else if(droneDynamic.checkRefresh()){
                    //refresh DroneDynamics
                    //pop out window with suggestion to click refresh button
                }
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}