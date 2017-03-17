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
package ru.dmig.infinityflight.logic.exceptions;

/**
 *
 * @author Dmig
 */
public class NotEnoughtMoneyException extends RuntimeException {
    
    public double amount;

    /**
     * Creates a new instance of <code>NotEnoughtMoneyException</code> without
     * detail message.
     * @param amount How much money need (extra)
     */
    public NotEnoughtMoneyException(double amount) {
        this.amount = amount;
    }

    /**
     * Constructs an instance of <code>NotEnoughtMoneyException</code> with the
     * specified detail message.
     *
     * @param amount How much money need (extra)
     * @param msg the detail message.
     */
    public NotEnoughtMoneyException(double amount,String msg) {
        super(msg);
        this.amount = amount;
    }
}
