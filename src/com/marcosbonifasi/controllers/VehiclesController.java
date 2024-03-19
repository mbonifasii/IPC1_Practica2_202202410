package com.marcosbonifasi.controllers;


import com.marcosbonifasi.models.Vehicle;

public class VehiclesController {


    public VehiclesController(){}




    public static String[] getVehicles(String removeVehicle){
        return Vehicle.getVehicles();
    }

}
