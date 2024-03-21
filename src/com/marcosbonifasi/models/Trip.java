package com.marcosbonifasi.models;

import com.marcosbonifasi.Main;

public class Trip {

    private int id;
    private String status;
    private Vehicle vehicle;
    private Route route;
    private History history;

    public Trip(){}
    public Trip(int id, Vehicle vehicle, Route route, String status){
        this.id = id;
        this.vehicle = vehicle;
        this.route = route;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDistance(){
        int distance = 0;

        for (int i = 0; i < Main.getRoutes().size(); i++) {
            if (Main.getRoutes().get(i).getStartingPoint().equals(this.route.getStartingPoint())){
                distance = Main.getRoutes().get(i).getDistance();
                break;
            }
        }

        return distance;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
