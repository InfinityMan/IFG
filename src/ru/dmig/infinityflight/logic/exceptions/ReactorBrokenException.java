/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic.exceptions;

/**
 *
 * @author Dmig
 */
public class ReactorBrokenException extends Exception {

    /**
     * Creates a new instance of <code>ReactorBrokenException</code> without
     * detail message.
     */
    public ReactorBrokenException() {
    }

    /**
     * Constructs an instance of <code>ReactorBrokenException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ReactorBrokenException(String msg) {
        super(msg);
    }
}
