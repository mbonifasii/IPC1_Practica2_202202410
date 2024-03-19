package com.marcosbonifasi;


import com.marcosbonifasi.models.History;
import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Route> routes = new ArrayList<Route>();
    private static ArrayList<Trip> trips = new ArrayList<Trip>();
    private static ArrayList<History> histories = new ArrayList<History>();
    private static Trip[] onGoingTrips = new Trip[3];
    private static int counterRoutes = 0;
    private static int counterTrips = 0;

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();
        dashboardView.setVisible(true);
    }

    public static ArrayList<Route> getRoutes(){
        return routes;
    }

    public static int generateId(String option){
        int id = 0;
        switch (option){
            case "route":
                id = counterRoutes + 1;
                counterRoutes++;
                break;
            case "trip":
                id = counterTrips + 1;
                counterTrips++;
                break;
        }

        return id;
    }

    public static void addRoute(Route route){
        routes.add(route);
    }
    public static void addTrip(Trip trip){
        trips.add(trip);
    }
    public static void addHistory(History history){
        histories.add(history);
    }

    public static void addTripToQueue(Trip trip){
        for (int i = 0; i < onGoingTrips.length; i++) {
            if(onGoingTrips[i] == null){
                System.out.println("New trip added");
                onGoingTrips[i] = trip;
                break;
            }
        }
    }

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static boolean driversAvailable(){
        System.out.println(onGoingTrips[0] == null || onGoingTrips[1] == null || onGoingTrips[2] == null);
        return onGoingTrips[0] == null || onGoingTrips[1] == null || onGoingTrips[2] == null;
    }

    public static boolean anyTripOnGoing(){
        System.out.println(onGoingTrips[0] != null || onGoingTrips[1] != null || onGoingTrips[2] != null);
        return onGoingTrips[0] != null || onGoingTrips[1] != null || onGoingTrips[2] != null;
    }

    public static Trip[] getOnGoingTrips(){
        return onGoingTrips;
    }
}
