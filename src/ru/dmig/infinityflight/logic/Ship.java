/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import java.util.ArrayList;

/**
 * Class for ship and all parameters of him
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
    
    public void updateHour() {
        hour += 4;
        if(hour != 0 && hour != 4) {
            //eating
        }
    }
    
}
