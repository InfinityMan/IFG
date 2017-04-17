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
import static ru.dmig.infinityflight.logic.InfinityFlight.PROFFESSION_AMOUNT;
import static ru.dmig.infinityflight.logic.InfinityFlight.TOURIST_CLASSES_AMOUNT;
import static ru.dmig.infinityflight.logic.InfinityFlight.chancesUpgrade;
import static ru.dmig.infinityflight.logic.Storage.NO_MAXIMUM;
import ru.dmig.infinityflight.logic.exceptions.StorageEmptyException;
import ru.dmig.infinityflight.logic.exceptions.StorageOverfilledException;
import static ru.epiclib.base.Base.chance;
import static ru.epiclib.base.Base.randomNumber;

/**
 * Class for stations in the infinity black sea
 *
 * @author Dmig
 */
public class Station {


    public static final byte[] SIZE_CHANCES = {56, 27, 17};
    public static final byte[] MAX_DAYS = {10, 20, 50};

    /**
     * Food bonus for big stations
     */
    public static final short FOOD_BONUS = 500;

    /**
     * Chance of food bonus for big stations
     */
    public static final byte CHANCE_FOOD_BONUS = 15;

    /**
     * Chance of 'low' fuel; Chance of 'normal' fuel; Chance of 'high' fuel
     */
    public static final byte[] VOLUME_FUEL_CHANCES = {15, 75, 10};

    /**
     * !Bonus fuel value
     */
    public static final short LOW_FUEL_ADDEND = 20;

    /**
     * Bonus fuel value
     */
    public static final short HIGH_FUEL_ADDEND = 30;

    /**
     * Workers amount: AOW[0] - small station, etc. AOW[x][0] - min; AOW[x][1] -
     * max;
     */
    public static final short[][] AMOUNT_OF_WORKERS = {{2, 4}, {6, 8}, {19, 23}};
    
    /**
     * Chance of workers: Worker: CW[0], Engineer CW[1] etc. (Personal).
     */
    public static final byte[] CHANCE_WORKERS = {43,12,11,16,18};

    /**
     * Tourists amount: AOT[0] - small station, etc. AOT[x][0] - min; AOT[x][1]
     * - max;
     */
    public static final short[][] AMOUNT_OF_TOURISTS = {{1, 3}, {7, 9}, {29, 35}};
    
    /**
     * Chance of tourists: Third: CT[0], Second: CT[1], First: CT[2].
     */
    public static final byte[] CHANCE_TOURISTS = {62,26,12};

    /**
     * Fuel amount: FA[0] - small station, etc. FA[x][0] - min; FA[x][1] - max;
     */
    public static final short[][] FUEL_AMOUNT = {{450, 540}, {510, 600}, {660, 840}};

    /**
     * Food amount: FA[0] - small station, etc. FA[x][0] - min; FA[x][1] - max;
     */
    public static final short[][] FOOD_AMOUNT = {{3_500, 4_500}, {5_000, 5_500}, {7_000, 7_500}};

    public SIZE size;
    
    public final Storage storage;
    
    private String name;
    
    /**
     * In sequence:
     *  Worker, Engineer, Medic, Guard, Biologist.
     */
    private short[] workersAmount;
    
    /**
     * In sequence:
     *  Third class, Second class, First class.
     */
    private short[] touristsAmount;

    public Station() throws StorageOverfilledException {
        
        storage = new Storage(true);
        
        int indx = chancesUpgrade(SIZE_CHANCES);
        switch (indx) {
            case 0:
                size = SIZE.SMALL;
                break;
            case 1:
                size = SIZE.NORMAL;
                break;
            case 2:
                size = SIZE.BIG;
                break;
            default:
                throw new IllegalStateException("Sise and chances and constructor"
                        + " has some problems");
        }
        
        constructor(size);
        
    }

    public Station(SIZE size) throws StorageOverfilledException {
        
        storage = new Storage(true);
        
        constructor(size);

    }
    
