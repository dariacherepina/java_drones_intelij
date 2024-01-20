package Drone;

import API.APIConnection;
import API.APIEndpoints;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class Refreshable {
    static APIEndpoints apiEndpoints = new APIEndpoints(); // wieso nicht attribute sondern static
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    public abstract int checkOnlineCount();
    public abstract int checkOfflineCount();

    public abstract void refresh() throws IOException;


}
