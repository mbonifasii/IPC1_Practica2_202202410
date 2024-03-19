package com.marcosbonifasi.models;

public class Vehicle {

    private String name;
    private static String[] vehicles = {
            "Motocicleta 1",
            "Motocicleta 2",
            "Motocicleta 3",
            "Vehiculo estandar 1",
            "Vehiculo estandar 2",
            "Vehiculo estandar 3",
            "Vehiculo premium 1",
            "Vehiculo premium 2",
            "Vehiculo premium 3",
    };

    public Vehicle(){}
    public Vehicle(String name){
        this.name = name;
    }

    public static String[] getVehicles(){
        return vehicles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
