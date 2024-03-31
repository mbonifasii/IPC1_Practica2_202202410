package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

import java.util.ArrayList;

public class HistoriesController {

//    private History history;

    public HistoriesController(){}


    public static Object[][] getParsedData() {
        int rows = Main.getTrips().size();
        ArrayList<Trip> histories = Main.getTrips();
        Object[][] historiesArr = new Object[rows][6];
        for (int i = 0; i < rows; i++) {
            if(histories.get(i).getStatus().equals("finalized")){
                historiesArr[i][0] = histories.get(i).getId();
                historiesArr[i][1] = histories.get(i).getInitialDatetime();
                historiesArr[i][2] = histories.get(i).getFinalDatetime();
                historiesArr[i][3] = histories.get(i).getDistanceTraveled();
                historiesArr[i][4] = histories.get(i).getVehicle().getName();
                historiesArr[i][5] = histories.get(i).getGasolineConsumed();
            }
        }

        return historiesArr;
    }



}
