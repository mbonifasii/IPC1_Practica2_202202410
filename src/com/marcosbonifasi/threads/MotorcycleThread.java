package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

public class MotorcycleThread extends Thread {

    int x, y;
    private volatile boolean runningVehicle1 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingMotorcycleThread trackingMotorcycleThread;

    public MotorcycleThread(){}
    public MotorcycleThread(TripsTrackingView tripsTrackingView, TrackingMotorcycleThread trackingMotorcycleThread){
        this.tripsTrackingView = tripsTrackingView;
        this.x = this.tripsTrackingView.labelVehicle1.getX();
        this.y = this.tripsTrackingView.labelVehicle1.getY();
        this.trackingMotorcycleThread = trackingMotorcycleThread;
    }

    public void run(){
        try {
            while(runningVehicle1){
                sleep(100); // this should be according to the distance

                if(this.trackingMotorcycleThread.tripType.equals("go"))
                    this.x -=8;
                else if(this.trackingMotorcycleThread.tripType.equals("return"))
                    this.x +=8;

                this.tripsTrackingView.labelVehicle1.setLocation(x, y);

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
