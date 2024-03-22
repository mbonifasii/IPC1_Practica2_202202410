package com.marcosbonifasi.controllers;


import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

public class TripsController {

    private Trip trip;
    private String startingPoint, finalPoint;

    public TripsController(){}

    public void create(int id, Vehicle vehicle, Route route, String status){
        this.trip = new Trip(
            id,
            vehicle,
            route,
            status
        );

        Main.addTrip(this.trip);
    }

    public Trip getTrip(){
        return this.trip;
    }

}
