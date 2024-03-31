package com.marcosbonifasi.models;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.controllers.VehiclesController;

import java.io.Serializable;

public class Trip implements Serializable {

    private int id;
    private String startingPoint, finalPoint;
    private String status;
    private String vehicleName;

    private float distanceTraveled, gasolineConsumed;
    private String initialDatetime, finalDatetime;
    private Vehicle vehicle;


    public Trip(){}
    public Trip(int id, String vehicleName, String startingPoint, String finalPoint, String status, float distanceTraveled, float gasolineConsumed, String initialDatetime, String finalDatetime){
        this.id = id;
        this.vehicleName = vehicleName;
        this.startingPoint = startingPoint;
        this.finalPoint = finalPoint;
        this.status = status;
        this.distanceTraveled = distanceTraveled;
        this.gasolineConsumed = gasolineConsumed;
        this.initialDatetime = initialDatetime;
        this.finalDatetime = finalDatetime;
        this.vehicle = new VehiclesController(vehicleName).getVehicle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDistance(){
        float distance = 0.0f;

        for (int i = 0; i < Main.getRoutes().size(); i++) {
            if (Main.getRoutes().get(i).getStartingPoint().equals(this.getStartingPoint()) || Main.getRoutes().get(i).getFinalPoint().equals(this.getStartingPoint())){
                distance = Main.getRoutes().get(i).getDistance();
                break;
            }
        }

        return distance;
    }

//    public History getHistory() {
//        return history;
//    }

//    public void setHistory(History history) {
//        this.history = history;
//    }

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

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(float distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
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
