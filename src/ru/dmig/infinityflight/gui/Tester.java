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
package ru.dmig.infinityflight.gui;

import static java.lang.System.out;
import static ru.dmig.infinityflight.gui.StationGui.start;
import static ru.dmig.infinityflight.logic.InfinityFlight.game;
import ru.dmig.infinityflight.logic.*;

/**
 *
 * @author Dmig
 */
public class Tester {
    public static void main(String[] args) {
        
        game.ship = new Ship();

        for (int i = 0; i < game.ship.rooms.size(); i++) {
            Room get = game.ship.rooms.get(i);
            out.println(get);
        }
        
        out.println(game.ship.engines.get(0).toString());
        out.println(game.ship.reactors.get(0).toString());

        start();

    }
}
