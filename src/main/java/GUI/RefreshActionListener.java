package GUI;

import API.Stream;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import java.io.IOException;
import java.util.ArrayList;
import static GUI.MyFrame.helper;

public class RefreshActionListener {
    private void updateTables(ArrayList<Drones> DronesList, ArrayList<DroneTypes> DroneTypesList, ArrayList<DroneDynamics> DroneDynamicsList) throws IOException {
        Stream.fetchData();
        DronesList = null;
        DroneTypesList = null;
        DroneDynamicsList = null;
        DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
        DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
        DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
        helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
        //updateTables (DronesList, DroneTypesList, DroneDynamicsList) aufrufen und dann weiter mit die drei ArrayListe arbeiten(Tabelle ersetzen)
    //TODO: refresh button -> to replace tables with neu data/ is list changed or should i return it, if yes, how ?
    }
}
