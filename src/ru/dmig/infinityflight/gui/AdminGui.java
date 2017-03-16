package ru.dmig.infinityflight.gui;

import static java.lang.Math.round;
import static java.lang.System.err;
import static java.lang.System.exit;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import ru.dmig.infinityflight.logic.Engine;
import ru.dmig.infinityflight.logic.InfinityFlight;
import static ru.dmig.infinityflight.logic.InfinityFlight.ship;
import ru.dmig.infinityflight.logic.Reactor;
import ru.dmig.infinityflight.logic.Updater;
import static ru.dmig.infinityflight.logic.Updater.changeTick;
import static ru.dmig.infinityflight.logic.Updater.getTick;
import ru.dmig.infinityflight.logic.exceptions.StorageOverfilledException;
import static ru.epiclib.gui.Util.setStyle;

/**
 *
 * @author Dmig
 */
public class AdminGui extends javax.swing.JFrame {

    private static class GuiStarter extends Thread {

        GuiStarter() {
            this.setName("AdminGui");
        }

        @Override
        public void run() {
            try {
                setStyle();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                showMessageDialog(null, ex, "Error", ERROR_MESSAGE);
                exit(-11);
            }

            AdminGui gui = new AdminGui();
            gui.setVisible(true);
        }
    }

    public static void start() {
        GuiStarter starter = new GuiStarter();
        starter.start();
    }

    /**
     * Creates new form AdminGui
     */
    public AdminGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        halfSpeed = new javax.swing.JButton();
        doubleSpeed = new javax.swing.JButton();
        tickSize = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        addFuel = new javax.swing.JButton();
        addFood = new javax.swing.JButton();
        startStorage = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        repair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IF:Admin");
        setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N

        halfSpeed.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        halfSpeed.setText("tick / 2");
        halfSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                halfSpeedActionPerformed(evt);
            }
        });

        doubleSpeed.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        doubleSpeed.setText("tick * 2");
        doubleSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doubleSpeedActionPerformed(evt);
            }
        });

        tickSize.setEditable(false);
        tickSize.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        tickSize.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tickSize.setText("1000 ms");

        addFuel.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        addFuel.setText("+100 Fuel");
        addFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFuelActionPerformed(evt);
            }
        });

        addFood.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        addFood.setText("+100 Food");
        addFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFoodActionPerformed(evt);
            }
        });

        startStorage.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        startStorage.setText("Start storage");
        startStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStorageActionPerformed(evt);
            }
        });

        repair.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        repair.setText("Repair all");
        repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(addFuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addFood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startStorage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tickSize)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(halfSpeed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(doubleSpeed)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(repair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tickSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(halfSpeed)
                    .addComponent(doubleSpeed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startStorage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addFuel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addFood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doubleSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doubleSpeedActionPerformed
        changeTick(false);
        tickSize.setText(round(getTick()) + " ms");
    }//GEN-LAST:event_doubleSpeedActionPerformed

    private void halfSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_halfSpeedActionPerformed
        changeTick(true);
        tickSize.setText(round(getTick()) + " ms");
    }//GEN-LAST:event_halfSpeedActionPerformed

    private void startStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStorageActionPerformed
        ship.storage.toStartAmounts();
    }//GEN-LAST:event_startStorageActionPerformed

    private void addFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFuelActionPerformed
        try {
            ship.storage.increaseFuel(100);
        } catch (StorageOverfilledException ex) {
            try {
                ship.storage.increaseFuel((float) (100 - ex.amount));
            } catch (StorageOverfilledException n) {err.println(n);}
        }
    }//GEN-LAST:event_addFuelActionPerformed

    private void addFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFoodActionPerformed
        try {
            ship.storage.increaseFood(100);
        } catch (StorageOverfilledException ex) {
            try {
                ship.storage.increaseFood((int) (100 - ex.amount));
            } catch (StorageOverfilledException n) {err.println(n);}
        }
    }//GEN-LAST:event_addFoodActionPerformed

    private void repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repairActionPerformed
        for (int i = 0; i < ship.engines.size(); i++) {
            Engine get = ship.engines.get(i);
            get.fixThis();
        }
        
        for (int i = 0; i < ship.reactors.size(); i++) {
            Reactor get = ship.reactors.get(i);
            get.fixThis();
        }
    }//GEN-LAST:event_repairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFood;
    private javax.swing.JButton addFuel;
    private javax.swing.JButton doubleSpeed;
    private javax.swing.JButton halfSpeed;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton repair;
    private javax.swing.JButton startStorage;
    private javax.swing.JTextField tickSize;
    // End of variables declaration//GEN-END:variables
}
