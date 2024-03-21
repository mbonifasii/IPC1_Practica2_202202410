package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

public class VPremiumThread extends Thread {

    int x, y;
    private volatile boolean running = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingMotorcycleThread trackingMotorcycleThread;

    public VPremiumThread(){}
    public VPremiumThread(TripsTrackingView tripsTrackingView, TrackingMotorcycleThread trackingMotorcycleThread){
        this.tripsTrackingView = tripsTrackingView;
        this.x = this.tripsTrackingView.labelVehicle1.getX();
        this.y = this.tripsTrackingView.labelVehicle1.getY();
        this.trackingMotorcycleThread = trackingMotorcycleThread;
    }

    public void run(){
        try {
            while(running){
                sleep(250); // ms
                this.x -=8;
                this.tripsTrackingView.labelVehicle1.setLocation(x, y);

                if (this.tripsTrackingView.labelVehicle1.getX() == 0) {
                    this.stopThread();
                    this.trackingMotorcycleThread.stopClockThread();
                }
                this.tripsTrackingView.repaint();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void stopThread() {
        running = false;
    }

}
