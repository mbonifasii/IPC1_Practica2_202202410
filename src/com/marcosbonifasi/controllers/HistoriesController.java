package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.History;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

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

}
