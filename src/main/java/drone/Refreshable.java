package drone;

import java.io.IOException;

public interface Refreshable {

    int checkOnlineCount(); //filename, etc

    int checkOfflineCount();

    boolean checkRefresh() throws IOException;


}