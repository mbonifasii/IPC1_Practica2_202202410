package com.marcosbonifasi.controllers;


import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

public class TripsController {

    private Trip trip;

    public TripsController(){}

    public void create(int id, String vehicleName, String startingPoint, String finalPoint, String status, float distanceTraveled, float gasolineConsumed, String initialDatetime, String finalDatetime, float xVehicle, float xInfo){
        this.trip = new Trip(
            id,
            vehicleName,
            startingPoint,
            finalPoint,
            status,
            distanceTraveled,
            gasolineConsumed,
            initialDatetime,
            finalDatetime,
            xVehicle,
            xInfo
        );

        Main.addTrip(this.trip);
    }

    public Trip getTrip(){
        return this.trip;
    }

}
