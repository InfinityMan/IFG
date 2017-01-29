/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

import ru.epiclib.base.Base;

/**
 *
 * @author Dmig
 */
public final class Code {
    
    /*
    
    CODE:
    
    Code syntax: "Sector:subSector(d):entityBlock(d):entityID(BL)
    Max variants: 11*10*10*26 = 28'600
    
    Code example: "Echo:6:2:D"
    
    */
    
    public static final String[] SECTORS_NAMES = {"ALPHA","BETA","GAMMA","DELTA",
        "THETA","ETA","SIGMA","ECHO","OMEGA"};
    
    private String sector;
    private short block;
    private String idenID;
    
    
    
    public Code() {
        sector = getRandomSector();
        block = (short) Base.randomNumber(0, 9);
        idenID = Base.randomString(1, false, true, false);
    }
    
    public Code(String sector) {
        if(testSector(sector)) {
            this.sector = sector;
        } else throw new IllegalArgumentException("Wrong sector name: "+sector);
        block = (short) Base.randomNumber(0, 9);
        idenID = Base.randomString(1, false, true, false);
    }
    
    public static String getRandomSector() { return SECTORS_NAMES[Base.randomNumber(0, SECTORS_NAMES.length-1)]; }

    @Override
    public String toString() {
        return sector + ":" + block + ":"+ idenID;
    }
    
    public static boolean testSector(String sector) {
        for (String string : SECTORS_NAMES) {
            if(string == null ? sector == null : string.equals(sector)) return true;
        }
        return false;
    }
    
    

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        if(testSector(sector)) {
            this.sector = sector;
        } else throw new IllegalArgumentException("Wrong sector name: "+sector);
    }

    public short getBlock() {
        return block;
    }

    public void setBlock(short block) {
        this.block = block;
    }

    public String getIdenID() {
        return idenID;
    }

    public void setIdenID(String idenID) {
        this.idenID = idenID;
    }
    
    
    
}
