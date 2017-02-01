/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmig.infinityflight.logic;

/**
 *
 * @author Dmig
 */
public final class Updater extends Thread {
    
    public Ship ship;
    private boolean ended = false;

    public Updater(Ship ship) {
        this.ship = ship;
        this.setName("Updater");
    }

    @Override
    public void run() {
        while(!ended) {
            try {
                Thread.sleep(1000);
                ship.updateHour();
            } catch (InterruptedException ex) {
                System.out.println(ex);
                System.exit(1);
            }
        }
    }
    
    public void end() {
        ended = true;
    }
    
}
