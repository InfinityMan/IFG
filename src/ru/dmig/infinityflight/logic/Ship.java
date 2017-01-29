/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import java.util.ArrayList;

/**
 * Class for ship and all parameters of him
 *
 * @author Dmig
 */
public final class Ship {

    public ArrayList<Person> persons = new ArrayList<>();

    private byte hour; // [0,4,8,12,16,20]

    private int foodAmount;
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
        
        numberOfDaysBeforeStation = InfinityFlight.genNumOfDaysBeforeStation();
        station = InfinityFlight.genNewStation();
    }
    
    

    public void updateHour() {
        hour += 4;
        if (hour != 0) {
            if (hour != 0 && hour != 4) {
                //eating
            }
        } else {
            //Day updating
            //Fuel--
        }
    }

    @Override
    public String toString() {
        return "Ship{" + "persons=" + persons + ", hour=" + hour + ", foodAmount=" + foodAmount + ", potencialFoodAmount=" + potencialFoodAmount + ", fCRoomAmount=" + fCRoomAmount + ", bCRoomAmount=" + bCRoomAmount + ", eCRoomAmount=" + eCRoomAmount + ", cabinAmount=" + cabinAmount + ", entertainmentPlacesAmount=" + entertainmentPlacesAmount + ", fuelAmount=" + fuelAmount + ", maxFuelAmount=" + maxFuelAmount + ", numberOfDaysBeforeStation=" + numberOfDaysBeforeStation + '}';
    }
    
    
    
    

}
