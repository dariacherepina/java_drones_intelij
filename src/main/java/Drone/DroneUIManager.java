package Drone;
import java.util.List;
import API.APIConnection;
import API.APIEndpoints;

public class DroneUIManager {
    private APIConnection apiConnection ;

    public DroneUIManager(APIConnection apiConnection){
        // ich überegebe die bereits erstellte istanz von ApiConnection
        this.apiConnection = apiConnection;
    }


    // Damit möchte ich die verschiedenen Daten abrufen und aktualisieere die Benutzeroberfläche
    public <DroneCatalogEntry> void fetchDataAndPopulateUI() {
        List<Drone> activeDrones = apiConnection.getActiveDrones();
        /*List<DroneTypes> DroneTypes =apiConnection.getDroneTypes();
        mit drontypes klappt das irgendwie nicht ? muss ich nochmal schauen
         */
        List<DroneCatalogEntry> droneCatalog = apiConnection.getDroneCatalog();

    }
        // ZUm updaten der Api-Daten
        public void updateDataFromAPI(){
            apiConnection.updateData();
            fetchDataAndPopulateUI(); // Aktualisierte Daten in der Benutzeroberfläche anzeigen
        }

        // Funktionen für die Historical Analysis
    public void performHistoricalAnalysis() {
        List<Drone.DroneStatus> HistoricalData = apiConnection.getHistoricalData(5);
        //Funktion um die Daten von vor 5 Minuten abzurufen
    }

    //Durschnittliche Batterielaufzeit für eine bestimmten Zeitraum berechnen
    public double calculateAverageBatteryLife(List<DroneStatus> historicalData) {
        double totalBatteryLife = 0.0;
        int count = 0;

        for (DroneStatus status : historicalData) {
            totalBatteryLife += status.getBatteryLife();
            count++;
        }

        return count > 0 ? totalBatteryLife / count : 0.0;
    }
}
