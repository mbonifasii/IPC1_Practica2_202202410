package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class Vehicle2Thread extends Thread {

    float xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle2 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVehicle2Thread trackingVehicle2Thread;

    public Vehicle2Thread(){}
    public Vehicle2Thread(TripsTrackingView tripsTrackingView, TrackingVehicle2Thread trackingVehicle2Thread){
        this.tripsTrackingView = tripsTrackingView;
        this.xVehicle= this.tripsTrackingView.labelVehicle2.getX();
        this.yVehicle= this.tripsTrackingView.labelVehicle2.getY();
        this.xInfo= this.tripsTrackingView.labelCurrentInfo2.getX();
        this.yInfo= this.tripsTrackingView.labelCurrentInfo2.getY();
        this.trackingVehicle2Thread = trackingVehicle2Thread;
    }

    public void run(){
        try{
            while(runningVehicle2){
                sleep((int) (600/(15/this.trackingVehicle2Thread.trip.getDistance()))); // this should be according to the distance

                if(this.trackingVehicle2Thread.tripType.equals("go")){
                    this.xVehicle-=600.0f/this.trackingVehicle2Thread.trip.getDistance();
                    this.xInfo -=600.0f/this.trackingVehicle2Thread.trip.getDistance();
                } else if(this.trackingVehicle2Thread.tripType.equals("return")){
                    this.xVehicle+=600.0f/this.trackingVehicle2Thread.trip.getDistance();
                    this.xInfo+=600.0f/this.trackingVehicle2Thread.trip.getDistance();
                }


                int initKm = (int) Math.floor(this.trackingVehicle2Thread.trip.getDistanceTraveled());
                int finalKm = (int) Math.floor(this.trackingVehicle2Thread.trip.getDistanceTraveled() + ((600.0f/this.trackingVehicle2Thread.trip.getDistance()) * this.trackingVehicle2Thread.trip.getDistance())/600.0f);

                if((finalKm - initKm) == 1.0f) {
                    this.trackingVehicle2Thread.trip.getVehicle().setGasoline(this.trackingVehicle2Thread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVehicle2Thread.trip.setGasolineConsumed(this.trackingVehicle2Thread.trip.getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle2.setLocation((int) Math.floor(xVehicle), (int )Math.floor(yVehicle));
                this.tripsTrackingView.labelCurrentInfo2.setLocation((int) Math.floor(xInfo), (int) Math.floor(yInfo));
                this.trackingVehicle2Thread.trip.setDistanceTraveled((600.0f/this.trackingVehicle2Thread.trip.getDistance()) * this.trackingVehicle2Thread.trip.getDistance()/600.0f);
                this.trackingVehicle2Thread.trip.setxVehicle(this.xVehicle);
                this.trackingVehicle2Thread.trip.setxInfo(this.xInfo);

                this.tripsTrackingView.labelCurrentInfo2.setText(
                        "<html>" +
                        "Recorrido " + this.trackingVehicle2Thread.trip.getDistanceTraveled() +
                        "<br>" +
                        "Gasolina " + this.trackingVehicle2Thread.trip.getVehicle().getGasoline() +
                        "</html>"
                );

                if(this.trackingVehicle2Thread.trip.getVehicle().getGasoline() <= 0.0f){
                    this.stopThread();
                    this.trackingVehicle2Thread.stopClockThread();

                    // Show refill button
                    this.tripsTrackingView.btnRefillTank2 = new JButton("Recargar");
                    this.tripsTrackingView.btnRefillTank2.setBounds(0, 0, 100, 30);
                    if(this.trackingVehicle2Thread.tripType.equals("return")){
                        this.tripsTrackingView.btnRefillTank2.setLocation(this.tripsTrackingView.labelVehicle2.getX() - 100, this.tripsTrackingView.labelVehicle2.getY());
                    }else{
                        this.tripsTrackingView.btnRefillTank2.setLocation(this.tripsTrackingView.labelVehicle2.getX() + 100, this.tripsTrackingView.labelVehicle2.getY());
                    }

                    this.tripsTrackingView.btnRefillTank2.setBackground(new Color(230, 57, 70));
                    this.tripsTrackingView.btnRefillTank2.setForeground(Color.white);
                    this.tripsTrackingView.btnRefillTank2.setFont(new Font(this.tripsTrackingView.btnRefillTank2.getFont().getFontName(), Font.BOLD, 10));
                    this.tripsTrackingView.btnRefillTank2.setOpaque(true);
                    this.tripsTrackingView.btnRefillTank2.setBorderPainted(false);
                    this.tripsTrackingView.btnRefillTank2.setVisible(true);
                    this.tripsTrackingView.btnRefillTank2.setEnabled(true);
                    this.tripsTrackingView.btnRefillTank2.addMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.highway2.add(this.tripsTrackingView.btnRefillTank2);
                }


                if (this.tripsTrackingView.labelVehicle2.getX() >= 600.0f) {
                    this.trackingVehicle2Thread.trip.setStatus("finalized");
                    this.tripsTrackingView.btnReturn2.setEnabled(false);
                    this.tripsTrackingView.btnReturn2.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnInitDriver2.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver2.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnResumeVehicle2.setEnabled(false);
                    this.tripsTrackingView.btnResumeVehicle2.removeMouseListener(this.tripsTrackingView);

                    JOptionPane.showMessageDialog(null, "Viaje conductor 2 finalizado :)");
                    this.trackingVehicle2Thread.trip.setFinalDatetime(LocalDateTime.now().toString());
                    this.tripsTrackingView.cleanVehicle2Info();
                    Main.makeVehicleAvailable(this.trackingVehicle2Thread.trip.getVehicleName());
                    Main.removeOnGoingTrip(this.trackingVehicle2Thread.trip);
                }

                if (this.tripsTrackingView.labelVehicle2.getX() <= 0.0f || this.tripsTrackingView.labelVehicle2.getX() >= 600.0f) {
                    this.stopThread();
                    this.trackingVehicle2Thread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle2.getX() <= 0.0f){
                    this.tripsTrackingView.btnInitDriver2.setEnabled(false);
                    this.tripsTrackingView.btnInitDriver2.removeMouseListener(this.tripsTrackingView);
                    this.tripsTrackingView.btnReturn2.setEnabled(true);
                    this.tripsTrackingView.btnReturn2.addMouseListener(this.tripsTrackingView);
                }

                this.tripsTrackingView.highway2.repaint();
            }
        } catch (Exception e){
            System.out.println("Something went wrong :(");
            System.out.println(e);
        }
    }

    public void stopThread() {
        runningVehicle2 = false;
    }

}
