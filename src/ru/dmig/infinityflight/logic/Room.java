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

import ru.dmig.infinityflight.logic.human.Person;

/**
 *
 * @author Dmig
 */
public abstract class Room {
    
    
    private final String typeName;
    
    private final Prestige prestige;
    private final byte placeAmount;
    
    private byte freePlaces;
    
    private Person[] persons;

    public Room(String typeName, Prestige prestige, byte placeAmount) {
        this.typeName = typeName;
        this.prestige = prestige;
        this.placeAmount = placeAmount;
        
        freePlaces = this.placeAmount;
        persons = new Person[this.placeAmount];
        
    }
    
    /**
     * Get the value of freePlaces
     *
     * @return the value of freePlaces
     */
    public byte getFreePlaces() {
        return freePlaces;
    }

    /**
     * Set the value of freePlaces
     *
     * @param freePlaces new value of freePlaces
     */
    public void setFreePlaces(byte freePlaces) {
        this.freePlaces = freePlaces;
    }


    /**
     * Get the value of persons
     *
     * @return the value of persons
     */
    public Person[] getPersons() {
        return persons;
    }

    /**
     * Set the value of persons
     *
     * @param persons new value of persons
     */
    public void setPersons(Person[] persons) {
        this.persons = persons;
    }


    @Override
    public String toString() {
        return "Room{" + "typeName=" + typeName + ", prestige=" + prestige + ", placeAmount=" + placeAmount + '}';
    }
    
    /**
     * Get the value of typeName
     *
     * @return the value of typeName
     */
    public String getTypeName() {
        return typeName;
    }
    
    /**
     * Get the value of prestige
     *
     * @return the value of prestige
     */
    public Prestige getPrestige() {
        return prestige;
    }
    
    /**
     * Get the value of placeAmount
     *
     * @return the value of placeAmount
     */
    public byte getPlaceAmount() {
        return placeAmount;
    }
    
    public static enum Prestige {
        TERRIBLE,BAD,NORMAL,GOOD
    }
    
}
