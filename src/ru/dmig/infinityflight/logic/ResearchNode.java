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
 *
 * @author Dmig
 */
public class ResearchNode {
    
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
        if(pointAmount < 0) {
            throw new IllegalArgumentException("RPointAmount = " + pointAmount);
        }
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
