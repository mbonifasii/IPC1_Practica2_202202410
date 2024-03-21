package com.marcosbonifasi.models;

import com.marcosbonifasi.Main;

public class Trip {

    private int id;
    private Vehicle vehicle;
    private String startingPoint, finalPoint, status;

    public Trip(){}
    public Trip(int id, Vehicle vehicle, String startingPoint, String finalPoint, String status){
        this.id = id;
        this.vehicle = vehicle;
        this.startingPoint = startingPoint;
        this.finalPoint = finalPoint;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDistance(){
        int distance = 0;

        for (int i = 0; i < Main.getRoutes().size(); i++) {
            if (Main.getRoutes().get(i).getStartingPoint().equals(this.getStartingPoint())){
                distance = Main.getRoutes().get(i).getDistance();
                break;
            }
        }

        return distance;
    }
}
