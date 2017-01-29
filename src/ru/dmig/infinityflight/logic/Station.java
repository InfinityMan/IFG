/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

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

    public static final byte[] CHANCES = {56, 27, 17};
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
     * Tourists amount: AOT[0] - small station, etc. AOT[x][0] - min; AOT[x][1]
     * - max;
     */
    public static final short[][] AMOUNT_OF_TOURISTS = {{1, 3}, {7, 9}, {29, 35}};

    /**
     * Fuel amount: FA[0] - small station, etc. FA[x][0] - min; FA[x][1] - max;
     */
    public static final short[][] FUEL_AMOUNT = {{75, 90}, {80, 95}, {100, 125}};

    /**
     * Food amount: FA[0] - small station, etc. FA[x][0] - min; FA[x][1] - max;
     */
    public static final short[][] FOOD_AMOUNT = {{3500, 4500}, {5000, 5500}, {7000, 7500}};

    public SIZE size;

    private short workersAmount;
    private short touristsAmount;

    private short fuelAmount;
    private short foodAmount;

    public Station() {
        int[] chances = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i <= CHANCES.length - 1) {
                chances[i] = CHANCES[i];
            } else {
                chances[i] = 0;
            }
        }
        int indx = Base.chances(chances);
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

        genStation();
    }

    public Station(SIZE size) {
        this.size = size;

        genStation();
    }

    private void genStation() {
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

        workersAmount = Base.randomNumber(AMOUNT_OF_WORKERS[sizeIndex][0],
                AMOUNT_OF_WORKERS[sizeIndex][1]);
        touristsAmount = Base.randomNumber(AMOUNT_OF_TOURISTS[sizeIndex][0],
                AMOUNT_OF_TOURISTS[sizeIndex][1]);
        fuelAmount = Base.randomNumber(FUEL_AMOUNT[sizeIndex][0], FUEL_AMOUNT[sizeIndex][1]);
        foodAmount = Base.randomNumber(FOOD_AMOUNT[sizeIndex][0], FOOD_AMOUNT[sizeIndex][1]);

        doFuelBonus();

        if (size == SIZE.BIG) {
            if (Base.chance(CHANCE_FOOD_BONUS, 0)) {
                foodAmount += CHANCE_FOOD_BONUS;
            }
        }
    }

    private void doFuelBonus() {
        int[] chances = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i <= VOLUME_FUEL_CHANCES.length - 1) {
                chances[i] = VOLUME_FUEL_CHANCES[i];
            } else {
                chances[i] = 0;
            }
        }
        int bonusType = Base.chances(chances);
        switch (bonusType) {
            case 0:
                fuelAmount += LOW_FUEL_ADDEND;
                break;
            case 1:
                //no addend
                break;
            case 2:
                fuelAmount += HIGH_FUEL_ADDEND;
                break;
        }
    }

    @Override
    public String toString() {
        return "Station{" + "size=" + size + ", workersAmount=" + workersAmount + ", touristsAmount=" + touristsAmount + ", fuelAmount=" + fuelAmount + ", foodAmount=" + foodAmount + '}';
    }

    /**
     * Get the value of foodAmount
     *
     * @return the value of foodAmount
     */
    public short getFoodAmount() {
        return foodAmount;
    }

    /**
     * Set the value of foodAmount
     *
     * @param foodAmount new value of foodAmount
     */
    public void setFoodAmount(short foodAmount) {
        this.foodAmount = foodAmount;
    }

    /**
     * Get the value of fuelAmount
     *
     * @return the value of fuelAmount
     */
    public short getFuelAmount() {
        return fuelAmount;
    }

    /**
     * Set the value of fuelAmount
     *
     * @param fuelAmount new value of fuelAmount
     */
    public void setFuelAmount(short fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    /**
     * Get the value of touristsAmount
     *
     * @return the value of touristsAmount
     */
    public short getTouristsAmount() {
        return touristsAmount;
    }

    /**
     * Set the value of touristsAmount
     *
     * @param touristsAmount new value of touristsAmount
     */
    public void setTouristsAmount(short touristsAmount) {
        this.touristsAmount = touristsAmount;
    }

    /**
     * Get the value of workersAmount
     *
     * @return the value of workersAmount
     */
    public short getWorkersAmount() {
        return workersAmount;
    }

    /**
     * Set the value of workersAmount
     *
     * @param workersAmount new value of workersAmount
     */
    public void setWorkersAmount(short workersAmount) {
        this.workersAmount = workersAmount;
    }

}
