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

import static ru.epiclib.base.Base.randomNumber;

/**
 * Class for any peolpe on the ship or on station
 *
 * @author Dmig
 */
public class Person {

    //CONSTANTS
    /**
     * Min age for people in game
     */
    public static final byte MIN_AGE = 15;

    /**
     * Max age for people in game
     */
    public static final byte MAX_AGE = 48;


    //<editor-fold defaultstate="collapsed" desc="Characteristic">

    private byte age;
    private boolean isMan;

    private INTELLIGENCE intelligence;
    private POWER power;

    //?:toString()
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Person() {

        isMan = randomNumber(0, 1) != 0;
        age = (byte) randomNumber(MIN_AGE, MAX_AGE);

    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Get the string "M" if this.isMan() else "W"
     *
     * @return "M" or "W" of gender
     */
    public String gender() {
        if (isMan()) {
            return "M";
        } else {
            return "W";
        }
    }

    @Override
    public String toString() {
        return "Age:" + age + "::Gender:" + gender() + ";";
    }
    //</editor-fold>

    /**
     * Get the value of man
     *
     * @return the value of man
     */
    public boolean isMan() {
        return isMan;
    }

    /**
     * Set the gender
     *
     * @param isMan new gender
     */
    public void setGender(boolean isMan) {
        this.isMan = isMan;
    }
    public static enum INTELLIGENCE {
        SILLY, NORMAL, SMART
    }
    public static enum POWER {
        WEAK, NORMAL, STRONG
    }
}
