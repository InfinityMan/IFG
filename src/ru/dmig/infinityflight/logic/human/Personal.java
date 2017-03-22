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
package ru.dmig.infinityflight.logic.human;

/**
 * Class for peoples, who do all work on ship
 *
 * @author Dmig
 */
public class Personal extends Person {


    public PROFESSION profession;

    public Personal() {
        super();
    }

    public Personal(PROFESSION profession) {
        super();
        this.profession = profession;
    }
    
    public static enum PROFESSION {
        WORKER, ENGINEER, MEDIC, GUARD, BIOLOGIST
    }

}
