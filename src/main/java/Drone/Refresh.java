package Drone;

import API.APIConnection;
import API.APIEndpoints;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class Refresh {
    static APIEndpoints apiEndpoints = new APIEndpoints(); // wieso nicht attribute sondern static
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    public abstract int checkOnlineCount();
    public abstract int checkOfflineCount();
    public static void refresh(int offlineCount, int onlineCount) throws IOException {
        if(offlineCount < onlineCount){
            helper.dataStreamIn(apiEndpoints.getDronesUrl(100, offlineCount), "outputDrones");
            helper.dataStreamIn(apiEndpoints.getDroneTypesUrl(100, offlineCount), "outputDroneTypes");
            helper.dataStreamIn(apiEndpoints.getDroneDynamics(1000000, offlineCount), "outputDroneDynamics");
        }else if(offlineCount > onlineCount){
            LOGGER.warning("Online Number of Data is smaller than offline, can't be right");
        }else {
            LOGGER.info("Same amount of data. No Updates ");
        }

    }


}
