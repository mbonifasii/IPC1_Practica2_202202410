package com.marcosbonifasi;

import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.models.Trip;
import com.marcosbonifasi.models.Vehicle;
import com.marcosbonifasi.threads.TrackingVehicle1Thread;
import com.marcosbonifasi.threads.TrackingVehicle2Thread;
import com.marcosbonifasi.threads.TrackingVehicle3Thread;
import com.marcosbonifasi.views.DashboardView;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Route> routes = new ArrayList<Route>();
    private static ArrayList<Trip> trips = new ArrayList<Trip>();
    private static ArrayList<Vehicle> vehiclesAvailable = new ArrayList<Vehicle>();
    private static Trip[] onGoingTrips = new Trip[3];
    private static int counterRoutes = 0;
    private static int counterTrips = 0;

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();
        dashboardView.setVisible(true);

        initVehicles();

        readBinaryFile();
    }

    public static ArrayList<Route> getRoutes(){
        return routes;
    }
    public static ArrayList<Vehicle> getVehiclesAvailable(){
        return vehiclesAvailable;
    }

    public static void makeVehicleBusy(String vehicleName){
        int index = -1;
        for (int i = 0; i < vehiclesAvailable.size(); i++) {
            if (vehiclesAvailable.get(i).getName().equals(vehicleName)) {
                index = i;
                break;
            }
        }
        if(index != -1){
            vehiclesAvailable.remove(index);
        }

    }

    private static void initVehicles(){
        for (int i = 0; i < Vehicle.getVehicles().length; i++) {
            vehiclesAvailable.add(new Vehicle(Vehicle.getVehicles()[i][0], Float.parseFloat(Vehicle.getVehicles()[i][1])));
        }
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
        return onGoingTrips[0] == null || onGoingTrips[1] == null || onGoingTrips[2] == null;
    }

    public static boolean anyTripOnGoing(){
        return onGoingTrips[0] != null || onGoingTrips[1] != null || onGoingTrips[2] != null;
    }

    public static Trip[] getOnGoingTrips(){
        return onGoingTrips;
    }

    public static ArrayList<Trip> getTrips(){
        return trips;
    }

    // Threads

    public static void goVehicle1(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[0] == null) return;
        TrackingVehicle1Thread trackingVehicle1Thread = new TrackingVehicle1Thread(tripsTrackingView, Main.getOnGoingTrips()[0], "go");
        trackingVehicle1Thread.start();

        getOnGoingTrips()[0].setInitialDatetime(LocalDateTime.now().toString());
        getOnGoingTrips()[0].setStatus("go");
    }

    public static void goVehicle2(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[1] == null) return;
        TrackingVehicle2Thread trackingVehicle2Thread = new TrackingVehicle2Thread(tripsTrackingView, Main.getOnGoingTrips()[1], "go");
        trackingVehicle2Thread.start();

        Main.getOnGoingTrips()[1].setInitialDatetime(LocalDateTime.now().toString());
        Main.getOnGoingTrips()[1].setStatus("go");
    }

    public static void goVehicle3(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[2] == null) return;
        TrackingVehicle3Thread trackingVehicle3Thread = new TrackingVehicle3Thread(tripsTrackingView, Main.getOnGoingTrips()[2], "go");
        trackingVehicle3Thread.start();

        Main.getOnGoingTrips()[2].setInitialDatetime(LocalDateTime.now().toString());
        Main.getOnGoingTrips()[2].setStatus("go");
    }

    public static void returnVehicle1(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[0] == null) return;
        TrackingVehicle1Thread trackingVehicle1Thread = new TrackingVehicle1Thread(tripsTrackingView, Main.getOnGoingTrips()[0],"return");
        Main.getOnGoingTrips()[0].setStatus("return");
        trackingVehicle1Thread.start();
    }

    public static void returnVehicle2(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[1] == null) return;
        TrackingVehicle2Thread trackingVehicle2Thread = new TrackingVehicle2Thread(tripsTrackingView, Main.getOnGoingTrips()[1],"return");
        Main.getOnGoingTrips()[1].setStatus("return");
        trackingVehicle2Thread.start();
    }

    public static void returnVehicle3(TripsTrackingView tripsTrackingView){
        if(getOnGoingTrips()[2] == null) return;
        TrackingVehicle3Thread trackingVehicle3Thread = new TrackingVehicle3Thread(tripsTrackingView, Main.getOnGoingTrips()[2],"return");
        Main.getOnGoingTrips()[2].setStatus("return");
        trackingVehicle3Thread.start();
    }

    public static void refillTank1(TripsTrackingView tripsTrackingView){
        TrackingVehicle1Thread trackingVehicle1Thread;
        trackingVehicle1Thread = new TrackingVehicle1Thread(tripsTrackingView, Main.getOnGoingTrips()[0], Main.getOnGoingTrips()[0].getStatus());
        Main.getOnGoingTrips()[0].getVehicle().setGasoline(6.0f);
        trackingVehicle1Thread.start();
        tripsTrackingView.btnRefillTank1.setVisible(false);
        tripsTrackingView.btnRefillTank1.setEnabled(false);
        tripsTrackingView.btnRefillTank1.removeMouseListener(tripsTrackingView);
    }

    public static void refillTank2(TripsTrackingView tripsTrackingView){
        TrackingVehicle2Thread trackingVehicle2Thread;
        trackingVehicle2Thread = new TrackingVehicle2Thread(tripsTrackingView, Main.getOnGoingTrips()[1], Main.getOnGoingTrips()[1].getStatus());
        Main.getOnGoingTrips()[1].getVehicle().setGasoline(10.0f);
        trackingVehicle2Thread.start();
        tripsTrackingView.btnRefillTank2.setVisible(false);
        tripsTrackingView.btnRefillTank2.setEnabled(false);
        tripsTrackingView.btnRefillTank2.removeMouseListener(tripsTrackingView);
    }

    public static void refillTank3(TripsTrackingView tripsTrackingView){
        TrackingVehicle3Thread trackingVehicle3Thread;
        trackingVehicle3Thread = new TrackingVehicle3Thread(tripsTrackingView, Main.getOnGoingTrips()[2], Main.getOnGoingTrips()[2].getStatus());
        Main.getOnGoingTrips()[2].getVehicle().setGasoline(12.0f);
        trackingVehicle3Thread.start();
        tripsTrackingView.btnRefillTank3.setVisible(false);
        tripsTrackingView.btnRefillTank3.setEnabled(false);
        tripsTrackingView.btnRefillTank3.removeMouseListener(tripsTrackingView);
    }

    // Serialization

    public static void createBinaryFile() {
        // Serialización de la lista
        try {
            // Creamos el archivo binario en la ruta especificada
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./cache/trips.bin"));
            // Escribimos nuestro ArrayList de tipo Trip
            out.writeObject(trips);
            // Cerramos el archivo
            out.close();
            System.out.println("Binary writen successfully :)");
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
            e.printStackTrace();
        }
    }

    public static Object readBinaryFile() {
        // Deserialización de la lista
        try {
            // Abrimos el archivo binario en la ruta especificada
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./cache/trips.bin"));
            // Leemos el objeto guardado (Arraylist de tipo Trip) y lo guardamos en un Arryalist del mismo tipo
            trips = (ArrayList<Trip>) in.readObject();
            System.out.println(trips);
            // Cerramos el archivo
            in.close();
            System.out.println("History list deserialize successfully :)");
            // Retornamos el objeto
            return trips;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Si no existe ningun archivo o si ocurre un error, se retorna null
        return null;
    }


}
