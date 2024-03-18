package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;

import java.util.ArrayList;


public class RoutesController {

    public RoutesController(){}

    public RoutesController(int id){
        load(id);
    }

    private void load(int id){

    }

    public static Object[][] getParsedData() {
        int rows = Main.getRoutes().size();
        ArrayList<Route> routes = new ArrayList<Route>();
        Object[][] routesArr = new Object[rows][5];
        for (int i = 0; i < rows; i++) {
            routesArr[i][0] = routes.get(i).getId();
            routesArr[i][1] = routes.get(i).getStartingPoint();
            routesArr[i][2] = routes.get(i).getFinalPoint();
            routesArr[i][3] = routes.get(i).getDistance();
        }

        return routesArr;
    }



}
