package com.marcosbonifasi.models;

import java.lang.invoke.VarHandle;

public class Trip {

    private int id;
    private Vehicle vehicle;
    private String startingPoint, finalPoint;

    public Trip(){}
    public Trip(int id, Vehicle vehicle, String startingPoint, String finalPoint){
        this.id = id;
        this.vehicle = vehicle;
        this.startingPoint = startingPoint;
        this.finalPoint = finalPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(String finalPoint) {
        this.finalPoint = finalPoint;
    }
}
