package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;

import java.util.ArrayList;


public class RoutesController {

    private Route route;

    public RoutesController(){}

    public RoutesController(int id){
        load(id);
    }

    public RoutesController(String startingPoint, String finalPoint, int distance){
        this.route = new Route();
        this.route.setId(Main.generateId("route"));
        this.route.setStartingPoint(startingPoint);
        this.route.setFinalPoint(finalPoint);
        this.route.setDistance(distance);
    }

    public boolean found(){
        return !Integer.toString(this.route.getId()).isEmpty();
    }

    private void load(int id){
        int[] existence = validateExistence(id);

        if(existence[0] == 1)
            this.route = Main.getRoutes().get(existence[1]);
    }

    public int[] validateExistence(int id){
        int[] existence = new int[2];

        // Find the doctor according to the code
        for (int i = 0; i < Main.getRoutes().size(); i++) {
            if(Main.getRoutes().get(i).getId() == id){
                existence[0] = 1;
                existence[1] = i;
                break;
            }
        }

        return existence;
    }

    public void create(){
        Main.addRoute(this.route);
    }

    public static Object[][] getParsedData() {
        int rows = Main.getRoutes().size();
        ArrayList<Route> routes = Main.getRoutes();
        Object[][] routesArr = new Object[rows][4];
        for (int i = 0; i < rows; i++) {
            routesArr[i][0] = routes.get(i).getId();
            routesArr[i][1] = routes.get(i).getStartingPoint();
            routesArr[i][2] = routes.get(i).getFinalPoint();
            routesArr[i][3] = routes.get(i).getDistance();
        }

        return routesArr;
    }

    public void update(int distance){
        this.route.setDistance(distance);
    }

}
