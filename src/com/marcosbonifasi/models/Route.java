package com.marcosbonifasi.models;

public class Route {

    private int id, distance;
    private String startingPoint, finalPoint;

    public Route(){}

    public Route(int id, int distance, String startingPoint, String finalPoint){
        this.id = id;
        this.distance = distance;
        this.startingPoint = startingPoint;
        this.finalPoint = finalPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(String finalPoint) {
        this.finalPoint = finalPoint;
    }
}
