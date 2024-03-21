package com.marcosbonifasi.models;

public class History extends Trip{

    private int distanceTraveled, gasolineConsumed;
    private String initialDatetime, finalDatetime;

    public History(){}

    public History(Trip trip, String initialDatetime, String finalDatetime, int distanceTraveled, int gasolineConsumed){
        super(trip.getId(), trip.getVehicle(), trip.getRoute(), trip.getStatus());
        this.initialDatetime = initialDatetime;
        this.finalDatetime = finalDatetime;
        this.distanceTraveled = distanceTraveled;
        this.gasolineConsumed = gasolineConsumed;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(int distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getGasolineConsumed() {
        return gasolineConsumed;
    }

    public void setGasolineConsumed(int gasolineConsumed) {
        this.gasolineConsumed = gasolineConsumed;
    }

    public String getInitialDatetime() {
        return initialDatetime;
    }

    public void setInitialDatetime(String initialDatetime) {
        this.initialDatetime = initialDatetime;
    }

    public String getFinalDatetime() {
        return finalDatetime;
    }

    public void setFinalDatetime(String finalDatetime) {
        this.finalDatetime = finalDatetime;
    }
}
