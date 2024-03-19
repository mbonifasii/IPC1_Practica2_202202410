package com.marcosbonifasi.controllers;


import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

public class TripsController {

    private Trip trip;

    public TripsController(){}

    public void create(int id, Vehicle vehicle, String startingPoint, String finalPoint, String status){
        this.trip = new Trip(
            id,
            vehicle,
            startingPoint,
            finalPoint,
            status
        );

        Main.addTrip(this.trip);
    }

    public Trip getTrip(){
        return this.trip;
    }

}
