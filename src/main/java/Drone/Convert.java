package Drone;

import com.google.gson.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;


public class Convert {

    public static void Input2Object(String input) {
        try {
            Gson gson = new Gson();
            JsonElement inputJson = JsonParser.parseString(input);
            JsonObject inputObject = inputJson.getAsJsonObject();
            JsonArray inputArray = inputObject.getAsJsonArray("results");
            //wrong input
            if (!inputArray.isJsonArray()) {
                throw new IllegalArgumentException("Input is not a JSON array");
            }
            ArrayList<Object> parsedResult = new ArrayList<>();
            for (JsonElement element : inputArray) {
                if (element.isJsonObject()) {
                    JsonObject jsonObject = element.getAsJsonObject();
                    if (jsonObject.has("dronetype")) {
                        parsedResult.add(gson.fromJson(jsonObject, Drones.class));
                    } else if (jsonObject.has("drone")) {
                        DroneDynamics droneDynamics = gson.fromJson(jsonObject, DroneDynamics.class);
                        droneDynamics.setDrone(jsonObject.get("drone").getAsString());
                        parsedResult.add(droneDynamics);
                    } else {
                        parsedResult.add(gson.fromJson(jsonObject, DroneTypes.class));
                    }
                }
            }
            //Now parsedResults contains the appropriate objects

            Collections.sort(parsedResult, (o1, o2) -> {
                int id1 = 0;
                int id2 = 0;

                if (o1 instanceof DroneTypes || o1 instanceof Drones) {
                    id1 = (o1 instanceof Drones) ? ((Drones) o1).getId() : ((DroneTypes) o1).getId();
                }

                if (o2 instanceof DroneTypes || o2 instanceof Drones) {
                    id2 = (o2 instanceof Drones) ? ((Drones) o2).getId() : ((DroneTypes) o2).getId();
                }
                if (o1 instanceof DroneDynamics && o2 instanceof DroneDynamics){
                    try {
                        id1 = ((DroneDynamics) o1).getId();
                        id2 = ((DroneDynamics) o2).getId();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }

                return Integer.compare(id1, id2);
            });


            for (Object obj : parsedResult) System.out.println(obj.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




