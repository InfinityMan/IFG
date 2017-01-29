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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            String sector = Code.getRandomSector();
            for (int j = 0; j < 10; j++) {
                System.out.println(new Person(sector).toString());
            }
            System.out.println("--");
        }
    }
    
}
