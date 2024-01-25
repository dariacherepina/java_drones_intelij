package Drone;

import API.APIEndpoints;
import API.Stream;

import java.io.IOException;

public abstract class Refresh {

    public abstract int checkOnlineCount();
    public abstract int checkOfflineCount();
    public abstract boolean checkRefresh()  throws IOException;


}