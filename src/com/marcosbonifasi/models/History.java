package com.marcosbonifasi.models;

public class History {

    private int id, distance, gasolineConsumed;
    private String initialDatetime, finalDatetime;
    private Vehicle vehicle;

    public History(){}

    public History(int id, String initialDatetime, String finalDatetime, Vehicle vehicle, int distance, int gasolineConsumed){
        this.id = id;
        this.initialDatetime = initialDatetime;
        this.finalDatetime = finalDatetime;
        this.vehicle = vehicle;
        this.distance = distance;
        this.gasolineConsumed = gasolineConsumed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
