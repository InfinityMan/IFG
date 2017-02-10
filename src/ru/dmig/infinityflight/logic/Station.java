/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import static ru.dmig.infinityflight.logic.InfinityFlight.chancesUpgrade;
import ru.epiclib.base.Base;

/**
 * Class for stations in the infinity black sea
 *
 * @author Dmig
 */
public final class Station {

    public static enum SIZE {
        SMALL, NORMAL, BIG
    };

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
     * Chance of 'low' fuel Chance of 'normal' fuel Chance of 'high' fuel
     */
    public static final byte[] VOLUME_FUEL_CHANCES = {15, 75, 10};

    /**
     * !Bonus fuel value
     */
    public static final short LOW_FUEL_ADDEND = -20;

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
    public static final short[][] FUEL_AMOUNT = {{75, 90}, {80, 95}, {100, 125}};

    /**
     * Food amount: FA[0] - small station, etc. FA[x][0] - min; FA[x][1] - max;
     */
    public static final short[][] FOOD_AMOUNT = {{3500, 4500}, {5000, 5500}, {7000, 7500}};

    public SIZE size;
    
    public final Storage storage;

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

    //private short fuelAmount;
    //private short foodAmount;

    public Station() {
        this(SIZE.SMALL);
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
    }

    public Station(SIZE size) {
        this.size = size;
        
        storage = new Storage(true);

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

        storage.setFuelAmount(Base.randomNumber(FUEL_AMOUNT[sizeIndex][0], FUEL_AMOUNT[sizeIndex][1]));
        storage.setFoodAmount(Base.randomNumber(FOOD_AMOUNT[sizeIndex][0], FOOD_AMOUNT[sizeIndex][1]));
        
        //Medinice bonus
        //Spare bonus

        doFuelBonus();

        if (size == SIZE.BIG) {
            if (Base.chance(CHANCE_FOOD_BONUS, 0)) {
                storage.setFoodAmount(storage.getFoodAmount() + CHANCE_FOOD_BONUS);
            }
        }
    }
    
    private void assignWorkers(int sizeIndex) {
        short workersMainAmount = Base.randomNumber(AMOUNT_OF_WORKERS[sizeIndex][0],
                AMOUNT_OF_WORKERS[sizeIndex][1]);

        for (int i = 0; i < workersMainAmount; i++) {
            workersAmount[InfinityFlight.chancesUpgrade(CHANCE_WORKERS)]++;
        }
    }

    private void assignTourists(int sizeIndex) {
        short touristsMainAmount = Base.randomNumber(AMOUNT_OF_TOURISTS[sizeIndex][0],
                AMOUNT_OF_TOURISTS[sizeIndex][1]);
        
        for (int i = 0; i < touristsMainAmount; i++) {
            touristsAmount[InfinityFlight.chancesUpgrade(CHANCE_TOURISTS)]++;
        }
    }

    private void doFuelBonus() {
        int bonusType = chancesUpgrade(VOLUME_FUEL_CHANCES);
        switch (bonusType) {
            case 0:
                storage.setFuelAmount(storage.getFuelAmount() + LOW_FUEL_ADDEND);
                break;
            case 1:
                //no addend
                break;
            case 2:
                storage.setFuelAmount(storage.getFuelAmount() + HIGH_FUEL_ADDEND);
                break;
        }
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

}
