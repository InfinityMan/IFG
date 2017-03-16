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

import static java.lang.Math.round;
import ru.dmig.infinityflight.logic.exceptions.EngineBrokenException;
import static ru.epiclib.base.Base.chance;

/**
 * Class for engines on ship
 * @author Dmig
 */
public class Engine {
    public static final float CHANCE_OF_BREAK_ENGINE = 0.14f;
    
    private String name;
    
    private float energyConsumption;
    private float efficiency;
    
    private boolean broken;

    public Engine(String name, float fuelConsumption, float efficiency) {
        this.name = name;
        this.energyConsumption = fuelConsumption;
        this.efficiency = efficiency;
        this.broken = false;
    }
    
    public int goDistance() throws EngineBrokenException {
        if(broken) {
            throw new EngineBrokenException();
        }
        
        if(chance((int)(CHANCE_OF_BREAK_ENGINE*100), 2)) {
            breakThis();
        }
        
        return round(energyConsumption*efficiency);
    }

    @Override
    public String toString() {
        return "Engine{" + "name=" + name + ", energyConsumption=" + energyConsumption + ", efficiency=" + efficiency + ", broken=" + broken + '}';
    }
    
    /**
     * Get the value of fuelConsumption
     *
     * @return the value of fuelConsumption
     */
    public float getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * Set the value of fuelConsumption
     *
     * @param fuelConsumption new value of fuelConsumption
     */
    public void setEnergyConsumption(float fuelConsumption) {
        this.energyConsumption = fuelConsumption;
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
        } else {
            throw new IllegalArgumentException();
        }
    }

}
