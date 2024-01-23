package Drone;

import java.io.IOException;

public class Threads extends Thread{

    Drones drone = new Drones();
    DroneTypes droneType = new DroneTypes();
    DroneDynamics droneDynamic = new DroneDynamics();
@Override
    public void run(){
        while(true){
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
