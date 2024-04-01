package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class Vehicle3Thread extends Thread {

    float xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle3 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVehicle3Thread trackingVehicle3Thread;

    public Vehicle3Thread(){}
    public Vehicle3Thread(TripsTrackingView tripsTrackingView, TrackingVehicle3Thread trackingVehicle3Thread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle3.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle3.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo3.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo3.getY();
        this.trackingVehicle3Thread = trackingVehicle3Thread;
    }

    public void run(){
        try{
            while(runningVehicle3){
                sleep((int) (600/(15/this.trackingVehicle3Thread.trip.getDistance()))); // this should be according to the distance

                if(this.trackingVehicle3Thread.tripType.equals("go")){
                    this.xVehicle-=600.0f/this.trackingVehicle3Thread.trip.getDistance();
                    this.xInfo -=600.0f/this.trackingVehicle3Thread.trip.getDistance();
                } else if(this.trackingVehicle3Thread.tripType.equals("return")){
                    this.xVehicle+=600.0f/this.trackingVehicle3Thread.trip.getDistance();
                    this.xInfo+=600.0f/this.trackingVehicle3Thread.trip.getDistance();
                }

                int initKm = (int) Math.floor(this.trackingVehicle3Thread.trip.getDistanceTraveled());
//                int finalKm = (int) Math.floor(this.trackingVehicle3Thread.trip.getDistanceTraveled() + ((this.trackingVehicle3Thread.trip.getDistance()/750.0f) * 10.0f));
                int finalKm = (int) Math.floor(this.trackingVehicle3Thread.trip.getDistanceTraveled() + ((600.0f/this.trackingVehicle3Thread.trip.getDistance()) * this.trackingVehicle3Thread.trip.getDistance())/600.0f);

                if((finalKm - initKm) == 1.0f) {
                    this.trackingVehicle3Thread.trip.getVehicle().setGasoline(this.trackingVehicle3Thread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVehicle3Thread.trip.setGasolineConsumed(this.trackingVehicle3Thread.trip.getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle3.setLocation((int) Math.floor(xVehicle), (int )Math.floor(yVehicle));
                this.tripsTrackingView.labelCurrentInfo3.setLocation((int) Math.floor(xInfo), (int) Math.floor(yInfo));
                this.trackingVehicle3Thread.trip.setDistanceTraveled((600.0f/this.trackingVehicle3Thread.trip.getDistance()) * this.trackingVehicle3Thread.trip.getDistance()/600.0f);
                this.trackingVehicle3Thread.trip.setxVehicle(this.xVehicle);
                this.trackingVehicle3Thread.trip.setxInfo(this.xInfo);

                this.tripsTrackingView.labelCurrentInfo3.setText(
                        "<html>" +
                                "Recorrido " + this.trackingVehicle3Thread.trip.getDistanceTraveled() +
                                "<br>" +
                                "Gasolina " + this.trackingVehicle3Thread.trip.getVehicle().getGasoline() +
                                "</html>"
                );

                if(this.trackingVehicle3Thread.trip.getVehicle().getGasoline() <= 0.0f){
                    this.stopThread();
                    this.trackingVehicle3Thread.stopClockThread();

                    // Show refill button
                    this.tripsTrackingView.btnRefillTank3 = new JButton("Recargar");
                    this.tripsTrackingView.btnRefillTank3.setBounds(0, 0, 100, 30);
                    if(this.trackingVehicle3Thread.tripType.equals("return")){
                        this.tripsTrackingView.btnRefillTank3.setLocation(this.tripsTrackingView.labelVehicle3.getX() - 100, this.tripsTrackingView.labelVehicle3.getY());
                    }else{
                        this.tripsTrackingView.btnRefillTank3.setLocation(this.tripsTrackingView.labelVehicle3.getX() + 100, this.tripsTrackingView.labelVehicle3.getY());
                    }

                    this.tripsTrackingView.btnRefillTank3.setBackground(new Color(230, 57, 70));
                    this.tripsTrackingView.btnRefillTank3.setForeground(Color.white);
                    this.tripsTrackingView.btnRefillTank3.setFont(new Font(this.tripsTrackingView.btnRefillTank3.getFont().getFontName(), Font.BOLD, 10));
                    this.tripsTrackingView.btnRefillTank3.setOpaque(true);
                    this.tripsTrackingView.btnRefillTank3.setBorderPainted(false);
                    this.tripsTrackingView.btnRefillTank3.setVisible(true);
                    this.tripsTrackingView.btnRefillTank3.setEnabled(true);
                    this.tripsTrackingView.btnRefillTank3.addMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.highway3.add(this.tripsTrackingView.btnRefillTank3);
                }


                if (this.tripsTrackingView.labelVehicle3.getX() >= 600.0f) {
                    this.trackingVehicle3Thread.trip.setStatus("finalized");
                    this.tripsTrackingView.btnReturn3.setEnabled(false);
                    this.tripsTrackingView.btnReturn3.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnInitDriver3.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver3.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnResumeVehicle3.setEnabled(false);
                    this.tripsTrackingView.btnResumeVehicle3.removeMouseListener(this.tripsTrackingView);

                    JOptionPane.showMessageDialog(null, "Viaje conductor 3 finalizado :)");
                    this.trackingVehicle3Thread.trip.setFinalDatetime(LocalDateTime.now().toString());
                    this.tripsTrackingView.cleanVehicle3Info();
                    Main.makeVehicleAvailable(this.trackingVehicle3Thread.trip.getVehicleName());
                    Main.removeOnGoingTrip(this.trackingVehicle3Thread.trip);
                }

                if (this.tripsTrackingView.labelVehicle3.getX() <= 0.0f || this.tripsTrackingView.labelVehicle3.getX() >= 600.0f) {
                    this.stopThread();
                    this.trackingVehicle3Thread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle3.getX() <= 0.0f){
                    this.tripsTrackingView.btnInitDriver3.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver3.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnReturn3.setEnabled(true);
                    this.tripsTrackingView.btnReturn3.addMouseListener(this.tripsTrackingView);
                }

                this.tripsTrackingView.highway3.repaint();
            }
        } catch (Exception e){
            System.out.println("Something went wrong :(");
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle3 = false;
    }

}
