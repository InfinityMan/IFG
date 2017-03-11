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
package ru.dmig.infinityflight.logic.rooms;

import ru.dmig.infinityflight.logic.*;

/**
 *
 * @author Dmig
 */
public final class TouristRoom extends Room {

    public static enum Class {
        FIRST, SECOND, THIRD
    };
    

    private Class roomClass;

    public TouristRoom(Class roomClass, String typeName, Prestige prestige, byte placeAmount) {
        this.roomClass = roomClass;
        setTypeName(typeName);
        setPrestige(prestige);
        setPlaceAmount(placeAmount);
    }
    
    /**
     * Get the value of roomClass
     *
     * @return the value of roomClass
     */
    public Class getRoomClass() {
        return roomClass;
    }

    /**
     * Set the value of roomClass
     *
     * @param roomClass new value of roomClass
     */
    public void setRoomClass(Class roomClass) {
        this.roomClass = roomClass;
    }

}
