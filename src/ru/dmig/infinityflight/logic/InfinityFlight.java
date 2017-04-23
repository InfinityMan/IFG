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

import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import ru.dmig.infinityflight.gui.*;
import ru.dmig.infinityflight.logic.exceptions.StorageOverfilledException;
import ru.dmig.infinityflight.logic.human.Passenger;
import ru.dmig.infinityflight.logic.human.Personal;
import static ru.dmig.infinityflight.logic.human.Personal.PROFESSION.BIOLOGIST;
import static ru.dmig.infinityflight.logic.human.Personal.PROFESSION.ENGINEER;
import static ru.dmig.infinityflight.logic.human.Personal.PROFESSION.GUARD;
import static ru.dmig.infinityflight.logic.human.Personal.PROFESSION.MEDIC;
import static ru.dmig.infinityflight.logic.human.Personal.PROFESSION.WORKER;
import ru.dmig.infinityflight.logic.rooms.*;
import static ru.dmig.infinityflight.res.Defaulter.loadDefaultCabin;
import static ru.dmig.infinityflight.res.Defaulter.loadDefaultEngines;
import static ru.dmig.infinityflight.res.Defaulter.loadDefaultReactors;
import static ru.dmig.infinityflight.res.Defaulter.loadDefaultTouristRooms;
import static ru.epiclib.base.Base.chances;
import static ru.epiclib.base.Base.randomNumber;

/**
 *
 * @author Dmig
 */
public class InfinityFlight {
    
    public static final double PRICE_FOR_ONE_FOOD = 1;
    public static final double PRICE_FOR_ONE_FUEL = 8;
    public static final double PRICE_FOR_ONE_SPARE = 25;
    public static final double PRICE_FOR_ONE_MEDICINE = 32;
    
    public static final TouristRoom[][] DEFAULT_TOURIST_ROOMS = loadDefaultTouristRooms();
    public static final CabinRoom[] DEFAULT_CABIN_ROOMS = loadDefaultCabin();
    public static final Reactor[] DEFAULT_REACTORS = loadDefaultReactors();
    public static final Engine[] DEFAULT_ENGINES = loadDefaultEngines();

    public static final byte PROFFESSION_AMOUNT = 5;
    public static final byte TOURIST_CLASSES_AMOUNT = 3;

    public static final short[] START_PERSONAL_AMOUNT = {2, 1, 2, 0, 2};
    
    public static final double START_MONEY_AMOUNT = 1000;

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


    public static Gui gui;
    
    public static Game game;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        game = new Game("Tester");
        
        game.ship = new Ship();
        game.ship.setDistanceToStation(genSmallDistanceToStation());
        /* Loading ship */

        Gui.start();
        
        AdminGui.start();

        sleep(4_200);

        gui = Gui.gui;

        Updater updater = new Updater(game.ship);
        updater.start();

        //updater.end();
    }
    
    public static Passenger genTourist(Passenger.CLASS pClass) {
        return new Passenger(pClass);
    }

    public static double genDistanceToStation() {
        int flightDurationType = chancesUpgrade(CHANCES_FLIGHT_DURATION);
        return ((randomNumber(FLIGHT_DURATION[flightDurationType][0],
                FLIGHT_DURATION[flightDurationType][1])) * 10);
    }
    
    public static double genSmallDistanceToStation() {
        return ((randomNumber(FLIGHT_DURATION[0][0], FLIGHT_DURATION[0][1])) * 10);
    }

    public static Station genNewStation() {
        try {
            return new Station(); //Can't throw exception
        } catch (StorageOverfilledException n) {
            err.println(n);
            exit(-1);
            return null;
        }
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
        return chances(newChances);
    }

    public static ArrayList<Personal> getStartPersonal() {
        ArrayList<Personal> alpha = new ArrayList<>();
        for (int i = 0; i < START_PERSONAL_AMOUNT.length; i++) {
            for (int j = 0; j < START_PERSONAL_AMOUNT[i]; j++) {
                switch (j) {
                    case 0:
                        alpha.add(new Personal(WORKER));
                        break;
                    case 1:
                        alpha.add(new Personal(ENGINEER));
                        break;
                    case 2:
                        alpha.add(new Personal(MEDIC));
                        break;
                    case 3:
                        alpha.add(new Personal(GUARD));
                        break;
                    case 4:
                        alpha.add(new Personal(BIOLOGIST));
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
                showMessageDialog(null,
                        "You defeated because: run out of fuel", "Defeat", INFORMATION_MESSAGE);
                break;
            case RUN_OUT_OF_FOOD:
                showMessageDialog(null,
                        "You defeated because: run out of food", "Defeat", INFORMATION_MESSAGE);
                break;
        }

        //Deleting save?
        
        
        int restart = showConfirmDialog(null, "Are you want to restart?", "Restart", YES_NO_OPTION, PLAIN_MESSAGE);
        if(restart == YES_OPTION) {
            showMessageDialog(null, "Sorry, but you can't to restart yet");
        }
        
        exit(0);
    }
    /**
     * Type of defeat
     */
    public static enum DEFEAT_STATE {
        RUN_OUT_OF_FUEL, RUN_OUT_OF_FOOD
    }
    
}
