/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic.human;

/**
 * Class for peoples, who traveling on space
 *
 * @author Dmig
 */
public final class Passenger extends Person {

    public static enum CLASS {
        FIRST, SECOND, THIRD
    };

    public CLASS passengerClass;

    public Passenger() {
        super();
    }

    public Passenger(CLASS pasClass) {
        this();
        passengerClass = pasClass;
    }

    public Passenger(CLASS passengerClass, String sector) {
        super(sector);
        this.passengerClass = passengerClass;
    }

    public Passenger(CLASS passengerClass, String[] codes) {
        super(codes);
        this.passengerClass = passengerClass;
    }

}
