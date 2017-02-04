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
        byte hour = 0; // 0;4;8;12;16;20
        while(!ended) {
            try {
                Thread.sleep(1000);
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
    
}
