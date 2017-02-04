/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import ru.dmig.infinityflight.logic.exceptions.*;
import ru.epiclib.base.Base;

/**
 * Class for reactor on ship
 * @author Dmig
 */
public final class Reactor {
    
    public static final float BONUS_PRODUCTION_PERCENT = 1.1f;
    public static final float CHANCE_OF_BREAK_REACTOR = 0.14f;
    
    private double energyPerSecond;
    
    private boolean broken;

    public Reactor(double energyPerSecond) {
        this.energyPerSecond = energyPerSecond;
        broken = false;
    }
    
    public long getEnergy() throws ReactorBrokenException {
        if(broken) throw new ReactorBrokenException();
        double maxEnergy = energyPerSecond*BONUS_PRODUCTION_PERCENT;
        double minEnergy = energyPerSecond * (BONUS_PRODUCTION_PERCENT - ((BONUS_PRODUCTION_PERCENT - 1) * 2));
        
        if(Base.chance((int)(CHANCE_OF_BREAK_REACTOR*100), 2)) breakThis();
        
        return Base.randomNumber(Math.round(minEnergy), Math.round(maxEnergy));
    }

    /**
     * Get the value of broken
     *
     * @return the value of broken
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     * Set the value of broken false
     */
    public void breakThis() {
        broken = true;
    }
    
    /**
     * Set the value of broken true
     */
    public void fixThis() {
        broken = false;
    }

    /**
     * Get the value of energyPerSecond
     *
     * @return the value of energyPerSecond
     */
    public double getEnergyPerSecond() {
        return energyPerSecond;
    }

    /**
     * Set the value of energyPerSecond
     *
     * @param energyPerSecond new value of energyPerSecond
     */
    public void setEnergyPerSecond(double energyPerSecond) {
        this.energyPerSecond = energyPerSecond;
    }

}
