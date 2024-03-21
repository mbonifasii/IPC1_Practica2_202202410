package com.marcosbonifasi.controllers;


import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Vehicle;

public class VehiclesController {

    Vehicle vehicle;

    public VehiclesController(String vehicleName){
        load(vehicleName);
    }

    private void load(String vehicleName){
        for (int i = 0; i < Main.getVehiclesAvailable().size(); i++) {
            if(Main.getVehiclesAvailable().get(i).getName().equals(vehicleName)){
                this.vehicle = Main.getVehiclesAvailable().get(i);
                break;
            }
        }
    }


    public static String[] getVehiclesAvailable(){
        String[] vehiclesArr = new String[Main.getVehiclesAvailable().size()];

        for (int i = 0; i < vehiclesArr.length; i++) {
            vehiclesArr[i] = Main.getVehiclesAvailable().get(i).getName();
        }

        return vehiclesArr;
    }

    public static void makeVehicleBusy(String vehicleName){
        Main.makeVehicleBusy(vehicleName);
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

}
