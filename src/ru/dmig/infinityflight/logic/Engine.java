/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
