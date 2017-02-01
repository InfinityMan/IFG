/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;
import ru.dmig.infinityflight.gui.Gui;
import ru.epiclib.base.Base;

/**
 *
 * @author Dmig
 */
public final class InfinityFlight {

    /**
     * Flight duration between stations in days; FD[0] - low flight duration FD[1] -
     * normal FD[2] - high
     *
     * FD[x][0] - minimum FD[x][1] - maximum.
     */
    public static final short[][] FLIGHT_DURATION = {{60, 70}, {80, 90}, {100, 120}};

    /**
     * Chances of flight duration categories: CFD[0] - low flight duration
     * CFD[1] - normal CFD[2] - high.
     */
    public static final byte[] CHANCES_FLIGHT_DURATION = {19, 71, 10};
    
    /**
     * Type of defeat
     */
    public static enum DEFEAT_STATE {RUN_OUT_OF_FUEL};
    
    public static Gui gui;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ship ship = new Ship();
        /* Loading ship */
        gui = new Gui(ship);
        gui.setVisible(true);
    }
    
    public static short genNumOfDaysBeforeStation() {
        int flightDurationType = chancesUpgrade(CHANCES_FLIGHT_DURATION);
        return Base.randomNumber(FLIGHT_DURATION[flightDurationType][0],
                FLIGHT_DURATION[flightDurationType][1]);
    }
    
    public static Station genNewStation() {
        return new Station();
    }
    
    public static int chancesUpgrade(byte[] chances) {
        int[] newChances = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i <= chances.length - 1) {
                newChances[i] = chances[i];
            } else {
                newChances[i] = 0;
            }
        }
        return Base.chances(newChances);
    }
    
    public static void defeatProcess(DEFEAT_STATE state) {
        //Stop update thread
        switch (state) {
            case RUN_OUT_OF_FUEL:
                JOptionPane.showMessageDialog(null,
                        "You defeated because: run out of fuel", "Defeat",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
    }

}
