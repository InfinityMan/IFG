/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
