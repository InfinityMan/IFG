/*
 * Copyright (C) 2017 Dmig
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
public class Game {
    
    private String playerName;
    public Ship ship;
    
    private int passedStations;

    public Game(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the value of playerName
     *
     * @return the value of playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set the value of playerName
     *
     * @param playerName new value of playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the value of passedStations
     *
     * @return the value of passedStations
     */
    public int getPassedStations() {
        return passedStations;
    }

    /**
     * Set the value of passedStations
     *
     * @param passedStations new value of passedStations
     */
    public void setPassedStations(int passedStations) {
        this.passedStations = passedStations;
    }
    
}
