package Drone;

import API.APIConnection;
import API.APIEndpoints;

import java.io.IOException;
import java.util.logging.Logger;

public abstract class Refreshable {

    public abstract int checkOnlineCount();
    public abstract int checkOfflineCount();

    public abstract void refresh() throws IOException;


}