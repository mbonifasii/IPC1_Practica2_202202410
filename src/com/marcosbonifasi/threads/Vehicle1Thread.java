package com.marcosbonifasi.threads;

import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;

public class Vehicle1Thread extends Thread {

    float xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle1 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVehicle1Thread trackingVehicle1Thread;

    public Vehicle1Thread(){}
    public Vehicle1Thread(TripsTrackingView tripsTrackingView, TrackingVehicle1Thread trackingVehicle1Thread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle1.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle1.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo1.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo1.getY();
        this.trackingVehicle1Thread = trackingVehicle1Thread;
        System.out.println("asdfasdfasdfas");
    }

    public void run(){
        try{
            while(runningVehicle1){
                sleep((int) (600/(15/this.trackingVehicle1Thread.trip.getDistance()))); // this should be according to the distance

                if(this.trackingVehicle1Thread.tripType.equals("go")){
                    this.xVehicle-=600.0f/this.trackingVehicle1Thread.trip.getDistance();
                    this.xInfo -=600.0f/this.trackingVehicle1Thread.trip.getDistance();
                } else if(this.trackingVehicle1Thread.tripType.equals("return")){
                    this.xVehicle+=600.0f/this.trackingVehicle1Thread.trip.getDistance();
                    this.xInfo+=600.0f/this.trackingVehicle1Thread.trip.getDistance();
                }


                int initKm = (int) Math.floor(this.trackingVehicle1Thread.trip.getDistanceTraveled());
                int finalKm = (int) Math.floor(this.trackingVehicle1Thread.trip.getDistanceTraveled() + ((600.0f/this.trackingVehicle1Thread.trip.getDistance()) * this.trackingVehicle1Thread.trip.getDistance())/600.0f);

                if((finalKm - initKm) == 1.0f) {
                    this.trackingVehicle1Thread.trip.getVehicle().setGasoline(this.trackingVehicle1Thread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVehicle1Thread.trip.setGasolineConsumed(this.trackingVehicle1Thread.trip.getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle1.setLocation((int) Math.floor(xVehicle), (int )Math.floor(yVehicle));
                this.tripsTrackingView.labelCurrentInfo1.setLocation((int) Math.floor(xInfo), (int) Math.floor(yInfo));
                this.trackingVehicle1Thread.trip.setDistanceTraveled((600.0f/this.trackingVehicle1Thread.trip.getDistance()) * this.trackingVehicle1Thread.trip.getDistance()/600.0f);
                this.trackingVehicle1Thread.trip.setxVehicle(this.xVehicle);
                this.trackingVehicle1Thread.trip.setxInfo(this.xInfo);

                this.tripsTrackingView.labelCurrentInfo1.setText(
                        "<html>" +
                        "Recorrido " + this.trackingVehicle1Thread.trip.getDistanceTraveled() +
                        "<br>" +
                        "Gasolina " + this.trackingVehicle1Thread.trip.getVehicle().getGasoline() +
                        "</html>"
                );

                if(this.trackingVehicle1Thread.trip.getVehicle().getGasoline() <= 0.0f){
                    this.stopThread();
                    this.trackingVehicle1Thread.stopClockThread();

                    // Show refill button
                    this.tripsTrackingView.btnRefillTank1 = new JButton("Recargar");
                    this.tripsTrackingView.btnRefillTank1.setBounds(0, 0, 100, 30);
                    if(this.trackingVehicle1Thread.tripType.equals("return")){
                        this.tripsTrackingView.btnRefillTank1.setLocation(this.tripsTrackingView.labelVehicle1.getX() - 100, this.tripsTrackingView.labelVehicle1.getY());
                    }else{
                        this.tripsTrackingView.btnRefillTank1.setLocation(this.tripsTrackingView.labelVehicle1.getX() + 100, this.tripsTrackingView.labelVehicle1.getY());
                    }

                    this.tripsTrackingView.btnRefillTank1.setBackground(new Color(230, 57, 70));
                    this.tripsTrackingView.btnRefillTank1.setForeground(Color.white);
                    this.tripsTrackingView.btnRefillTank1.setFont(new Font(this.tripsTrackingView.btnRefillTank1.getFont().getFontName(), Font.BOLD, 10));
                    this.tripsTrackingView.btnRefillTank1.setOpaque(true);
                    this.tripsTrackingView.btnRefillTank1.setBorderPainted(false);
                    this.tripsTrackingView.btnRefillTank1.setVisible(true);
                    this.tripsTrackingView.btnRefillTank1.setEnabled(true);
                    this.tripsTrackingView.btnRefillTank1.addMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.highway1.add(this.tripsTrackingView.btnRefillTank1);
                }


                if (this.tripsTrackingView.labelVehicle1.getX() >= 600.0f) {
                    this.trackingVehicle1Thread.trip.setStatus("finalized");
                    this.tripsTrackingView.btnReturn1.setEnabled(false);
                    this.tripsTrackingView.btnReturn1.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnInitDriver1.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver1.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnResumeVehicle1.setEnabled(false);
                    this.tripsTrackingView.btnResumeVehicle1.removeMouseListener(this.tripsTrackingView);

                    JOptionPane.showMessageDialog(null, "Viaje finalizado :)");
                    this.tripsTrackingView.cleanVehicle1Info();
                }
                if (this.tripsTrackingView.labelVehicle1.getX() <= 0 || this.tripsTrackingView.labelVehicle1.getX() >= 600) {
                    this.stopThread();
                    this.trackingVehicle1Thread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle1.getX() <= 0){
                    this.tripsTrackingView.btnInitDriver1.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver1.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnReturn1.setEnabled(true);
                    this.tripsTrackingView.btnReturn1.addMouseListener(this.tripsTrackingView);
                }

                this.tripsTrackingView.highway1.repaint();
            }
        } catch (Exception e){
            System.out.println("Something went wrong :(");
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle1 = false;
    }

}
