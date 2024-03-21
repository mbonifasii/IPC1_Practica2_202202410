package com.marcosbonifasi.controllers;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.models.Route;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class RoutesController {

    private Route route;

    public RoutesController(){}
    public RoutesController(int id){
        loadById(id);
    }
    public RoutesController(String startingPoint){
        load(startingPoint);
    }

    public RoutesController(String startingPoint, String finalPoint, float distance){
        this.route = new Route();
        this.route.setId(Main.generateId("route"));
        this.route.setStartingPoint(startingPoint);
        this.route.setFinalPoint(finalPoint);
        this.route.setDistance(distance);
    }

    public boolean found(){
        return !Integer.toString(this.route.getId()).isEmpty();
    }

    private void load(String startingPoint){
        int[] existence = validateExistence(startingPoint);

        if(existence[0] == 1)
            this.route = Main.getRoutes().get(existence[1]);
    }

    private void loadById(int id){
        int[] existence = validateExistenceById(id);

        if(existence[0] == 1)
            this.route = Main.getRoutes().get(existence[1]);
    }

    public int[] validateExistence(String startingPoint){
        int[] existence = new int[2];

        // Find the doctor according to the code
        for (int i = 0; i < Main.getRoutes().size(); i++) {
            if(Main.getRoutes().get(i).getStartingPoint().equals(startingPoint)){
                existence[0] = 1;
                existence[1] = i;
                break;
            }
        }

        return existence;
    }

    public int[] validateExistenceById(int id){
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

    public static String[] getPoints(String removeRoute){
        int indexToRemove = -1;
        ArrayList<String> points = new ArrayList<String>();
//        String[] points = new String[Main.getRoutes().size() * 2];

        for (int i = 0; i < Main.getRoutes().size() * 2; i++) {
            if(i<Main.getRoutes().size()){
                points.add(Main.getRoutes().get(i).getStartingPoint());
            }else{
                points.add(Main.getRoutes().get(i - Main.getRoutes().size()).getFinalPoint());
            }
        }

        if(!removeRoute.isEmpty()) {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).equals(removeRoute)) {
                    indexToRemove = i;
                    break;
                }
            }

            if(indexToRemove != -1) points.remove(indexToRemove);
        }


        // Convert ArrayList to an array of Strings
        String[] pointsArr = points.toArray(new String[points.size()]);


        return pointsArr;
    }

    public void update(int distance){
        this.route.setDistance(distance);
    }
    public Route getRoute(){
        return this.route;
    }


}
