package com.marcosbonifasi.threads;

import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.views.trips.TripsTrackingView;

public class TrackingVStandardThread extends Thread {
    String tripType;
    TripsTrackingView tripsTrackingView;
    Trip trip;
    private volatile boolean runningClock = true;

    public TrackingVStandardThread(TripsTrackingView tripsTrackingView, Trip trip, String tripType) {
        this.tripsTrackingView = tripsTrackingView;
        this.tripType = tripType;
        this.trip = trip;
    }

    @Override
    public void run() {
        VStandardThread vStandardThread = new VStandardThread(this.tripsTrackingView, this);
        vStandardThread.start();
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
