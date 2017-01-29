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
public final class InfinityFlight {
    
    /**
     * Flight duration between stations;
     * FD[0] - low flight duration
     * FD[1] - normal
     * FD[2] - high
     * 
     * FD[x][0] - minimum
     * FD[x][1] - maximum.
     */
    public static final short[][] FLIGHT_DURATION = {{60,70},{80,90},{100,120}};
    
    /**
     * Chances of flight duration categories:
     * CFD[0] - low flight duration
     * CFD[1] - normal
     * CFD[2] - high.
     */
    public static final byte[] CHANCES_FLIGHT_DURATION = {19,71,10};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Station().toString());
        }
    }
    
}
