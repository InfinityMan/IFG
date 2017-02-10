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
 * Class for engines on ship
 * @author Dmig
 */
public final class Engine {
    
    private double distancePerSecond;
    private long energyConsumption;

    private String name;

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
     * Get the value of energyConsumption
     *
     * @return the value of energyConsumption
     */
    public long getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * Set the value of energyConsumption
     *
     * @param energyConsumption new value of energyConsumption
     */
    public void setEnergyConsumption(long energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    /**
     * Get the value of distancePerSecond
     *
     * @return the value of distancePerSecond
     */
    public double getDistancePerSecond() {
        return distancePerSecond;
    }

    /**
     * Set the value of distancePerSecond
     *
     * @param distancePerSecond new value of distancePerSecond
     */
    public void setDistancePerSecond(double distancePerSecond) {
        this.distancePerSecond = distancePerSecond;
    }

    
}
