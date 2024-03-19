package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.History;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;

public class HistoriesController {

    private History history;

    public HistoriesController(){}

    public void create(Trip trip, String initialDatetime, String finalDatetime, int distance, int gasolineConsumed){
        this.history = new History(
                trip,
                initialDatetime,
                finalDatetime,
                distance,
                gasolineConsumed
        );

        Main.addHistory(this.history);
    }

}