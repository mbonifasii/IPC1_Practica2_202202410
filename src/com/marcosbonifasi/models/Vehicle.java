package com.marcosbonifasi.models;

public class Vehicle {

    private String name;
    private static String[] vehicles = {
            "Motocicleta 1",
            "Motocicleta 2",
            "Motocicleta 3",
            "Vehículo estandar 1",
            "Vehículo estandar 2",
            "Vehículo estandar 3",
            "Vehículo premium 1",
            "Vehículo premium 2",
            "Vehículo premium 3",
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
