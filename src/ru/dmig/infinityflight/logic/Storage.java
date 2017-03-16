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

import ru.dmig.infinityflight.logic.exceptions.StorageEmptyException;
import ru.dmig.infinityflight.logic.exceptions.StorageOverfilledException;

/**
 * Class for storages on ship and stations. In storages on stations no max fuel,
 * food, medicine, spares
 *
 * @author Dmig
 */
public final class Storage {
    
    /**
     * Set this value to any "maxXAmount" if storage of 'x' infinity.
     * In this case <code>StorageOverfilledException</code> can't be throwed
     */
    public static final short NO_MAXIMUM = -1;

    private static final int START_FOOD_AMOUNT = 5000;
    private static final int START_MAX_FOOD_AMOUNT = 10000;

    private static final float START_FUEL_AMOUNT = 400;
    private static final float START_MAX_FUEL_AMOUNT = 750;

    private static final short START_MEDICINE_AMOUNT = 20;
    private static final short START_MAX_MEDICINE_AMOUNT = 20;

    private static final short START_SPARE_AMOUNT = 10;
    private static final short START_MAX_SPARE_AMOUNT = 20;

    private int foodAmount;
    private int maxFoodAmount;

    private float fuelAmount;
    private float maxFuelAmount;

    private short medicineAmount;
    private short maxMedicineAmount;

    private short spareAmount;
    private short maxSpareAmount;

    public Storage(int foodAmount, int maxFoodAmount, float fuelAmount, float maxFuelAmount, short medicineAmount, short maxMedicineAmount, short spareAmount, short maxSpareAmount) {
        this.foodAmount = foodAmount;
        this.maxFoodAmount = maxFoodAmount;
        this.fuelAmount = fuelAmount;
        this.maxFuelAmount = maxFuelAmount;
        this.medicineAmount = medicineAmount;
        this.maxMedicineAmount = maxMedicineAmount;
        this.spareAmount = spareAmount;
        this.maxSpareAmount = maxSpareAmount;
    }
    
    /**
     * Returns food,fuel,medicine,spare amounts to a start level: START_FOOD.. And etc.
     */
    public void toStartAmounts() {
        foodAmount = START_FOOD_AMOUNT;
        fuelAmount = START_FUEL_AMOUNT;
        medicineAmount = START_MEDICINE_AMOUNT;
        spareAmount = START_SPARE_AMOUNT;
    }

