package com.marcosbonifasi.threads;

import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.views.trips.TripsTrackingView;

public class TrackingVPremiumThread extends Thread {
    String tripType;
    TripsTrackingView tripsTrackingView;
    Trip trip;
    private volatile boolean runningClock = true;

    public TrackingVPremiumThread(TripsTrackingView tripsTrackingView, Trip trip, String tripType) {
        this.tripsTrackingView = tripsTrackingView;
        this.tripType = tripType;
        this.trip = trip;
    }

    @Override
    public void run() {
        Vehicle3Thread cPremiumThread = new Vehicle3Thread(this.tripsTrackingView, this);
        cPremiumThread.start();
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
