package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

public class MotorcycleThread extends Thread {

    int x, y;
    private volatile boolean runningVehicle1 = true;
    private TripsTrackingView tripsTrackingView;
    private Clock clock;

    public MotorcycleThread(){}
    public MotorcycleThread(TripsTrackingView tripsTrackingView, Clock clock){
        this.tripsTrackingView = tripsTrackingView;
        this.x = this.tripsTrackingView.labelVehicle1.getX();
        this.y = this.tripsTrackingView.labelVehicle1.getY();
        this.clock = clock;
    }

    public void run(){
        try {
            while(runningVehicle1){
                sleep(250); // ms
                this.x -=8;
                this.tripsTrackingView.labelVehicle1.setLocation(x, y);

                if (this.tripsTrackingView.labelVehicle1.getX() == 0) {
                    System.out.println("dentro");
                    System.out.println("Llego al inicio");
                    this.tripsTrackingView.stopVehicle1Thread();
                    this.stopThread();
                    this.clock.stopClockThread();

                }
                this.tripsTrackingView.repaint();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle1 = false;
    }

}
