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

import ru.dmig.infinityflight.logic.exceptions.*;
import ru.epiclib.base.Base;

/**
 * Class for reactor on ship
 * @author Dmig
 */
public final class Reactor {
    
    public static final float CHANCE_OF_BREAK_REACTOR = 0.14f;
    
    private String name;
    
    private float fuelConsumption;
    private float efficiency;
    
    private boolean broken;

    public Reactor(String name, float fuelConsumption, float efficiency) {
        this.name = name;
        this.fuelConsumption = fuelConsumption;
        this.efficiency = efficiency;
        this.broken = false;
    }
    
    public int getEnergy() throws EngineBrokenException {
        if(broken) throw new EngineBrokenException();
        
        if(Base.chance((int)(CHANCE_OF_BREAK_REACTOR*100), 2)) breakThis();
        
        return Math.round(fuelConsumption*efficiency);
    }
    
    /**
     * Get the value of fuelConsumption
     *
     * @return the value of fuelConsumption
     */
    public float getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Set the value of fuelConsumption
     *
     * @param fuelConsumption new value of fuelConsumption
     */
    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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
     * Get the value of efficiency
     *
     * @return the value of efficiency
     */
    public float getEfficiency() {
        return efficiency;
    }

    /**
     * Set the value of efficiency
     *
     * @param efficiency new value of efficiency
     */
    public void setEfficiency(float efficiency) {
        if(efficiency > 0 && efficiency <= 1) {
            this.efficiency = efficiency;
        } else throw new IllegalArgumentException();
    }

}
