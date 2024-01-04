package Drone;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Convert {
    public enum Type {
        DRONES("drones"),
        DRONETYPES("drone types");

        Type(Object namesOfType){
        }
    }

        public static void Input2Object(String input, Type type) {
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
                for(JsonElement element : inputArray){
                    if(element.isJsonObject()){
                        JsonObject jsonObject = element.getAsJsonObject();
                        if(jsonObject.has("dronetype")){
                            parsedResult.add(gson.fromJson(jsonObject, Drones.class));
                        }else {
                            parsedResult.add(gson.fromJson(jsonObject, DroneTypes.class));
                        }
                    }
                }
                 //Now parsedResults contains the appropriate objects
                Collections.sort(parsedResult, new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        int id1 = (o1 instanceof Drones) ? ((Drones) o1).getId() : ((DroneTypes) o1).getId();
                        int id2 = (o2 instanceof Drones) ? ((Drones) o2).getId() : ((DroneTypes) o2).getId();
                        return Integer.compare(id1, id2);
                    }
                });

                for (Object obj : parsedResult) {

                    System.out.println(obj.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




