package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Clock extends Thread{

    TripsTrackingView tripsTrackingView;
    private volatile boolean runningClock = true;
//    private int segundos = 0;
//    private int minutos = 0;

    public Clock(TripsTrackingView tripsTrackingView) {
        this.tripsTrackingView = tripsTrackingView;
//        this.tripsTrackingView = tripsTrackingView;
//        this.tripsTrackingView.lbl2 = new JLabel("Tiempo: 00:00");
//        this.tripsTrackingView.lbl2.setBounds(10, 10, 100, 20);
//        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
//        this.tripsTrackingView.lbl2.setBorder(border);
//        this.tripsTrackingView.lbl2.setVisible(true);
//        this.tripsTrackingView.add(this.tripsTrackingView.lbl2);
//        this.tripsTrackingView.repaint();
    }

    @Override
    public void run() {
//        while (running) {
//            try {
////                sleep(1000); // miliseconds
////                segundos++;
////
////                if (segundos == 60) {
////                    segundos = 0;
////                    minutos++;
////                }
////                actualizarReloj();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
        MotorcycleThread motorcycleThread = new MotorcycleThread(this.tripsTrackingView, this);
        motorcycleThread.start();
        try {
            while (runningClock) {
                this.tripsTrackingView.btnInitDriver1.setEnabled(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stopClockThread() {
        runningClock = false;
    }

    private void actualizarReloj() {
//        String tiempo = String.format("%02d:%02d", minutos, segundos);
//        this.tripsTrackingView.lbl2.setText("Tiempo: " + tiempo);
    }

}
