/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import java.util.ArrayList;
import ru.dmig.infinityflight.logic.rooms.CabinRoom;

/**
 * Class for ship and all parameters of him
 *
 * @author Dmig
 */
public final class Ship {

    public ArrayList<Person> persons = new ArrayList<>();
    public ArrayList<Room> rooms = new ArrayList<>();

    private byte hour; // [0,4,8,12,16,20]

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
    
    private short numberOfDaysBeforeStation; // 0 = on station
    public Station station;
    
    /**
     * Start new game
     */
    public Ship() {
        foodAmount = 5000; //Start food
        potencialFoodAmount = 0;
        
        fCRoomAmount = 0;
        bCRoomAmount = 0;
        eCRoomAmount = 2;
        
        cabinAmount = 10;
        entertainmentPlacesAmount = 30;
        
        fuelAmount = 300; //Start fuel
        maxFuelAmount = 500;
        
        setNewRouteAndStation();
    }
    
    

    public void updateHour() {
        hour += 4;
        if (hour != 0) {
            if (hour != 0 && hour != 4) {
                if(hour != 12) {
                    foodAmount -= persons.size();
                }
            }
        } else {
            if(numberOfDaysBeforeStation != 1) {
                numberOfDaysBeforeStation--;
                if(fuelAmount != 1) fuelAmount--;
                else {
                    InfinityFlight.defeatProcess(InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FUEL);
                }
            } else {
                //Arrival to station
                setNewRouteAndStation();
            }
        }
        InfinityFlight.gui.update();
    }
    
    private void setNewRouteAndStation() {
        numberOfDaysBeforeStation = InfinityFlight.genNumOfDaysBeforeStation();
        station = InfinityFlight.genNewStation();
    }

    @Override
    public String toString() {
        return "Ship{" + "persons=" + persons + ", hour=" + hour + ", foodAmount=" + foodAmount + ", potencialFoodAmount=" + potencialFoodAmount + ", fCRoomAmount=" + fCRoomAmount + ", bCRoomAmount=" + bCRoomAmount + ", eCRoomAmount=" + eCRoomAmount + ", cabinAmount=" + cabinAmount + ", entertainmentPlacesAmount=" + entertainmentPlacesAmount + ", fuelAmount=" + fuelAmount + ", maxFuelAmount=" + maxFuelAmount + ", numberOfDaysBeforeStation=" + numberOfDaysBeforeStation + '}';
    }
    
    public short getPersonalAmount() {
        short amount = 0;
        for (int i = 0; i < persons.size(); i++) {
            Person get = persons.get(i);
            if (get instanceof Personal) {
                amount++;
            }
        }
        return amount;
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

    
    
    
    
    public int getMaxFoodAmount() {
        return maxFoodAmount;
    }

    public void setMaxFoodAmount(int maxFoodAmount) {
        this.maxFoodAmount = maxFoodAmount;
    }
    
    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
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
