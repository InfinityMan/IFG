/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic.human;

/**
 * Class for peoples, who do all work on ship
 *
 * @author Dmig
 */
public final class Personal extends Person {

    public static enum PROFESSION { 
        WORKER, ENGINEER, MEDIC, GUARD, BIOLOGIST
    }; /* In case of edit: in InfinityFlight:StartPersonalAmount */

    public PROFESSION profession;

    public Personal() {
        super();
    }

    public Personal(PROFESSION profession) {
        super();
        this.profession = profession;
    }

    public Personal(PROFESSION profession, String sector) {
        super(sector);
        this.profession = profession;
    }

    public Personal(PROFESSION profession, String[] codes) {
        super(codes);
        this.profession = profession;
    }

}
