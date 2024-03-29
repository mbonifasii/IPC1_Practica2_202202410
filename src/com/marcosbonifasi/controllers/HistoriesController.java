package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.History;
import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

import java.util.ArrayList;

public class HistoriesController {

    private History history;

    public HistoriesController(){}

    public void create(Trip trip, String initialDatetime, String finalDatetime, float distanceTraveled, int gasolineConsumed){
        this.history = new History(
                trip,
                initialDatetime,
                finalDatetime,
                distanceTraveled,
                gasolineConsumed
        );

        Main.addHistory(this.history);
    }

    public History getHistory(){
        return this.history;
    }

    public static Object[][] getParsedData() {
        int rows = Main.getHistories().size();
        ArrayList<History> histories = Main.getHistories();
        Object[][] historiesArr = new Object[rows][6];
        for (int i = 0; i < rows; i++) {
            historiesArr[i][0] = histories.get(i).getId();
            historiesArr[i][1] = histories.get(i).getInitialDatetime();
            historiesArr[i][2] = histories.get(i).getFinalDatetime();
            historiesArr[i][3] = histories.get(i).getDistanceTraveled();
            historiesArr[i][4] = histories.get(i).getVehicle().getName();
            historiesArr[i][5] = histories.get(i).getGasolineConsumed();
        }

        return historiesArr;
    }



}
