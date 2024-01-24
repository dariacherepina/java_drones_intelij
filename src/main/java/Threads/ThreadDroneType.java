package Threads;

import API.APIEndpoints;
import API.Stream;
import Drone.Convert;
import Drone.DroneTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import API.Stream;
import Exception.InvalidFileNameException;
import org.main.Main;

public class ThreadDroneType implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Convert helper = new Convert();

    @Override
    public void run() {
//        try {
//            int count = APIEndpoints.getDroneTypesUrl(20, 19).get("count").getAsInt();
//            Stream.dataStreamIn(APIEndpoints.getDroneTypesUrl(count, 0), "outputDroneTypes");
//        } catch (IOException e) {
//            LOGGER.warning("IOException");
//            throw new RuntimeException(e);
//        } catch (InvalidFileNameException e) {
//            LOGGER.warning("InvalidFileNameException");
//            throw new RuntimeException(e);
//        }

    }
}
