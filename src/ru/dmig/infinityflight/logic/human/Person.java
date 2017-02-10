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

import ru.dmig.infinityflight.logic.Code;
import ru.epiclib.base.Base;

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

    public static enum INTELLIGENCE {
        SILLY, NORMAL, SMART
    };

    public static enum POWER {
        WEAK, NORMAL, STRONG
    };

    //<editor-fold defaultstate="collapsed" desc="Characteristic">
    private Code code;

    private byte age;
    private boolean isMan;

    private INTELLIGENCE intelligence;
    private POWER power;

    //?:toString()
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Person() {
        code = new Code();

        isMan = Base.randomNumber(0, 1) != 0;
        age = (byte) Base.randomNumber(MIN_AGE, MAX_AGE);

    }

    public Person(String[] codes) {
        this();
        code = new Code();
        //expect
    }

    public Person(String sector) {
        this();

        code = new Code(sector);
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

    /**
     * Edit the sector of code of people
     *
     * @param sector new sector name
     */
    public void editSector(String sector) {
        Code a = getCode();
        a.setSector(sector);
        setCode(a);
    }

    @Override
    public String toString() {
        return code.toString() + "::Age:" + age + "::Gender:" + gender() + ";";
    }
    //</editor-fold>

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public Code getCode() {
        return code;
    }

    /**
     * Set the value of code
     *
     * @param code new value of code
     */
    public void setCode(Code code) {
        this.code = code;
    }

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
}
