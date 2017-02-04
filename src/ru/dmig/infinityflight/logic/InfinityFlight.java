/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import ru.dmig.infinityflight.gui.Gui;
import ru.epiclib.base.Base;

/**
 *
 * @author Dmig
 */
public final class InfinityFlight {
    
    public static final short[] START_PERSONAL_AMOUNT = {2,1,2,0,2};

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
    public static enum DEFEAT_STATE {RUN_OUT_OF_FUEL, RUN_OUT_OF_FOOD};
    
    public static Gui gui;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Ship ship = new Ship();
        /* Loading ship */
        
        GuiStarter starter = new GuiStarter(ship);
        starter.start();
        
        Thread.sleep(4000);
        
        Updater updater = new Updater(ship);
        updater.start();
        
        
        //updater.end();
    }
    
    private static class GuiStarter extends Thread {
        
        Ship ship;

        public GuiStarter(Ship ship) {
            this.ship = ship;
            this.setName("Guier");
        }

        @Override
        public void run() {
            gui = new Gui(ship);
            gui.setVisible(true);
        }
    }
    
    public static double genDistanceToStation() {
        int flightDurationType = chancesUpgrade(CHANCES_FLIGHT_DURATION);
        return ((Base.randomNumber(FLIGHT_DURATION[flightDurationType][0],
                FLIGHT_DURATION[flightDurationType][1]))*10);
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
    
    public static ArrayList<Personal> getStartPersonal() {
        ArrayList<Personal> alpha = new ArrayList<>();
        for (int i = 0; i < START_PERSONAL_AMOUNT.length; i++) {
            for (int j = 0; j < START_PERSONAL_AMOUNT[i]; j++) {
                switch (j) {
                    case 0:
                        alpha.add(new Personal(Personal.PROFESSION.WORKER));
                        break;
                    case 1:
                        alpha.add(new Personal(Personal.PROFESSION.ENGINEER));
                        break;
                    case 2:
                        alpha.add(new Personal(Personal.PROFESSION.MEDIC));
                        break;
                    case 3:
                        alpha.add(new Personal(Personal.PROFESSION.GUARD));
                        break;
                    case 4:
                        alpha.add(new Personal(Personal.PROFESSION.BIOLOGIST));
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
        return alpha;
    }
    
    public static void defeatProcess(DEFEAT_STATE state) {
        //Stop update thread
        switch (state) {
            case RUN_OUT_OF_FUEL:
                JOptionPane.showMessageDialog(null,
                        "You defeated because: run out of fuel", "Defeat",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case RUN_OUT_OF_FOOD:
                JOptionPane.showMessageDialog(null,
                        "You defeated because: run out of food", "Defeat",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
        //Deleting save?
        //Restart message
        
    }

}
