package Drone;
import Drone.DroneDynamics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoricalAnalysis {

    // Meine Funktion für historische Analyse
    public static ArrayList<DroneDynamics> analyzeHistoricalData(
            ArrayList<DroneDynamics> historicalData, Date currentDate, int timeWindowInMinutes) {

        ArrayList<DroneDynamics> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        // das berechnet Startzeitpunkt basierend auf dem aktuellen Datum und der Zeitspanne
        long startTimeMillis = currentDate.getTime() - (timeWindowInMinutes * 60 * 1000);

        try {
            Date startTime = new Date(startTimeMillis);

            for (DroneDynamics dynamics : historicalData) {
                Date dynamicsTimestamp = dateFormat.parse(dynamics.getTimestamp());

                // Zum Überprüfen ob Zeit im gewüschten Zeitfenster ist
                if (dynamicsTimestamp.after(startTime) && dynamicsTimestamp.before(currentDate)) {
                    result.add(dynamics);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
}

