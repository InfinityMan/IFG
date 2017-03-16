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
package ru.dmig.infinityflight.res;

import java.io.IOException;
import static java.lang.Float.valueOf;
import static java.lang.System.exit;
import ru.dmig.infinityflight.logic.*;
import static ru.dmig.infinityflight.logic.Room.Prestige.BAD;
import static ru.dmig.infinityflight.logic.Room.Prestige.GOOD;
import static ru.dmig.infinityflight.logic.Room.Prestige.NORMAL;
import static ru.dmig.infinityflight.logic.Room.Prestige.TERRIBLE;
import ru.dmig.infinityflight.logic.rooms.*;
import static ru.dmig.infinityflight.logic.rooms.TouristRoom.Class.FIRST;
import static ru.dmig.infinityflight.logic.rooms.TouristRoom.Class.SECOND;
import static ru.dmig.infinityflight.logic.rooms.TouristRoom.Class.THIRD;

/**
 * Class for loading defaults (Reactors;Engines;Tourist rooms;Cabins..)
 * @author Dmig
 */
public class Defaulter {

    public static TouristRoom[][] loadDefaultTouristRooms() {
        TouristRoom[][] trRooms = null;

        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("touristRooms");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        if (alpha != null) {
            for (int i = 0; i < alpha.length; i++) {
                String[] beta = alpha[i].split(";");
                for (int j = 0; j < beta.length; j++) {
                    if (i == 0 && j == 0) {
                        trRooms = new TouristRoom[alpha.length][];
                    }
                    if (j == 0) {
                        trRooms[i] = new TouristRoom[beta.length];
                    }
                    String[] gamm = beta[j].split(",");

                    String typeName;
                    TouristRoom.Class cl = THIRD;
                    Room.Prestige pr = TERRIBLE;
                    byte placeAmount;

                    typeName = gamm[0]; //TypeName

                    switch (i) { //Class
                        case 0:
                            cl = THIRD;
                            break;
                        case 1:
                            cl = SECOND;
                            break;
                        case 2:
                            cl = FIRST;
                            break;
                        default:
                            throw new AssertionError();
                    }

                    switch (Integer.valueOf(gamm[1])) {
                        case 0:
                            pr = TERRIBLE;
                            break;
                        case 1:
                            pr = BAD;
                            break;
                        case 2:
                            pr = NORMAL;
                            break;
                        case 3:
                            pr = GOOD;
                            break;
                        default:
                            throw new AssertionError();
                    }

                    placeAmount = Byte.valueOf(gamm[2]);

                    trRooms[i][j] = new TouristRoom(cl, typeName, pr, placeAmount);
                }
            }
        } else {
            exit(-2);
        }
        return trRooms;
    }

    public static CabinRoom[] loadDefaultCabin() {
        CabinRoom[] cbRooms = null;

        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("cabinRooms");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        if (alpha != null) {
            for (int i = 0; i < alpha.length; i++) {
                if (i == 0) {
                    cbRooms = new CabinRoom[alpha.length];
                }
                String[] beta = alpha[i].split(",");

                String typeName;
                Room.Prestige pr = TERRIBLE;
                byte placeAmount;

                typeName = beta[0]; //TypeName

                switch (Integer.valueOf(beta[1])) {
                    case 0:
                        pr = TERRIBLE;
                        break;
                    case 1:
                        pr = BAD;
                        break;
                    case 2:
                        pr = NORMAL;
                        break;
                    case 3:
                        pr = GOOD;
                        break;
                    default:
                        throw new AssertionError();
                }

                placeAmount = Byte.valueOf(beta[2]);

                cbRooms[i] = new CabinRoom(typeName, pr, placeAmount);
            }
        } else {
            exit(-2);
        }
        return cbRooms;
    }
    
    public static Reactor[] loadDefaultReactors() {
        Reactor[] rs = null;

        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("reactors");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        if (alpha != null) {
            for (int i = 0; i < alpha.length; i++) {
                if (i == 0) {
                    rs = new Reactor[alpha.length];
                }
                String[] beta = alpha[i].split(";");

                String name;
                float fuelConsumption;
                float efficiency;

                name = beta[0];

                fuelConsumption = valueOf(beta[1]);

                efficiency = (valueOf(beta[2]) / 100);

                rs[i] = new Reactor(name, fuelConsumption, efficiency);
            }
        } else {
            exit(-2);
        }
        return rs;
    }
    
    public static Engine[] loadDefaultEngines() {
        Engine[] es = null;

        Link l = new Link();
        String[] alpha = null;
        try {
            alpha = l.readResArray("engines");
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        if (alpha != null) {
            for (int i = 0; i < alpha.length; i++) {
                if (i == 0) {
                    es = new Engine[alpha.length];
                }
                String[] beta = alpha[i].split(";");

                String name;
                float energyConsumption;
                float efficiency;

                name = beta[0];

                energyConsumption = valueOf(beta[1]);

                efficiency = (valueOf(beta[2]) / 100);

                es[i] = new Engine(name, energyConsumption, efficiency);
            }
        } else {
            exit(-2);
        }
        return es;
    }
    
}
