package drone;

import api.APIConnection;

import java.io.IOException;
import java.util.logging.Logger;

public interface Refreshable {
    Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    int checkOnlineCount();
    int checkOfflineCount();
    boolean isRefreshChecked() throws IOException;


}