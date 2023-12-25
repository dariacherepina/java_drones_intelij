package Drone;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class Convert {
    public static void Input2Object(String input) {
        try {
            JSONObject inputFile = new JSONObject(input);
            JSONArray jsonFile = inputFile.getJSONArray("results");
            ArrayList<Drone> dronesList = new ArrayList<>();
            for (int i = 0; i < jsonFile.length(); ++i) {
                JSONObject resultItem = jsonFile.getJSONObject(i);
                Drone drone = new Drone(
                        resultItem.getInt("id"),
                        resultItem.getString("dronetype"),
                        resultItem.getString("created"),
                        resultItem.getString("serialnumber"),
                        resultItem.getInt("carriage_weight"),
                        resultItem.getString("carriage_type")
//                        resultItem.getString("manufacturer"),
//                        resultItem.getString("typename"),
//                        resultItem.getInt("weight"),
//                        resultItem.getInt("max_speed"),
//                        resultItem.getInt("battery_capacity"),
//                        resultItem.getInt("control_range"),
//                        resultItem.getInt("max_carriage")
                );
                dronesList.add(drone);
                System.out.println("Drone was created");
            }
            for (Drone drone : dronesList) {
                System.out.println(drone.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
