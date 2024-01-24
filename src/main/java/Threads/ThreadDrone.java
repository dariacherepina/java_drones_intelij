package Threads;

import API.APIEndpoints;
import API.Stream;
import Drone.Convert;
import Drone.Drones;
import org.main.Main;

import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.logging.Logger;


import API.Stream;
import Exception.InvalidFileNameException;
import org.main.Main;

public class ThreadDrone implements Runnable{  //TODO: implements Runnable cleaner
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Convert helper = new Convert();

    @Override
    public void run(){
//        try {
//            int count = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
//            Stream.dataStreamIn(APIEndpoints.getDronesUrl(count, 0), "outputDrones");
//        } catch (IOException e) {
//            LOGGER.warning("IOException");
//            throw new RuntimeException(e);
//        } catch (InvalidFileNameException e) {
//            LOGGER.warning("InvalidFileNameException");
//            throw new RuntimeException(e);
//        }
    }

}
