package com.marcosbonifasi.models;

public class History extends Trip{

    private float distanceTraveled, gasolineConsumed;
    private String initialDatetime, finalDatetime;

    public History(){}

    public History(Trip trip, String initialDatetime, String finalDatetime, float distanceTraveled, float gasolineConsumed){
        super(trip.getId(), trip.getVehicle(), trip.getRoute(), trip.getStatus());
        this.initialDatetime = initialDatetime;
        this.finalDatetime = finalDatetime;
        this.distanceTraveled = distanceTraveled;
        this.gasolineConsumed = gasolineConsumed;
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(float distanceTraveled) {

        this.distanceTraveled += distanceTraveled;
    }

    public float getGasolineConsumed() {
        return gasolineConsumed;
    }

    public void setGasolineConsumed(float gasolineConsumed) {
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