    /**
     * New default storage
     *
     * @param empty if !empty this create basic storage for ship
     */
    public Storage(boolean empty) {
        if (!empty) {
            //foodAmount = START_FOOD_AMOUNT;
            maxFoodAmount = START_MAX_FOOD_AMOUNT;
            //fuelAmount = START_FUEL_AMOUNT;
            maxFuelAmount = START_MAX_FUEL_AMOUNT;
            //medicineAmount = START_MEDICINE_AMOUNT;
            maxMedicineAmount = START_MAX_MEDICINE_AMOUNT;
            //spareAmount = START_SPARE_AMOUNT;
            maxSpareAmount = START_MAX_SPARE_AMOUNT;
            
            toStartAmounts();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Get and set">

    /**
     * USING ONLY ON STATION AND BASIC SHIP!
     * @param food foodAmount = food
     * @param fuel fuelAmount = fuel;
     * @param medicine medicineAmount = medicine;
     * @param spare spareAmount = spare;
     */
    
    public void setAmounts(int food, float fuel, short medicine, short spare) {
        if(food >= 0 && fuel >= 0 && medicine >= 0 && spare >= 0) {
            foodAmount = food;
            fuelAmount = fuel;
            medicineAmount = medicine;
            spareAmount = spare;
        } else throw new IllegalArgumentException();
    }
    
    /**
     * Get the value of foodAmount
     *
     * @return the value of foodAmount
     */
    public int getFoodAmount() {
        return foodAmount;
    }

    public void reduceFood(int amount) throws StorageEmptyException {
        if((foodAmount - amount) >= 0) {
            foodAmount -= amount;
        } else {
            foodAmount = 0;
            throw new StorageEmptyException();
        }
    }
    
    public void increaseFood(int amount) throws StorageOverfilledException {
        if ((this.foodAmount + amount) <= maxFoodAmount || maxFoodAmount == NO_MAXIMUM) {
            this.foodAmount += amount;
        } else {
            throw new StorageOverfilledException((this.foodAmount + amount) - maxFoodAmount);
        }
    }

    /**
     * Get the value of maxFoodAmount
     *
     * @return the value of maxFoodAmount
     */
    public int getMaxFoodAmount() {
        return maxFoodAmount;
    }

    /**
     * Set the value of maxFoodAmount
     *
     * @param maxFoodAmount new value of maxFoodAmount
     */
    public void setMaxFoodAmount(int maxFoodAmount) {
        this.maxFoodAmount = maxFoodAmount;
    }

    /**
     * Get the value of fuelAmount
     *
     * @return the value of fuelAmount
     */
    public float getFuelAmount() {
        return fuelAmount;
    }
    
    public void reduceFuel(float amount) throws StorageEmptyException {
        if((fuelAmount - amount) >= 0) {
            fuelAmount -= amount;
        } else {
            fuelAmount = 0;
            throw new StorageEmptyException();
        }
    }
    
    public void increaseFuel(float amount) throws StorageOverfilledException {
        if ((this.fuelAmount + amount) <= maxFuelAmount || maxFuelAmount == NO_MAXIMUM) {
            this.fuelAmount += amount;
        } else {
            throw new StorageOverfilledException((this.fuelAmount + amount) - maxFuelAmount);
        }
    }

    /**
     * Get the value of maxFuelAmount
     *
     * @return the value of maxFuelAmount
     */
    public float getMaxFuelAmount() {
        return maxFuelAmount;
    }

    /**
     * Set the value of maxFuelAmount
     *
     * @param maxFuelAmount new value of maxFuelAmount
     */
    public void setMaxFuelAmount(float maxFuelAmount) {
        this.maxFuelAmount = maxFuelAmount;
    }

    /**
     * Get the value of medicineAmount
     *
     * @return the value of medicineAmount
     */
    public short getMedicineAmount() {
        return medicineAmount;
    }

    public void reduceMedicine(short amount) throws StorageEmptyException {
        if((medicineAmount - amount) >= 0) {
            medicineAmount -= amount;
        } else {
            medicineAmount = 0;
            throw new StorageEmptyException();
        }
    }
    
    public void increaseMedicine(short amount) throws StorageOverfilledException {
        if ((this.medicineAmount + amount) <= maxMedicineAmount || maxMedicineAmount == NO_MAXIMUM) {
            this.medicineAmount += amount;
        } else {
            throw new StorageOverfilledException((this.medicineAmount + amount) - maxMedicineAmount);
        }
    }

    /**
     * Get the value of maxMedicineAmount
     *
     * @return the value of maxMedicineAmount
     */
    public short getMaxMedicineAmount() {
        return maxMedicineAmount;
    }

    /**
     * Set the value of maxMedicineAmount
     *
     * @param maxMedicineAmount new value of maxMedicineAmount
     */
    public void setMaxMedicineAmount(short maxMedicineAmount) {
        this.maxMedicineAmount = maxMedicineAmount;
    }

    /**
     * Get the value of spareAmount
     *
     * @return the value of spareAmount
     */
    public short getSpareAmount() {
        return spareAmount;
    }

    public void reduceSpare(short amount) throws StorageEmptyException {
        if((spareAmount - amount) >= 0) {
            spareAmount -= amount;
        } else {
            spareAmount = 0;
            throw new StorageEmptyException();
        }
    }
    
    public void increaseSpare(short amount) throws StorageOverfilledException {
        if ((this.spareAmount + amount) <= maxSpareAmount || maxSpareAmount == NO_MAXIMUM) {
            this.spareAmount += amount;
        } else {
            throw new StorageOverfilledException((this.spareAmount + amount) - maxSpareAmount);
        }
    }

    /**
     * Get the value of maxSpareAmount
     *
     * @return the value of maxSpareAmount
     */
    public short getMaxSpareAmount() {
        return maxSpareAmount;
    }

    /**
     * Set the value of maxSpareAmount
     *
     * @param maxSpareAmount new value of maxSpareAmount
     */
    public void setMaxSpareAmount(short maxSpareAmount) {
        this.maxSpareAmount = maxSpareAmount;
    }

    //</editor-fold>
    
}
