package Drone;
import java.util.List;
import API.APIConnection;
import API.APIEndpoints;

public class DroneUIManager {
    private APIConnection apiConnection;

    public DroneUIManager(APIConnection apiConnection) {
        // ich überegebe die bereits erstellte istanz von ApiConnection
        this.apiConnection = apiConnection;
    }


    // Damit möchte ich die verschiedenen Daten abrufen und aktualisieere der Benutzeroberfläche
    public <DroneCatalogEntry> void fetchDataAndPopulateUI() {
      //?  List<Drone> activeDrones = apiConnection.getActiveDrones();
        /*List<DroneTypes> DroneTypes =apiConnection.getDroneTypes();
        mit drontypes klappt das irgendwie nicht ? muss ich nochmal schauen
         */
     //   List<DroneCatalogEntry> droneCatalog = apiConnection.getDroneCatalog();

    }

    // ZUm updaten der Api-Daten
    public void updateDataFromAPI() {
      // ?  apiConnection.updateData();
        fetchDataAndPopulateUI(); // Aktualisierte Daten in der Benutzeroberfläche anzeigen
    }
}
        // Funktionen für die Historical Analysis
