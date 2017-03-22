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
package ru.dmig.infinityflight.logic.human;

/**
 * Class for peoples, who traveling on space
 *
 * @author Dmig
 */
public class Passenger extends Person {


    public CLASS passengerClass;
    
    private byte stationNumInFlight;

    public Passenger(CLASS passengerClass) {
        super();
        this.passengerClass = passengerClass;
    }
    
    /**
     * Get the value of stationNumInFlight
     *
     * @return the value of stationNumInFlight
     */
    public byte getStationNumInFlight() {
        return stationNumInFlight;
    }

    /**
     * Set the value of stationNumInFlight
     *
     * @param stationNumInFlight new value of stationNumInFlight
     */
    public void setStationNumInFlight(byte stationNumInFlight) {
        this.stationNumInFlight = stationNumInFlight;
    }

    
    public static enum CLASS {
        FIRST, SECOND, THIRD
    }

}
