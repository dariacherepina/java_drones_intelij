package org.main;

import Drone.Convert;
import Drone.Sortable;
import GUI.MyFrame;
import Threads.ThreadCheckRefresh;
import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ThreadCheckRefresh threadDrone = new ThreadCheckRefresh();
        Thread threadD = new Thread(threadDrone);
        try {
            helper.checkToInitialiseAllData();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        new MyFrame(helper.getDronesList(), helper.getDroneTypesList(), helper.getDroneDynamicsList());
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "IOException in MyFrame", e);
                    }
                }
            });

            threadD.start();
        } catch (JSONException e) {
            threadD.interrupt();
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}