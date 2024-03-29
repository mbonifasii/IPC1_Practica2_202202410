package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;

public class VStandardThread extends Thread {

    float xVehicle, yVehicle, xInfo, yInfo;
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
                sleep((int) (600/(15/this.trackingVStandardThread.trip.getDistance()))); // this should be according to the distance

                if(this.trackingVStandardThread.tripType.equals("go")){
                    this.xVehicle-=600.0f/this.trackingVStandardThread.trip.getDistance();
                    this.xInfo -=600.0f/this.trackingVStandardThread.trip.getDistance();
                } else if(this.trackingVStandardThread.tripType.equals("return")){
                    this.xVehicle+=600.0f/this.trackingVStandardThread.trip.getDistance();
                    this.xInfo+=600.0f/this.trackingVStandardThread.trip.getDistance();
                }


                int initKm = (int) Math.floor(this.trackingVStandardThread.trip.getHistory().getDistanceTraveled());
                int finalKm = (int) Math.floor(this.trackingVStandardThread.trip.getHistory().getDistanceTraveled() + ((600.0f/this.trackingVStandardThread.trip.getDistance()) * this.trackingVStandardThread.trip.getDistance())/600.0f);

                if((finalKm - initKm) == 1.0f) {
                    this.trackingVStandardThread.trip.getVehicle().setGasoline(this.trackingVStandardThread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVStandardThread.trip.getHistory().setGasolineConsumed(this.trackingVStandardThread.trip.getHistory().getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle2.setLocation((int) Math.floor(xVehicle), (int )Math.floor(yVehicle));
                this.tripsTrackingView.labelCurrentInfo2.setLocation((int) Math.floor(xInfo), (int) Math.floor(yInfo));
                this.trackingVStandardThread.trip.getHistory().setDistanceTraveled((600.0f/this.trackingVStandardThread.trip.getDistance()) * this.trackingVStandardThread.trip.getDistance()/600.0f);

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


                if (this.tripsTrackingView.labelVehicle2.getX() >= 600.0f)
                    this.trackingVStandardThread.trip.getHistory().setStatus("finalized");

                if (this.tripsTrackingView.labelVehicle2.getX() <= 0.0f || this.tripsTrackingView.labelVehicle2.getX() >= 600.0f) {
                    this.stopThread();
                    this.trackingVStandardThread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle2.getX() <= 0.0f){
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
