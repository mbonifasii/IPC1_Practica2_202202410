package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;

public class MotorcycleThread extends Thread {

    int xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle1 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingMotorcycleThread trackingMotorcycleThread;

    public MotorcycleThread(){}
    public MotorcycleThread(TripsTrackingView tripsTrackingView, TrackingMotorcycleThread trackingMotorcycleThread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle1.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle1.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo1.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo1.getY();
        this.trackingMotorcycleThread = trackingMotorcycleThread;
    }

    public void run(){
        try{
            while(runningVehicle1){
                sleep((750/this.trackingMotorcycleThread.trip.getDistance())*10); // this should be according to the distance

                if(this.trackingMotorcycleThread.tripType.equals("go")){
                    this.xVehicle-=8;
                    this.xInfo -=8;
                } else if(this.trackingMotorcycleThread.tripType.equals("return")){
                    this.xVehicle+=8;
                    this.xInfo+=8;
                }

                this.tripsTrackingView.labelVehicle1.setLocation(xVehicle, yVehicle);
                this.tripsTrackingView.labelCurrentInfo1.setLocation(xInfo, yInfo);

                this.trackingMotorcycleThread.trip.getHistory().setDistanceTraveled(((this.trackingMotorcycleThread.trip.getDistance()/750.0f)*10f));

                this.tripsTrackingView.labelCurrentInfo1.setText(
                        "<html>" +
                        "Recorrido " + this.trackingMotorcycleThread.trip.getHistory().getDistanceTraveled() +
                        "<br>" +
                        "Gasolina " + this.trackingMotorcycleThread.trip.getVehicle().getGasoline() +
                        "</html>"
                );

                System.out.println("Test: " + (this.trackingMotorcycleThread.trip.getDistance()/750.0f)*10f);
                System.out.println("Distance traveled: " + this.trackingMotorcycleThread.trip.getHistory().getDistanceTraveled());
                System.out.println();

                if (this.tripsTrackingView.labelVehicle1.getX() == 0 || this.tripsTrackingView.labelVehicle1.getX() == 600) {
                    this.stopThread();
                    this.trackingMotorcycleThread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle1.getX() == 0){
                    this.tripsTrackingView.btnInitDriver1.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver1.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnReturn1.setEnabled(true);
                    this.tripsTrackingView.btnReturn1.addMouseListener(this.tripsTrackingView);
                }

                this.tripsTrackingView.highway1.repaint();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle1 = false;
    }

}
