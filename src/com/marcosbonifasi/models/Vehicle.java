package com.marcosbonifasi.models;

public class Vehicle {

    private float gasoline;
    private String name;

    private static String[][] vehicles = {
            {"Motocicleta 1", "6.0f"},
            {"Motocicleta 2", "6.0f"},
            {"Motocicleta 3", "6.0f"},
            {"Vehiculo estandar 1", "10.0f"},
            {"Vehiculo estandar 2", "10.0f"},
            {"Vehiculo estandar 3", "10.0f"},
            {"Vehiculo premium 1", "12.0f"},
            {"Vehiculo premium 2", "12.0f"},
            {"Vehiculo premium 3", "12.0f"},
    };

    public Vehicle(){}
    public Vehicle(String name, float gasoline){
        this.name = name;
        this.gasoline = gasoline;
    }

    public static String[][] getVehicles(){
        return vehicles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGasoline() {
        return this.gasoline;
    }

    public void setGasoline(float gasoline) {
        this.gasoline = gasoline;
    }

}
