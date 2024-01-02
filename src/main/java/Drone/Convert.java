package Drone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Convert {
    public static void Input2Object(String input) {
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Wrapper>(){}.getType();
            Wrapper wrapper = gson.fromJson(input, type);
            ArrayList<Drone> dronesList = wrapper.results;
            for (Drone drone : dronesList) {
                System.out.println(drone.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Wrapper {
    ArrayList<Drone> results;
}
