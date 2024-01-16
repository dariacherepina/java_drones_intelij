package API;
import Drone.Drone;
import Drone.Drones;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DroneHistory {
    private static final int HISTORY_SIZE = 100; // Anpassen Sie die Größe je nach Ihren Anforderungen
    private List<DroneHistoryEntry> history;
    private APIEndpoints apiEndpoints;
    public DroneHistory() {
        this.history = new ArrayList<>();
    }
    public void addToHistory(List<Drones> droneList) {
        long timestamp = System.currentTimeMillis();
        DroneHistoryEntry entry = new DroneHistoryEntry(timestamp, new ArrayList<>(droneList));
        history.add(entry);

        // zum einschränken der größe der history
        if (history.size() > HISTORY_SIZE) {
            history.remove(0);
        }
    }

    public List<Drones> getHistoricalData(long targetTimestamp) {

        return history.stream()
                .filter(entry -> entry.getTimestamp() <= targetTimestamp)
                .flatMap(entry -> entry.getDrones().stream())
                .collect(Collectors.toList());
    }

    public void investigateDroneStatus() {
        // Berechnen Sie den Zeitstempel für fünf Minuten in der Vergangenheit
        long fiveMinutesAgo = System.currentTimeMillis() - (5 * 60 * 1000);

        // Holen Sie sich die Drohnendaten, die vor fünf Minuten gemeldet wurden
        List<Drones> dronesFromFiveMinutesAgo = getHistoricalData(fiveMinutesAgo);


        for (Drones drone : dronesFromFiveMinutesAgo) {
            // Überprüfen Sie die Bedingungen der Drohne...
            System.out.println("Drohne ID: " + drone.getId());
            System.out.println("Position: " + drone.getPosition());
            System.out.println("Batteriestand: " + drone.getBatteryLevel());
        }
    }

    public void performHistoricalAnalysis() {
        long targetTimestamp = System.currentTimeMillis() - (5 * 60 * 1000); // Vor fünf Minuten
        //List<Drones> currentDroneList = apiEndpoints.getDrones();
        String currentDroneList = String.valueOf(apiEndpoints.getDrones());


        DroneHistory droneHistory = new DroneHistory();
        droneHistory.addToHistory(currentDroneList);
        List<Drones> historicalData = droneHistory.getHistoricalData(targetTimestamp);
        // Führen Sie hier Ihre Analyse mit historischen Daten durch
    }

    public void addToHistory(String currentDroneList) {
    }

    private static class DroneHistoryEntry {
        private final long timestamp;
        private final List<Drones> drones;

        public DroneHistoryEntry(long timestamp, List<Drones> drones) {
            this.timestamp = timestamp;
            this.drones = drones;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public List<Drones> getDrones() {
            return drones;
        }
    }
}

