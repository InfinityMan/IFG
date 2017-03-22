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

import ru.epiclib.base.Base;

/**
 * Class for peoples, who traveling on space for money
 *
 * @author Dmig
 */
public class Passenger extends Person {
    
    public static final byte[][] STATION_NUMS = {{1,2},{1,3},{2,5}};

    public CLASS passengerClass;
    
    private byte stationNumInFlight;

    public Passenger(CLASS passengerClass) {
        super();
        this.passengerClass = passengerClass;
        genStationNumInFlight();
    }
    
    /**
     * Gen the value of stationNumInFlight
     */
    private void genStationNumInFlight() {
        switch (passengerClass) {
            case THIRD:
                stationNumInFlight = (byte) Base.randomNumber(STATION_NUMS[0][0], STATION_NUMS[0][1]);
                break;
            case SECOND:
                stationNumInFlight = (byte) Base.randomNumber(STATION_NUMS[1][0], STATION_NUMS[1][1]);
                break;
            case FIRST:
                stationNumInFlight = (byte) Base.randomNumber(STATION_NUMS[2][0], STATION_NUMS[2][1]);
                break;
            default:
                throw new AssertionError();
        }
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
        THIRD, SECOND, FIRST
    }

}
