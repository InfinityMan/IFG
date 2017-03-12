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

/**
 *
 * @author Dmig
 */
public final class Updater extends Thread {
    
    private static float tick = 1000;
    
    public Ship ship;
    private boolean ended = false;

    public Updater(Ship ship) {
        this.ship = ship;
        this.setName("Updater");
    }

    @Override
    public void run() {
        byte hour = 0; // 0;4;8;12;16;20
        while(!ended) {
            try {
                Thread.sleep(Math.round(tick));
                ship.updateHour(hour); //in case of edit: edit update hour
                
                if(hour != 20) hour = (byte)(hour + 4);
                else hour = 0;
            } catch (InterruptedException ex) {
                System.out.println(ex);
                System.exit(1);
            }
        }
    }
    
    public void end() {
        ended = true;
    }
    
    /**
     * Change tick of game
     * @param half If half - halfes tick; else doubles
     */
    public static void changeTick(boolean half) {
        if(half) tick = tick/2;
        else tick = tick*2;
    }

    public static float getTick() {
        return tick;
    }
    
}
