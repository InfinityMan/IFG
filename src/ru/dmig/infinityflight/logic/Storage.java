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

/**
 * Class for storages on ship and stations
 * In storages on stations no max fuel, food, medicine, spares
 * @author Dmig
 */
public final class Storage {
    
    private static final int START_FOOD_AMOUNT = 5000;
    private static final int START_MAX_FOOD_AMOUNT = 10000;
    
    private static final float START_FUEL_AMOUNT = 300;
    private static final float START_MAX_FUEL_AMOUNT = 500;
    
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
     * New default storage
     * @param empty if !empty this create basic storage for ship
     */
    public Storage(boolean empty) {
        if(!empty) {
            foodAmount = START_FOOD_AMOUNT;
            maxFoodAmount = START_MAX_FOOD_AMOUNT;
            fuelAmount = START_FUEL_AMOUNT;
            maxFuelAmount = START_MAX_FUEL_AMOUNT;
            medicineAmount = START_MEDICINE_AMOUNT;
            maxMedicineAmount = START_MAX_MEDICINE_AMOUNT;
            spareAmount = START_SPARE_AMOUNT;
            maxSpareAmount = START_MAX_SPARE_AMOUNT;
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Get and set">
    
    /**
     * Get the value of foodAmount
     *
     * @return the value of foodAmount
     */
    public int getFoodAmount() {
        return foodAmount;
    }
    
    /**
     * Set the value of foodAmount
     *
     * @param foodAmount new value of foodAmount
     */
    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
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
    
    /**
     * Set the value of fuelAmount
     *
     * @param fuelAmount new value of fuelAmount
     */
    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
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
    
    /**
     * Set the value of medicineAmount
     *
     * @param medicineAmount new value of medicineAmount
     */
    public void setMedicineAmount(short medicineAmount) {
        this.medicineAmount = medicineAmount;
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
    
    /**
     * Set the value of spareAmount
     *
     * @param spareAmount new value of spareAmount
     */
    public void setSpareAmount(short spareAmount) {
        this.spareAmount = spareAmount;
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
