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
