/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

/**
 *
 * @author Dmig
 */
public final class ResearchNode {
    
    private String name;
    
    private int pointAmount;
    
    /**
     * Get the value of pointAmount
     *
     * @return the value of pointAmount
     */
    public int getPointAmount() {
        return pointAmount;
    }

    /**
     * Set the value of pointAmount
     *
     * @param pointAmount new value of pointAmount
     */
    public void setPointAmount(int pointAmount) {
        if(pointAmount < 0) throw new IllegalArgumentException("RPointAmount = " + pointAmount);
        this.pointAmount = pointAmount;
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

    
}
