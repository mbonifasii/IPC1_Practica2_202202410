package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;

public class VStandardThread extends Thread {

    int xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle2 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVStandardThread trackingVStandardThread;

    public VStandardThread(){}
    public VStandardThread(TripsTrackingView tripsTrackingView, TrackingVStandardThread trackingVStandardThread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle2.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle2.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo2.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo2.getY();
        this.trackingVStandardThread = trackingVStandardThread;
    }

    public void run(){
        try{
            while(runningVehicle2){
                sleep((int) (750/this.trackingVStandardThread.trip.getDistance())*10); // this should be according to the distance

                if(this.trackingVStandardThread.tripType.equals("go")){
                    this.xVehicle-=8;
                    this.xInfo -=8;
                } else if(this.trackingVStandardThread.tripType.equals("return")){
                    this.xVehicle+=8;
                    this.xInfo+=8;
                }


                int initKm = (int) Math.floor(this.trackingVStandardThread.trip.getHistory().getDistanceTraveled());
                int finalKm = (int) Math.floor(this.trackingVStandardThread.trip.getHistory().getDistanceTraveled() + (this.trackingVStandardThread.trip.getDistance()/750.0f)*10f);

                if((finalKm - initKm) == 1.0) {
                    this.trackingVStandardThread.trip.getVehicle().setGasoline(this.trackingVStandardThread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVStandardThread.trip.getHistory().setGasolineConsumed(this.trackingVStandardThread.trip.getHistory().getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle2.setLocation(xVehicle, yVehicle);
                this.tripsTrackingView.labelCurrentInfo2.setLocation(xInfo, yInfo);
                this.trackingVStandardThread.trip.getHistory().setDistanceTraveled(((this.trackingVStandardThread.trip.getDistance()/750.0f)*10.0f));

                this.tripsTrackingView.labelCurrentInfo2.setText(
                        "<html>" +
                                "Recorrido " + this.trackingVStandardThread.trip.getHistory().getDistanceTraveled() +
                                "<br>" +
                                "Gasolina " + this.trackingVStandardThread.trip.getVehicle().getGasoline() +
                                "</html>"
                );

                if(this.trackingVStandardThread.trip.getVehicle().getGasoline() <= 0.0f){
                    this.stopThread();
                    this.trackingVStandardThread.stopClockThread();

                    // Show refill button
                    this.tripsTrackingView.btnRefillTank2 = new JButton("Recargar");
                    this.tripsTrackingView.btnRefillTank2.setBounds(0, 0, 100, 30);
                    if(this.trackingVStandardThread.tripType.equals("return")){
                        this.tripsTrackingView.btnRefillTank2.setLocation(this.tripsTrackingView.labelVehicle2.getX() - 100, this.tripsTrackingView.labelVehicle2.getY());
                    }else{
                        this.tripsTrackingView.btnRefillTank2.setLocation(this.tripsTrackingView.labelVehicle2.getX() + 100, this.tripsTrackingView.labelVehicle2.getY());
                    }

                    this.tripsTrackingView.btnRefillTank2.setBackground(new Color(230, 57, 70));
                    this.tripsTrackingView.btnRefillTank2.setForeground(Color.white);
                    this.tripsTrackingView.btnRefillTank2.setFont(new Font(this.tripsTrackingView.btnRefillTank2.getFont().getFontName(), Font.BOLD, 10));
                    this.tripsTrackingView.btnRefillTank2.setOpaque(true);
                    this.tripsTrackingView.btnRefillTank2.setBorderPainted(false);
                    this.tripsTrackingView.btnRefillTank2.setVisible(true);
                    this.tripsTrackingView.btnRefillTank2.setEnabled(true);
                    this.tripsTrackingView.btnRefillTank2.addMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.highway2.add(this.tripsTrackingView.btnRefillTank2);
                }


                if (this.tripsTrackingView.labelVehicle2.getX() == 600)
                    this.trackingVStandardThread.trip.getHistory().setStatus("finalized");

                if (this.tripsTrackingView.labelVehicle2.getX() == 0 || this.tripsTrackingView.labelVehicle2.getX() == 600) {
                    this.stopThread();
                    this.trackingVStandardThread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle2.getX() == 0){
                    this.tripsTrackingView.btnInitDriver2.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver2.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnReturn2.setEnabled(true);
                    this.tripsTrackingView.btnReturn2.addMouseListener(this.tripsTrackingView);
                }

                this.tripsTrackingView.highway2.repaint();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle2 = false;
    }

}
