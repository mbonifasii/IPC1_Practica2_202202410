package com.marcosbonifasi.models;

public class Vehicle {

    private int gasoline;
    private String name;

    private static String[][] vehicles = {
            {"Motocicleta 1", "6"},
            {"Motocicleta 2", "6"},
            {"Motocicleta 3", "6"},
            {"Vehiculo estandar 1", "10"},
            {"Vehiculo estandar 2", "10"},
            {"Vehiculo estandar 3", "10"},
            {"Vehiculo premium 1", "12"},
            {"Vehiculo premium 2", "12"},
            {"Vehiculo premium 3", "12"},
    };

    public Vehicle(){}
    public Vehicle(String name, int gasoline){
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

    public int getGasoline() {
        return this.gasoline;
    }

    public void setGasoline(int gasoline) {
        this.gasoline = gasoline;
    }

}
