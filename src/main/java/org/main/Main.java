package org.main;

import API.APIEndpoints;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    // Define constants

    static Convert helper = new Convert();

    public static void main(String[] args) {
        APIEndpoints droneIndivData = new APIEndpoints();
//        System.out.println(droneIndivData.getDroneTypesIndivData(55));
//        String drone55 = droneIndivData.getDroneTypesIndivData(55);
//        System.out.println(droneIndivData.getDroneTypes());
//        System.out.println(droneIndivData.getDronesIndivData(65));
//        System.out.println(droneIndivData.getDroneDynamics());
//        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json
//        ArrayList<Drones> DronesList = helper.Input2DronesObject(droneIndivData.getDrones());
//        ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(droneIndivData.getDroneDynamics());
//        helper.addDroneDynamics(DronesList);
//        System.out.println(DronesList);
//        System.out.println(DronesList.get(0));
//        helper.ArrayList2ObjectDroneType(DroneTypesList);
//        System.out.println(DroneTypesList);
//        System.out.println(DroneDynamicsList);

//        ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(droneIndivData.getDroneTypes());
//        Object[][] DroneTypeObj= helper.ArrayList2ObjectDroneType(DroneTypesList);
//
        int droneId = 85;
        ArrayList<Drones> DronesListFull = helper.Input2DronesObjectIndiv(droneIndivData.getDronesIndivData(droneId));
        Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
        System.out.println(Arrays.deepToString(data));
//        try {
//            write2DArrayToFile(DroneTypeObj);
//            //read2DArrayToFile(DroneTypeObj);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }




    }
    //:TODO: File stream for gui ?
//    public static String[][] convertObjectArrayToStringArray(Object[][] objArray) {
//        String[][] stringArray = new String[objArray.length][];
//        for (int i = 0; i < objArray.length; i++) {
//            stringArray[i] = new String[objArray[i].length];
//            for (int j = 0; j < objArray[i].length; j++) {
//                stringArray[i][j] = objArray[i][j].toString();
//            }
//        }
//        return stringArray;
//    }

    //    public static void write2DArrayToFile(String[][] array, String fileName) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            for (String[] row : array) {
//                StringBuilder builder = new StringBuilder();
//                for (String item : row) {
//                    builder.append(item).append("\t");
//                }
//                writer.write(builder.toString());
//                writer.newLine();
//            }
//        }
//    }
//    public static void write2DArrayToFile(Object[][] obj) throws IOException {
//        ObjectOutputStream out = null ;
//        try {
//            /* Following will also work in one line */
//            FileOutputStream filestream = new FileOutputStream ("output.ser");
//            BufferedOutputStream bufOutstream = new BufferedOutputStream (
//                    filestream );
//            out = new ObjectOutputStream ( bufOutstream );
//            out.writeObject(obj);
//            filestream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void writeArrayListToFile(ArrayList<DroneTypes> obj) throws IOException {
//        ObjectOutputStream out = null ;
//        try {
//            /* Following will also work in one line */
//            FileOutputStream filestream = new FileOutputStream ("output.ser");
//            BufferedOutputStream bufOutstream = new BufferedOutputStream (
//                    filestream );
//            out = new ObjectOutputStream ( bufOutstream );
//            out.writeObject(obj);
//            filestream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void read2DArrayToFile(Object[][] obj) throws IOException {
//        ObjectInputStream in = null ;
//        try {
//            FileInputStream filestream = new FileInputStream ("");
//            BufferedInputStream bufInstream = new BufferedInputStream ( filestream ) ;
//            in = new ObjectInputStream ( bufInstream );
//            while ( true ){
//                Object[][] good = (Object[][]) in.readObject();
//                System.out.println ( good.toString );
//                }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

}