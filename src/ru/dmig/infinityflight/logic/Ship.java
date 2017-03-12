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

import java.util.ArrayList;
import ru.dmig.infinityflight.logic.exceptions.EngineBrokenException;
import ru.dmig.infinityflight.logic.human.*;
import ru.dmig.infinityflight.logic.rooms.CabinRoom;

/**
 * Class for ship and all parameters of him
 *
 * @author Dmig
 */
public final class Ship {

    public final Storage storage;

    public ArrayList<Personal> personel = new ArrayList<>();
    public ArrayList<Passenger> passengers = new ArrayList<>();
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Reactor> reactors = new ArrayList<>();
    public ArrayList<Engine> engines = new ArrayList<>();

    private int potencialFoodAmount;

    private long energyAmount;

    private double distanceToStation; // 0 = on station 10x days
    public Station station;

    /**
     * Start new game
     */
    public Ship() {

        storage = new Storage(false);

        personel = InfinityFlight.getStartPersonal();

        potencialFoodAmount = 0;

        rooms.add(InfinityFlight.DEFAULT_TOURIST_ROOMS[0][1]); // "Heit"
        rooms.add(InfinityFlight.DEFAULT_TOURIST_ROOMS[0][0]); // "Dorm"

        rooms.add(InfinityFlight.DEFAULT_CABIN_ROOMS[0]);
        rooms.add(InfinityFlight.DEFAULT_CABIN_ROOMS[0]);

        energyAmount = 0;

        setNewRouteAndStation();
    }

    /**
     * Updating hour on ship: eating and energy
     *
     * @param hour hour; need for information: eat or not to eat?
     */
    public void updateHour(byte hour) {
        boolean eat = (hour != 0 && hour != 4 && hour != 12);
        if (eat) {
            int amount = (personel.size() + passengers.size());
            if (storage.getFoodAmount() >= amount) {
                storage.setFoodAmount(storage.getFoodAmount() - amount);
            } else {
                InfinityFlight.defeatProcess(InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FOOD);
            }
        }

        if (storage.getFuelAmount() != 0) {
            for (Reactor reactor : reactors) {
                try {
                    energyAmount += reactor.getEnergy();
                    if (storage.getFuelAmount() > reactor.getFuelConsumption()) {
                        storage.setFuelAmount(storage.getFuelAmount() - reactor.getFuelConsumption());
                    } else {
                        storage.setFuelAmount(0);
                        break;
                    }
                } catch (EngineBrokenException ex) {
                }
            }
        }

        if (distanceToStation != 0 && energyAmount != 0) {
            for (Engine engine : engines) {
                try {
                    if (energyAmount > engine.getEnergyConsumption()) {
                        energyAmount -= engine.getEnergyConsumption();
                    } else {
                        energyAmount = 0;
                        break;
                    }
                    int distancePassed = engine.goDistance();
                    if (distanceToStation > distancePassed) {
                        distanceToStation -= distancePassed;
                    } else {
                        distanceToStation = 0;
                        arriveToStation();
                        break;
                    }
                } catch (EngineBrokenException ex) {
                }
            }
        }
        /* If distanceToStation == 0 => ship on station and engines off */

        if (storage.getFuelAmount() == 0 && energyAmount == 0) {
            InfinityFlight.defeatProcess(InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FUEL);
        }

        //Energy consumption to rooms
        InfinityFlight.gui.update();
    }

    private void arriveToStation() {

    }

    private void setNewRouteAndStation() {
        distanceToStation = InfinityFlight.genDistanceToStation();
        station = InfinityFlight.genNewStation();
    }

    public short getMaxPersonalAmount() {
        short amount = 0;
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            if (room instanceof CabinRoom) {
                CabinRoom cr = (CabinRoom) room;
                amount += cr.getPlaceAmount();
            }
        }
        return amount;
    }

    /**
     * Get the value of energyAmount
     *
     * @return the value of energyAmount
     */
    public long getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Set the value of energyAmount
     *
     * @param energyAmount new value of energyAmount
     */
    public void setEnergyAmount(long energyAmount) {
        this.energyAmount = energyAmount;
    }

    public int getPotencialFoodAmount() {
        return potencialFoodAmount;
    }

    public void setPotencialFoodAmount(int potencialFoodAmount) {
        this.potencialFoodAmount = potencialFoodAmount;
    }

    public double getDistanceToStation() {
        return distanceToStation;
    }

    public void setDistanceToStation(double numberOfDaysBeforeStation) {
        this.distanceToStation = numberOfDaysBeforeStation;
    }

}
