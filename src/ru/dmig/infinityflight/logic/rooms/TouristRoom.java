/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic.rooms;

import ru.dmig.infinityflight.logic.*;

/**
 *
 * @author Dmig
 */
public final class TouristRoom extends Room {
    
    public static enum CLASS {FIRST,SECOND,THIRD};
    
    private byte placeAmount;

    /**
     * Get the value of placeAmount
     *
     * @return the value of placeAmount
     */
    public byte getPlaceAmount() {
        return placeAmount;
    }

    /**
     * Set the value of placeAmount
     *
     * @param placeAmount new value of placeAmount
     */
    public void setPlaceAmount(byte placeAmount) {
        this.placeAmount = placeAmount;
    }
    
}
