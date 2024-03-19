package com.marcosbonifasi.models;

public class History extends Trip{

    private int distance, gasolineConsumed;
    private String initialDatetime, finalDatetime;

    public History(){}

    public History(Trip trip, String initialDatetime, String finalDatetime, int distance, int gasolineConsumed){
        super(trip.getId(), trip.getVehicle(), trip.getStartingPoint(), trip.getFinalPoint(), trip.getStatus());
        this.initialDatetime = initialDatetime;
        this.finalDatetime = finalDatetime;
        this.distance = distance;
        this.gasolineConsumed = gasolineConsumed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
