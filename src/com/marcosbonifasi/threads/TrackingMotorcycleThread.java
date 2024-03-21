package com.marcosbonifasi.threads;

import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.views.trips.TripsTrackingView;

public class TrackingMotorcycleThread extends Thread {
    String tripType;
    TripsTrackingView tripsTrackingView;
    Trip trip;
    private volatile boolean runningClock = true;

    public TrackingMotorcycleThread(TripsTrackingView tripsTrackingView, Trip trip, String tripType) {
        this.tripsTrackingView = tripsTrackingView;
        this.tripType = tripType;
        this.trip = trip;
    }

    @Override
    public void run() {
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


}
