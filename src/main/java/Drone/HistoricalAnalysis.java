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
        // Die Funktion erwartet eine LIste mit 'Drone Dynamics' Objekten (aktuelle Datum, Zeitfenster in Minuten)

        ArrayList<DroneDynamics> result = new ArrayList<>(); // hier ich erstelle eine neue ArrayListe 'Result', die die Ergebnisse der historischen Analyse speichert
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        // 'SimpleDateFormat' um datum  und Uhrzeitantrag richtig formatiert zu vearbeiten
        // das berechnet Startzeitpunkt basierend auf dem aktuellen Datum und der Zeitspanne
        long startTimeMillis = currentDate.getTime() - (timeWindowInMinutes * 60 * 1000);

        try {
            Date startTime = new Date(startTimeMillis); // Startzeitpunkt wird erstellt,milisek- wird in ein 'Date'-Objekt umgewandelt
            // Scleife um jedes 'DroneDynamics'-Objekt in historische datenliste zu überprüfen
            for (DroneDynamics dynamics : historicalData) {
                Date dynamicsTimestamp = dateFormat.parse(dynamics.getTimestamp()); // umwandlung in ein date-Objekt

                // Zum Überprüfen ob Zeitstemple  im gewüschten Zeitfenster ist
                if (dynamicsTimestamp.after(startTime) && dynamicsTimestamp.before(currentDate)) {
                    result.add(dynamics);
                }
            }
            // Fehlerbehbung für das Parsen von Daten
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result; // Rückgabe der Ergebnisliste
    }
}

