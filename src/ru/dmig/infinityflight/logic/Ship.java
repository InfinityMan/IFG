/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import java.util.ArrayList;
import ru.dmig.infinityflight.logic.exceptions.ReactorBrokenException;
import ru.dmig.infinityflight.logic.rooms.CabinRoom;

/**
 * Class for ship and all parameters of him
 *
 * @author Dmig
 */
public final class Ship {

    public ArrayList<Personal> personel = new ArrayList<>();
    public ArrayList<Passenger> passengers = new ArrayList<>();
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Reactor> reactors = new ArrayList<>();

    private int foodAmount;
    private int maxFoodAmount;
    private int potencialFoodAmount;

    private short fCRoomAmount;
    private short bCRoomAmount;
    private short eCRoomAmount;

    private short cabinAmount;
    private short entertainmentPlacesAmount;

    private int fuelAmount;
    private int maxFuelAmount;
    
    private long energyAmount;
    
    private short spareAmount;
    
    private short numberOfDaysBeforeStation; // 0 = on station
    public Station station;
    
    /**
     * Start new game
     */
    public Ship() {
        
        personel = InfinityFlight.getStartPersonal();
        
        foodAmount = 5000; //Start food
        maxFoodAmount = 10000;
        potencialFoodAmount = 0;
        
        fCRoomAmount = 0;
        bCRoomAmount = 0;
        eCRoomAmount = 2;
        
        cabinAmount = 10;
        entertainmentPlacesAmount = 30;
        
        fuelAmount = 300; //Start fuel
        maxFuelAmount = 500;
        
        energyAmount = 0;
        
        setNewRouteAndStation();
    }
    
    /**
     * Updating hour on ship: eating and energy
     * @param hour hour; need for information: eat or not to eat?
     */
    public void updateHour(byte hour) {
        boolean eat = (hour != 0 && hour != 4 && hour != 12);
        if (eat) {
            int amount = (personel.size() + passengers.size());
            if(getFoodAmount() >= amount) {
                setFoodAmount(getFoodAmount() - amount);
            } else {
                InfinityFlight.defeatProcess(InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FOOD);
            }
        }
        reactors.forEach((reactor) -> {
            try {
                energyAmount += reactor.getEnergy();
            } catch (ReactorBrokenException ex) {}
        });
        
        //Energy compusition
        
        InfinityFlight.gui.update();
    }
    
    public void updateDay() {
        if (numberOfDaysBeforeStation != 1) {
            numberOfDaysBeforeStation--;
            if (fuelAmount != 1) {
                fuelAmount--;
            } else {
                //if(energyAmount == 0) {
                    InfinityFlight.defeatProcess(InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FUEL);
                //}
            }
        } else {
            //Arrival to station
            setNewRouteAndStation();
        }
        InfinityFlight.gui.update();
    }
    
    private void setNewRouteAndStation() {
        numberOfDaysBeforeStation = InfinityFlight.genNumOfDaysBeforeStation();
        station = InfinityFlight.genNewStation();
    }

    @Override
    public String toString() {
        return "Ship{" + "personel=" + personel + ", foodAmount=" + foodAmount + ", potencialFoodAmount=" + potencialFoodAmount + ", fCRoomAmount=" + fCRoomAmount + ", bCRoomAmount=" + bCRoomAmount + ", eCRoomAmount=" + eCRoomAmount + ", cabinAmount=" + cabinAmount + ", entertainmentPlacesAmount=" + entertainmentPlacesAmount + ", fuelAmount=" + fuelAmount + ", maxFuelAmount=" + maxFuelAmount + ", numberOfDaysBeforeStation=" + numberOfDaysBeforeStation + '}';
    }
    
    public short getMaxPersonalAmount() {
        short amount = 0;
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room instanceof CabinRoom) {
                CabinRoom cr = (CabinRoom) room;
                amount += cr.getPlaceAmount();
            }
        }
        return amount;
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
     * Get the value of energyAmount
     *
     * @return the value of energyAmount
     */
    public long getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Set the value of energyAmount
     *
     * @param energyAmount new value of energyAmount
     */
    public void setEnergyAmount(long energyAmount) {
        this.energyAmount = energyAmount;
    }
    
    
    public int getMaxFoodAmount() {
        return maxFoodAmount;
    }

    public void setMaxFoodAmount(int maxFoodAmount) {
        this.maxFoodAmount = maxFoodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getPotencialFoodAmount() {
        return potencialFoodAmount;
    }

    public void setPotencialFoodAmount(int potencialFoodAmount) {
        this.potencialFoodAmount = potencialFoodAmount;
    }

    public short getfCRoomAmount() {
        return fCRoomAmount;
    }

    public void setfCRoomAmount(short fCRoomAmount) {
        this.fCRoomAmount = fCRoomAmount;
    }

    public short getbCRoomAmount() {
        return bCRoomAmount;
    }

    public void setbCRoomAmount(short bCRoomAmount) {
        this.bCRoomAmount = bCRoomAmount;
    }

    public short geteCRoomAmount() {
        return eCRoomAmount;
    }

    public void seteCRoomAmount(short eCRoomAmount) {
        this.eCRoomAmount = eCRoomAmount;
    }

    public short getCabinAmount() {
        return cabinAmount;
    }

    public void setCabinAmount(short cabinAmount) {
        this.cabinAmount = cabinAmount;
    }

    public short getEntertainmentPlacesAmount() {
        return entertainmentPlacesAmount;
    }

    public void setEntertainmentPlacesAmount(short entertainmentPlacesAmount) {
        this.entertainmentPlacesAmount = entertainmentPlacesAmount;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public int getMaxFuelAmount() {
        return maxFuelAmount;
    }

    public void setMaxFuelAmount(int maxFuelAmount) {
        this.maxFuelAmount = maxFuelAmount;
    }

    public short getNumberOfDaysBeforeStation() {
        return numberOfDaysBeforeStation;
    }

    public void setNumberOfDaysBeforeStation(short numberOfDaysBeforeStation) {
        this.numberOfDaysBeforeStation = numberOfDaysBeforeStation;
    }
}
