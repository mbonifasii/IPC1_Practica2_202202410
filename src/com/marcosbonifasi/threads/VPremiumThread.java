package com.marcosbonifasi.threads;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;

public class VPremiumThread extends Thread {

    int xVehicle, yVehicle, xInfo, yInfo;
    private volatile boolean runningVehicle3 = true;
    private TripsTrackingView tripsTrackingView;
    private TrackingVPremiumThread trackingVPremiumThread;

    public VPremiumThread(){}
    public VPremiumThread(TripsTrackingView tripsTrackingView, TrackingVPremiumThread trackingVPremiumThread){
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
                sleep((int) (750/this.trackingVPremiumThread.trip.getDistance())*10); // this should be according to the distance

                if(this.trackingVPremiumThread.tripType.equals("go")){
                    this.xVehicle-=8;
                    this.xInfo -=8;
                } else if(this.trackingVPremiumThread.tripType.equals("return")){
                    this.xVehicle+=8;
                    this.xInfo+=8;
                }


                int initKm = (int) Math.floor(this.trackingVPremiumThread.trip.getHistory().getDistanceTraveled());
                int finalKm = (int) Math.floor(this.trackingVPremiumThread.trip.getHistory().getDistanceTraveled() + (this.trackingVPremiumThread.trip.getDistance()/750.0f)*10f);

                if((finalKm - initKm) == 1.0) {
                    this.trackingVPremiumThread.trip.getVehicle().setGasoline(this.trackingVPremiumThread.trip.getVehicle().getGasoline() - 0.1f);
                    this.trackingVPremiumThread.trip.getHistory().setGasolineConsumed(this.trackingVPremiumThread.trip.getHistory().getGasolineConsumed() + 0.1f);
                }

                this.tripsTrackingView.labelVehicle3.setLocation(xVehicle, yVehicle);
                this.tripsTrackingView.labelCurrentInfo3.setLocation(xInfo, yInfo);
                this.trackingVPremiumThread.trip.getHistory().setDistanceTraveled(((this.trackingVPremiumThread.trip.getDistance()/750.0f)*10.0f));

                this.tripsTrackingView.labelCurrentInfo3.setText(
                        "<html>" +
                                "Recorrido " + this.trackingVPremiumThread.trip.getHistory().getDistanceTraveled() +
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


                if (this.tripsTrackingView.labelVehicle3.getX() == 600)
                    this.trackingVPremiumThread.trip.getHistory().setStatus("finalized");

                if (this.tripsTrackingView.labelVehicle3.getX() == 0 || this.tripsTrackingView.labelVehicle3.getX() == 600) {
                    this.stopThread();
                    this.trackingVPremiumThread.stopClockThread();
                }

                if (this.tripsTrackingView.labelVehicle3.getX() == 0){
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