    private void constructor(SIZE size) throws StorageOverfilledException {
        
        name = "NoName";
        //Generating name
        
        this.size = size;

        int sizeIndex = 0;
        switch (size) {
            case SMALL:
                sizeIndex = 0;
                break;
            case NORMAL:
                sizeIndex = 1;
                break;
            case BIG:
                sizeIndex = 2;
                break;
            default:
                throw new IllegalStateException("Sise and chances and constructor"
                        + " has some problems");
        }

        assignWorkers(sizeIndex);
        assignTourists(sizeIndex);
        
        storage.setMaxFoodAmount(NO_MAXIMUM);
        storage.setMaxFuelAmount(NO_MAXIMUM);
        storage.setMaxMedicineAmount(NO_MAXIMUM);
        storage.setMaxFoodAmount(NO_MAXIMUM);

        storage.setAmounts(randomNumber(FOOD_AMOUNT[sizeIndex][0], FOOD_AMOUNT[sizeIndex][1]),
                randomNumber(FUEL_AMOUNT[sizeIndex][0], FUEL_AMOUNT[sizeIndex][1]),
                (short) 0, (short) 0);

        //Medinice bonus
        //Spare bonus

        doFuelBonus();

        if (size == SIZE.BIG) {
            if (chance(CHANCE_FOOD_BONUS, 0)) {
                storage.increaseFood(FOOD_BONUS);
            }
        }
    }
    
    private void assignWorkers(int sizeIndex) {
        short workersMainAmount = randomNumber(AMOUNT_OF_WORKERS[sizeIndex][0],
                AMOUNT_OF_WORKERS[sizeIndex][1]);

        workersAmount = new short[PROFFESSION_AMOUNT];
        for (int i = 0; i < workersMainAmount; i++) {
            workersAmount[chancesUpgrade(CHANCE_WORKERS)]++;
        }
    }

    private void assignTourists(int sizeIndex) {
        short touristsMainAmount = randomNumber(AMOUNT_OF_TOURISTS[sizeIndex][0],
                AMOUNT_OF_TOURISTS[sizeIndex][1]);
        
        touristsAmount = new short[TOURIST_CLASSES_AMOUNT];
        for (int i = 0; i < touristsMainAmount; i++) {
            touristsAmount[chancesUpgrade(CHANCE_TOURISTS)]++;
        }
    }

    private void doFuelBonus() {
        try {
            int bonusType = chancesUpgrade(VOLUME_FUEL_CHANCES);
            switch (bonusType) {
                case 0:
                    storage.reduceFuel(LOW_FUEL_ADDEND);
                    break;
                case 1:
                    //no addend
                    break;
                case 2:
                    storage.increaseFuel(HIGH_FUEL_ADDEND);
                    break;
            }
        } catch (StorageEmptyException | StorageOverfilledException n) {
            err.println(n);
        }
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of touristsAmount
     *
     * @return the value of touristsAmount
     */
    public short[] getTouristsAmount() {
        return touristsAmount;
    }

    /**
     * Set the value of touristsAmount
     *
     * @param touristsAmount new value of touristsAmount
     */
    public void setTouristsAmount(short[] touristsAmount) {
        this.touristsAmount = touristsAmount;
    }

    /**
     * Get the value of workersAmount
     *
     * @return the value of workersAmount
     */
    public short[] getWorkersAmount() {
        return workersAmount;
    }

    /**
     * Set the value of workersAmount
     *
     * @param workersAmount new value of workersAmount
     */
    public void setWorkersAmount(short[] workersAmount) {
        this.workersAmount = workersAmount;
    }
    
    public String getSizeString() {
        switch(size) {
            case SMALL:
                return "Small";
            case NORMAL:
                return "Normal";
            case BIG:
                return "Big";
            default:
                throw new IllegalStateException("Station::getSizeString()");
        }
    }
    public static enum SIZE {
        SMALL, NORMAL, BIG
    }

}
