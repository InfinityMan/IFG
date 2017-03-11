/*
 * Copyright (C) 2016 Dmitry Tsvetkovsky
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.dmig.infinityflight.logic;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ru.dmig.infinityflight.gui.*;
import ru.dmig.infinityflight.logic.human.Personal;
import ru.dmig.infinityflight.logic.rooms.*;
import ru.dmig.infinityflight.res.Link;
import ru.epiclib.base.Base;

/**
 *
 * @author Dmig
 */
public final class InfinityFlight {
    
    public static final TouristRoom[][] DEFAULT_TOURIST_ROOMS = loadDefaultTouristRooms();
    public static final CabinRoom[] DEFAULT_CABIN_ROOMS = loadDefaultCabin();
    
    //default engines
    //default reactors

    public static final byte PROFFESSION_AMOUNT = 5;
    public static final byte TOURIST_CLASSES_AMOUNT = 3;

    public static final short[] START_PERSONAL_AMOUNT = {2, 1, 2, 0, 2};

    /**
     * Flight duration between stations in days; FD[0] - low flight duration
     * FD[1] - normal FD[2] - high
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
    public static enum DEFEAT_STATE {
        RUN_OUT_OF_FUEL, RUN_OUT_OF_FOOD
    };

    public static Gui gui;

    public static Ship ship;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ship = new Ship();
        /* Loading ship */

        Gui.start();
        
        StationGui.start();

        Thread.sleep(4000);

        gui = Gui.gui;

        Updater updater = new Updater(ship);
        updater.start();

        //updater.end();
    }

    public static double genDistanceToStation() {
        int flightDurationType = chancesUpgrade(CHANCES_FLIGHT_DURATION);
        return ((Base.randomNumber(FLIGHT_DURATION[flightDurationType][0],
                FLIGHT_DURATION[flightDurationType][1])) * 10);
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
    
    public static TouristRoom[][] loadDefaultTouristRooms() {
        TouristRoom[][] trRooms = null;
        
        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("touristRooms");
        } catch (IOException ex) {
            throw new IllegalStateException("loadDefaultTouristRooms() :"+ ex.getMessage());
        }
        if(alpha != null) {
            for (int i = 0; i < alpha.length; i++) {
                String[] beta = alpha[i].split(";");
                for (int j = 0; j < beta.length; j++) {
                    if(i == 0 && j == 0) {
                        trRooms = new TouristRoom[alpha.length][];
                    }
                    if(j == 0) {
                        trRooms[i] = new TouristRoom[beta.length];
                    }
                    String[] gamm = beta[j].split(",");
                    
                    String typeName;
                    TouristRoom.Class cl = TouristRoom.Class.THIRD;
                    Room.Prestige pr = Room.Prestige.TERRIBLE;
                    byte placeAmount;
                    
                    typeName = gamm[0]; //TypeName
                    
                    switch (i) { //Class
                        case 0:
                            cl = TouristRoom.Class.THIRD;
                            break;
                        case 1:
                            cl = TouristRoom.Class.SECOND;
                            break;
                        case 2:
                            cl = TouristRoom.Class.FIRST;
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                    switch (Integer.valueOf(gamm[1])) {
                        case 0:
                            pr = Room.Prestige.TERRIBLE;
                            break;
                        case 1:
                            pr = Room.Prestige.BAD;
                            break;
                        case 2:
                            pr = Room.Prestige.NORMAL;
                            break;
                        case 3:
                            pr = Room.Prestige.GOOD;
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                    placeAmount = Byte.valueOf(gamm[2]);
                    
                    trRooms[i][j] = new TouristRoom(cl, typeName, pr, placeAmount);
                }
            }
        } else System.exit(-2);
        return trRooms;
    }

    public static CabinRoom[] loadDefaultCabin() {
        CabinRoom[] cbRooms = null;
        
        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("cabinRooms");
        } catch (IOException ex) {
            throw new IllegalStateException("loadDefaultCabinRooms() :"+ ex.getMessage());
        }
        if(alpha != null) {
                for (int i = 0; i < alpha.length; i++) {
                    if(i == 0) {
                        cbRooms = new CabinRoom[alpha.length];
                    }
                    String[] beta = alpha[i].split(",");
                    
                    String typeName;
                    Room.Prestige pr = Room.Prestige.TERRIBLE;
                    byte placeAmount;
                    
                    typeName = beta[0]; //TypeName
                    
                    switch (Integer.valueOf(beta[1])) {
                        case 0:
                            pr = Room.Prestige.TERRIBLE;
                            break;
                        case 1:
                            pr = Room.Prestige.BAD;
                            break;
                        case 2:
                            pr = Room.Prestige.NORMAL;
                            break;
                        case 3:
                            pr = Room.Prestige.GOOD;
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                    placeAmount = Byte.valueOf(beta[2]);
                    
                    cbRooms[i] = new CabinRoom(typeName, pr, placeAmount);
                }
        } else System.exit(-2);
        return cbRooms;
    }
}
