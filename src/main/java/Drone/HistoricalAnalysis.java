package Drone;
import Drone.DroneDynamics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.time.OffsetTime;

public class HistoricalAnalysis {

    // Meine Funktion für historische Analyse
    public static ArrayList<DroneDynamics> analyzeHistoricalData(
            ArrayList<DroneDynamics> historicalData, String Date) throws ParseException {
        // Die Funktion erwartet eine LIste mit 'Drone Dynamics' Objekten (aktuelle Datum, Zeitfenster in Minuten)

        ArrayList<DroneDynamics> result = new ArrayList<>(); // hier ich erstelle eine neue ArrayListe 'Result', die die Ergebnisse der historischen Analyse speichert
        OffsetDateTime currentDate = OffsetDateTime.parse(Date);

        // 'SimpleDateFormat' um datum  und Uhrzeitantrag richtig formatiert zu vearbeiten
        // das berechnet Startzeitpunkt basierend auf dem aktuellen Datum und der Zeitspanne
        int  timeWindowInMinutes = 2;
        long startTimeMillis = currentDate.getTime() - (timeWindowInMinutes * 60 * 1000);
        try {
            OffsetDateTime startTime = OffsetDateTime .(startTimeMillis); // Startzeitpunkt wird erstellt,milisek- wird in ein 'Date'-Objekt umgewandelt
            // Scleife um jedes 'DroneDynamics'-Objekt in historische datenliste zu überprüfen
            for (DroneDynamics dynamics : historicalData) {
                String timeStemp = dynamics.getTimestamp();
                OffsetDateTime dynamicsTimestamp = OffsetDateTime.parse(dynamics.getTimestamp());

                //Date dynamicsTimestamp = dateFormatDynamic.parse(timeStemp); // umwandlung in ein date-Objekt

                // Zum Überprüfen ob Zeitstemple  im gewüschten Zeitfenster ist
                if (dynamicsTimestamp.isAfter(startTime) && dynamicsTimestamp.before(currentDate)) {
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

