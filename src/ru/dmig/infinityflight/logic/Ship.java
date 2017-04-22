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

import static java.lang.System.err;
import static java.lang.System.out;
import java.util.ArrayList;
import ru.dmig.infinityflight.gui.StationGui;
import static ru.dmig.infinityflight.gui.StationGui.stationGui;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFAULT_CABIN_ROOMS;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFAULT_ENGINES;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFAULT_REACTORS;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFAULT_TOURIST_ROOMS;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FOOD;
import static ru.dmig.infinityflight.logic.InfinityFlight.DEFEAT_STATE.RUN_OUT_OF_FUEL;
import static ru.dmig.infinityflight.logic.InfinityFlight.defeatProcess;
import static ru.dmig.infinityflight.logic.InfinityFlight.genDistanceToStation;
import static ru.dmig.infinityflight.logic.InfinityFlight.genNewStation;
import static ru.dmig.infinityflight.logic.InfinityFlight.getStartPersonal;
import static ru.dmig.infinityflight.logic.InfinityFlight.gui;
import ru.dmig.infinityflight.logic.exceptions.EngineBrokenException;
import ru.dmig.infinityflight.logic.exceptions.NotEnoughtMoneyException;
import ru.dmig.infinityflight.logic.exceptions.StorageEmptyException;
import ru.dmig.infinityflight.logic.exceptions.StorageOverfilledException;
import ru.dmig.infinityflight.logic.human.*;
import ru.dmig.infinityflight.logic.rooms.CabinRoom;

/**
 * Class for ship and all parameters of him
 *
 * @author Dmig
 */
public class Ship {
    
    public final Storage storage;

    public ArrayList<Personal> personel = new ArrayList<>();
    public ArrayList<Passenger> passengers = new ArrayList<>();
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Reactor> reactors = new ArrayList<>();
    public ArrayList<Engine> engines = new ArrayList<>();

    private int potencialFoodAmount;
    
    private double money;

    private long energyAmount;

    private double distanceToStation; // 0 = on station; 12x days
    public Station station;
    
    private int timeOnStationRemaining; //0 = not on station; in hours

    /**
     * Start new game
     */
    public Ship() {

        storage = new Storage(false);
        
        money = InfinityFlight.START_MONEY_AMOUNT;

        personel = getStartPersonal();

        potencialFoodAmount = 0;

        rooms.add(DEFAULT_TOURIST_ROOMS[0][1]); // "Heit"
        rooms.add(DEFAULT_TOURIST_ROOMS[0][0]); // "Dorm"

        rooms.add(DEFAULT_CABIN_ROOMS[0]);
        rooms.add(DEFAULT_CABIN_ROOMS[0]);
        
        engines.add(DEFAULT_ENGINES[0]);
        reactors.add(DEFAULT_REACTORS[0]);

        energyAmount = 0;

        setNewRouteAndStation();
    }
    
    public double getMoney() {
        return money;
    }
    
    /**
     * In case of increase or reduce money is not enought. No safe!
     * @param amount Future amount of money
     */
    public void setMoney(double amount) {
        if(amount < 0) throw new IllegalArgumentException();
        money = amount;
    }
    
    /**
     * Increase amount of money
     * @param amount amount of money plus
     */
    public void increaseMoney(double amount) {
        money += amount;
    }
    
    /**
     * Reduce amount of money
     * @param amount amount of money minus
     * @throws NotEnoughtMoneyException
     */
    public void reduceMoney(double amount) throws NotEnoughtMoneyException {
        if(money >= amount) {
            money -= amount;
        } else {
            money = 0;
            throw new NotEnoughtMoneyException(amount - money);
        }
    }

    /**
     * Updating hour on ship: eating and energy
     *
     * @param hour hour; need for information: eat or not to eat?
     */
    public void updateHour(byte hour) {
        
        try {
            eat(hour); //Can't throw exception
            getEnergy(); //Can't throw exception
        } catch (StorageOverfilledException n) {err.println(n);}
        
        if(distanceToStation != 0) {
            goDistance();
        } else {
            if(timeOnStationRemaining != 4) {
                timeOnStationRemaining -= 4;
            } else {
                timeOnStationRemaining = 0;
                departureFromStation();
            }
        }
        /* If distanceToStation == 0 => ship on station and engines off */

        if (storage.getFuelAmount() == 0 && energyAmount == 0) {
            defeatProcess(RUN_OUT_OF_FUEL);
        }

        //Energy consumption to rooms
        
        gui.update();
    }

    private void eat(byte hour) throws StorageOverfilledException {
        boolean eat = (hour != 0 && hour != 4 && hour != 12);
        if (eat) {
            int amount = (personel.size() + passengers.size());
            try {
                storage.reduceFood(amount);
            } catch (StorageEmptyException ex) {
                defeatProcess(RUN_OUT_OF_FOOD);
            }
        }
    }

    private void getEnergy() throws StorageOverfilledException {
        if (storage.getFuelAmount() != 0) {
            for (Reactor reactor : reactors) {
                try {
                    energyAmount += reactor.getEnergy();
                    try {
                        storage.reduceFuel(reactor.getFuelConsumption());
                    } catch (StorageEmptyException ex) {
                        break;
                    }
                } catch (EngineBrokenException n) {
                    out.println(n);
                }
            }
        }
    }

    private void goDistance() {
        if (energyAmount != 0) {
            for (Engine engine : engines) {
                try {
                    int distancePassed = engine.goDistance();
                    if (distanceToStation > distancePassed) {
                        distanceToStation -= distancePassed;
                    } else {
                        distanceToStation = 0;
                        arriveToStation();
                        break;
                    }
                    if (energyAmount >= engine.getEnergyConsumption()) {
                        energyAmount -= engine.getEnergyConsumption();
                    } else {
                        energyAmount = 0;
                        break;
                    }
                } catch (EngineBrokenException ex) {
                }
            }
        }
    }

    private void arriveToStation() {
        InfinityFlight.game.addPassedStation();
        
        //exit passengers
        ArrayList<Passenger> newPassengers = passengers;
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            passenger.setStationRemaining((byte) (passenger.getStationRemaining() - 1));
            if(passenger.getStationRemaining() == 0) {
                InfinityFlight.game.addPassengersTransfered();
                increaseMoney(passenger.pay());
                newPassengers.remove(i);
            }
        }
        
        StationGui.start();
    }
    
    public void departureFromStation() {
        stationGui.dispose();
        setNewRouteAndStation();
    }

    private void setNewRouteAndStation() {
        distanceToStation = genDistanceToStation();
        station = genNewStation();
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
