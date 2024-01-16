package API;

import java.net.HttpURLConnection;

public class APIConnectionNew {

     private static final String USER_AGENT ="Mozilla Firefox Awesome version";

     private static final String ENDPOINT_URL ="https://dronesim.facets-labs.com/api/drones/?format=json";
     // Der URL wird verwendet um die Daten von dem server abzurufen
    // Die beiden Zeilen definieren zwei KOnstanten in einer KLass, ich kann sie nicht au?erhalb der klasse verändern

    private static final String TOKEN = "Token 1586b43740b3c8b3686b31e2dc1cf1b4273b838f";
    private HttpURLConnection connection; // KLasse die für HTTP-Verbindungen entwickelt wurde
}
