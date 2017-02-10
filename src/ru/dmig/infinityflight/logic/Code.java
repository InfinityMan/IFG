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
    public static final String[] SECTORS_NAMES = {"ALPHA", "BETA", "GAMMA", "DELTA",
        "THETA", "ETA", "SIGMA", "ECHO", "OMEGA"};

    private String sector;
    private short block;
    private String idenID;

    public Code() {
        sector = getRandomSector();
        block = (short) Base.randomNumber(0, 9);
        idenID = Base.randomString(1, false, true, false);
    }

    public Code(String sector) {
        if (testSector(sector)) {
            this.sector = sector;
        } else {
            throw new IllegalArgumentException("Wrong sector name: " + sector);
        }
        block = (short) Base.randomNumber(0, 9);
        idenID = Base.randomString(1, false, true, false);
    }

    public static String getRandomSector() {
        return SECTORS_NAMES[Base.randomNumber(0, SECTORS_NAMES.length - 1)];
    }

    @Override
    public String toString() {
        return sector + ":" + block + ":" + idenID;
    }

    public static boolean testSector(String sector) {
        for (String string : SECTORS_NAMES) {
            if (string == null ? sector == null : string.equals(sector)) {
                return true;
            }
        }
        return false;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        if (testSector(sector)) {
            this.sector = sector;
        } else {
            throw new IllegalArgumentException("Wrong sector name: " + sector);
        }
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
