package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;

public class Vehicle3Thread extends Thread {

    float xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle3 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVPremiumThread trackingVPremiumThread;

    public Vehicle3Thread(){}
    public Vehicle3Thread(TripsTrackingView tripsTrackingView, TrackingVPremiumThread trackingVPremiumThread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle3.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle3.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo3.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo3.getY();
        this.trackingVPremiumThread = trackingVPremiumThread;
    }

    public void run(){
        try{
            while(runningVehicle3){
                sleep((int) (600/(15/this.trackingVPremiumThread.trip.getDistance()))); // this should be according to the distance

                if(this.trackingVPremiumThread.tripType.equals("go")){
                    this.xVehicle-=600.0f/this.trackingVPremiumThread.trip.getDistance();
                    this.xInfo -=600.0f/this.trackingVPremiumThread.trip.getDistance();
                } else if(this.trackingVPremiumThread.tripType.equals("return")){
                    this.xVehicle+=600.0f/this.trackingVPremiumThread.trip.getDistance();
                    this.xInfo+=600.0f/this.trackingVPremiumThread.trip.getDistance();
                }

                int initKm = (int) Math.floor(this.trackingVPremiumThread.trip.getDistanceTraveled());
//                int finalKm = (int) Math.floor(this.trackingVPremiumThread.trip.getDistanceTraveled() + ((this.trackingVPremiumThread.trip.getDistance()/750.0f) * 10.0f));
                int finalKm = (int) Math.floor(this.trackingVPremiumThread.trip.getDistanceTraveled() + ((600.0f/this.trackingVPremiumThread.trip.getDistance()) * this.trackingVPremiumThread.trip.getDistance())/600.0f);

                if((finalKm - initKm) == 1.0f) {
                    this.trackingVPremiumThread.trip.getVehicle().setGasoline(this.trackingVPremiumThread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVPremiumThread.trip.setGasolineConsumed(this.trackingVPremiumThread.trip.getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle3.setLocation((int) Math.floor(xVehicle), (int )Math.floor(yVehicle));
                this.tripsTrackingView.labelCurrentInfo3.setLocation((int) Math.floor(xInfo), (int) Math.floor(yInfo));
                this.trackingVPremiumThread.trip.setDistanceTraveled((600.0f/this.trackingVPremiumThread.trip.getDistance()) * this.trackingVPremiumThread.trip.getDistance()/600.0f);

                this.tripsTrackingView.labelCurrentInfo3.setText(
                        "<html>" +
                                "Recorrido " + this.trackingVPremiumThread.trip.getDistanceTraveled() +
                                "<br>" +
                                "Gasolina " + this.trackingVPremiumThread.trip.getVehicle().getGasoline() +
                                "</html>"
                );

                if(this.trackingVPremiumThread.trip.getVehicle().getGasoline() <= 0.0f){
                    this.stopThread();
                    this.trackingVPremiumThread.stopClockThread();

                    // Show refill button
                    this.tripsTrackingView.btnRefillTank3 = new JButton("Recargar");
                    this.tripsTrackingView.btnRefillTank3.setBounds(0, 0, 100, 30);
                    if(this.trackingVPremiumThread.tripType.equals("return")){
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


                if (this.tripsTrackingView.labelVehicle3.getX() >= 600.0f)
                    this.trackingVPremiumThread.trip.setStatus("finalized");

                if (this.tripsTrackingView.labelVehicle3.getX() <= 0.0f || this.tripsTrackingView.labelVehicle3.getX() >= 600.0f) {
                    this.stopThread();
                    this.trackingVPremiumThread.stopClockThread();
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
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle3 = false;
    }

}
