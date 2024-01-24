package Threads;

import API.APIEndpoints;
import Drone.Convert;
import org.main.Main;

import java.io.IOException;
import java.util.logging.Logger;



import API.Stream;
import Exception.InvalidFileNameException;
import org.main.Main;

public class ThreadDroneDynamic implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Convert helper = new Convert();


    @Override
    public void run() {
//        try {
//            int count = APIEndpoints.getDroneDynamics(36025, 36024).get("count").getAsInt();
//            Stream.dataStreamIn(APIEndpoints.getDroneDynamics(count, 0), "outputDroneDynamics");
//
//        } catch (IOException e) {
//            LOGGER.warning("IOException");
//            throw new RuntimeException(e);
//        } catch (InvalidFileNameException e) {
//            LOGGER.warning("InvalidFileNameException");
//            throw new RuntimeException(e);
//        }
    }
}
