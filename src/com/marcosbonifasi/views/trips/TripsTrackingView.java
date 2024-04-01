package com.marcosbonifasi.views.trips;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TripsTrackingView extends JFrame implements MouseListener {

    private JPanel tripTrackingPanel;
    private JLabel closeLabel;
    private JLabel labelInitialPointInfo1, labelFinalPointInfo1;
    public JLabel labelCurrentInfo1;
    public JLabel labelVehicle1, labelVehicle2, labelVehicle3;
    private JLabel labelInitialPointInfo2, labelFinalPointInfo2;
    public JLabel labelCurrentInfo2;
    private JLabel labelInitialPointInfo3, labelFinalPointInfo3;
    public JLabel labelCurrentInfo3;
    private JButton btnGenerateTrip;
    public JButton btnInitAllDrivers;
    public JButton btnInitDriver1, btnInitDriver2, btnInitDriver3;
    public JButton btnRefillTank1, btnRefillTank2, btnRefillTank3;
    public JButton btnResumeVehicle1, btnResumeVehicle2, btnResumeVehicle3;
    public JButton btnReturn1, btnReturn2, btnReturn3;
    public Rectangle vehicle1, vehicle2, vehicle3;
    public Rectangle vehicle1Info, vehicle2Info, vehicle3Info;
    public JLabel highway1, highway2, highway3;

    public TripsTrackingView(){
        setSize(800, 620);
        setTitle("Viajes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initTripTrackingPanel();
        renderTrips();

        // Adding a WindowListener to capture window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Create binary file
                Main.createBinaryFile();

                // Handle the window closing event
                System.out.println("Application is closing...");
                System.exit(0); // This exits the application
            }
        });
    }

    private void initTripTrackingPanel(){
        tripTrackingPanel = new JPanel();
        tripTrackingPanel.setBounds(0, 0, 800, 600);
        tripTrackingPanel.setBackground(Color.decode("#F5F3F4"));
        tripTrackingPanel.setLayout(null);

        getContentPane().add(tripTrackingPanel);

        //  -- Components --
        btnGenerateTrip = new JButton("Generar viaje");
        btnGenerateTrip.setBounds(250, 20, 150, 30);
        btnGenerateTrip.setBackground(new Color(163, 196, 243));
        btnGenerateTrip.setForeground(Color.white);
        btnGenerateTrip.setFont(new Font(btnGenerateTrip.getFont().getFontName(), Font.BOLD, 13));
        btnGenerateTrip.setOpaque(true);
        btnGenerateTrip.setBorderPainted(false);
        if(!Main.driversAvailable() || Main.getRoutes().isEmpty())
            btnGenerateTrip.setEnabled(false);
        else
            btnGenerateTrip.addMouseListener(this);

        tripTrackingPanel.add(btnGenerateTrip);

        JLabel labelNoDriversAvailable = new JLabel("No hay pilotos disponibles :(");
        labelNoDriversAvailable.setBounds(250, 50, 200, 30);
        labelNoDriversAvailable.setForeground(Color.red);
        labelNoDriversAvailable.setFont(new Font(labelNoDriversAvailable.getFont().getFontName(), Font.PLAIN, 12));
        labelNoDriversAvailable.setVisible(false);
        if(!Main.driversAvailable()){
            labelNoDriversAvailable.setVisible(true);
        }
        tripTrackingPanel.add(labelNoDriversAvailable);

        JLabel labelNoRoutesAvailable = new JLabel("Las rutas no han sido cargadas");
        labelNoRoutesAvailable.setBounds(250, 50, 200, 30);
        labelNoRoutesAvailable.setForeground(Color.red);
        labelNoRoutesAvailable.setFont(new Font(labelNoRoutesAvailable.getFont().getFontName(), Font.PLAIN, 12));
        labelNoRoutesAvailable.setVisible(false);
        if(Main.getRoutes().isEmpty()){
            labelNoRoutesAvailable.setVisible(true);
        }
        tripTrackingPanel.add(labelNoRoutesAvailable);

        btnInitAllDrivers = new JButton("Iniciar todos");
        btnInitAllDrivers.setBounds(410, 20, 150, 30);
        btnInitAllDrivers.setBackground(new Color(144, 219, 244));
        btnInitAllDrivers.setForeground(Color.white);
        btnInitAllDrivers.setFont(new Font(btnInitAllDrivers.getFont().getFontName(), Font.BOLD, 13));
        btnInitAllDrivers.setOpaque(true);
        btnInitAllDrivers.setBorderPainted(false);
        btnInitAllDrivers.setEnabled(false);
        if(!Main.driversAvailable() || Main.getRoutes().isEmpty())
            btnInitAllDrivers.setEnabled(false);
        else
            btnInitAllDrivers.addMouseListener(this);

        tripTrackingPanel.add(btnInitAllDrivers);


        // First highway
        // Carga la imagen
        ImageIcon imgHighway1 = new ImageIcon(getClass().getResource("../../images/highway.jpeg"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimensionHighway = imgHighway1.getImage().getScaledInstance(700, 80, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageHighwayIcon = new ImageIcon(imageDimensionHighway);
        // Crea un JLabel para mostrar la imagen
        highway1 = new JLabel(adjustedImageHighwayIcon);
        highway1.setBounds(50, 120, 700, 80); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        tripTrackingPanel.add(highway1);

        btnInitDriver1 = new JButton("Iniciar");
        btnInitDriver1.setBounds(50, 200, 100, 30);
        btnInitDriver1.setBackground(new Color(128, 237, 153));
        btnInitDriver1.setForeground(Color.black);
        btnInitDriver1.setFont(new Font(btnInitDriver1.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver1.setOpaque(true);
        btnInitDriver1.setBorderPainted(false);
        btnInitDriver1.setEnabled(false);
        tripTrackingPanel.add(btnInitDriver1);

        btnReturn1 = new JButton("Regresar");
        btnReturn1.setBounds(640, 200, 110, 30);
        btnReturn1.setBackground(new Color(11, 57, 84));
        btnReturn1.setForeground(Color.white);
        btnReturn1.setFont(new Font(btnReturn1.getFont().getFontName(), Font.BOLD, 13));
        btnReturn1.setOpaque(true);
        btnReturn1.setBorderPainted(false);
        btnReturn1.setEnabled(false);
        tripTrackingPanel.add(btnReturn1);

        btnResumeVehicle1 = new JButton("Reanudar");
        btnResumeVehicle1.setBounds(500, 200, 110, 30);
        btnResumeVehicle1.setBackground(new Color(234, 226, 183));
        btnResumeVehicle1.setForeground(Color.white);
        btnResumeVehicle1.setFont(new Font(btnResumeVehicle1.getFont().getFontName(), Font.BOLD, 13));
        btnResumeVehicle1.setOpaque(true);
        btnResumeVehicle1.setBorderPainted(false);
        btnResumeVehicle1.setEnabled(false);
        tripTrackingPanel.add(btnResumeVehicle1);

        // Second highway
        // Carga la imagen
        ImageIcon imgHighway2 = new ImageIcon(getClass().getResource("../../images/highway.jpeg"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimensionHighway2 = imgHighway2.getImage().getScaledInstance(700, 80, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageHighway2Icon = new ImageIcon(imageDimensionHighway2);
        // Crea un JLabel para mostrar la imagen
        highway2 = new JLabel(adjustedImageHighway2Icon);
        highway2.setBounds(50, 280, 700, 80); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        tripTrackingPanel.add(highway2);

        btnInitDriver2 = new JButton("Iniciar");
        btnInitDriver2.setBounds(50, 360, 100, 30);
        btnInitDriver2.setBackground(new Color(128, 237, 153));
        btnInitDriver2.setForeground(Color.black);
        btnInitDriver2.setFont(new Font(btnInitDriver2.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver2.setOpaque(true);
        btnInitDriver2.setBorderPainted(false);
        btnInitDriver2.setEnabled(false);
        tripTrackingPanel.add(btnInitDriver2);

        btnReturn2 = new JButton("Regresar");
        btnReturn2.setBounds(640, 360, 110, 30);
        btnReturn2.setBackground(new Color(11, 57, 84));
        btnReturn2.setForeground(Color.white);
        btnReturn2.setFont(new Font(btnReturn2.getFont().getFontName(), Font.BOLD, 13));
        btnReturn2.setOpaque(true);
        btnReturn2.setBorderPainted(false);
        btnReturn2.setEnabled(false);
        tripTrackingPanel.add(btnReturn2);

        btnResumeVehicle2 = new JButton("Reanudar");
        btnResumeVehicle2.setBounds(500, 360, 110, 30);
        btnResumeVehicle2.setBackground(new Color(234, 226, 183));
        btnResumeVehicle2.setForeground(Color.white);
        btnResumeVehicle2.setFont(new Font(btnResumeVehicle2.getFont().getFontName(), Font.BOLD, 13));
        btnResumeVehicle2.setOpaque(true);
        btnResumeVehicle2.setBorderPainted(false);
        btnResumeVehicle2.setEnabled(false);
        tripTrackingPanel.add(btnResumeVehicle2);

        // Third highway
        // Carga la imagen
        ImageIcon imgHighway3 = new ImageIcon(getClass().getResource("../../images/highway.jpeg"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimensionHighway3 = imgHighway3.getImage().getScaledInstance(700, 80, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageHighway3Icon = new ImageIcon(imageDimensionHighway3);
        // Crea un JLabel para mostrar la imagen
        highway3 = new JLabel(adjustedImageHighway3Icon);
        highway3.setBounds(50, 450, 700, 80); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        tripTrackingPanel.add(highway3);

        btnInitDriver3 = new JButton("Iniciar");
        btnInitDriver3.setBounds(50, 530, 100, 30);
        btnInitDriver3.setBackground(new Color(128, 237, 153));
        btnInitDriver3.setForeground(Color.black);
        btnInitDriver3.setFont(new Font(btnInitDriver3.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver3.setOpaque(true);
        btnInitDriver3.setBorderPainted(false);
        btnInitDriver3.setEnabled(false);
        tripTrackingPanel.add(btnInitDriver3);

        btnReturn3 = new JButton("Regresar");
        btnReturn3.setBounds(640, 530, 110, 30);
        btnReturn3.setBackground(new Color(11, 57, 84));
        btnReturn3.setForeground(Color.white);
        btnReturn3.setFont(new Font(btnReturn3.getFont().getFontName(), Font.BOLD, 13));
        btnReturn3.setOpaque(true);
        btnReturn3.setBorderPainted(false);
        btnReturn3.setEnabled(false);
        tripTrackingPanel.add(btnReturn3);

        btnResumeVehicle3 = new JButton("Reanudar");
        btnResumeVehicle3.setBounds(500, 530, 110, 30);
        btnResumeVehicle3.setBackground(new Color(234, 226, 183));
        btnResumeVehicle3.setForeground(Color.white);
        btnResumeVehicle3.setFont(new Font(btnResumeVehicle3.getFont().getFontName(), Font.BOLD, 13));
        btnResumeVehicle3.setOpaque(true);
        btnResumeVehicle3.setBorderPainted(false);
        btnResumeVehicle3.setEnabled(false);
        tripTrackingPanel.add(btnResumeVehicle3);


        // Carga la imagen
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("../../images/close.png"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimension = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        // Crea un JLabel para mostrar la imagen
        closeLabel = new JLabel(adjustedImageIcon);
        closeLabel.setBounds(730, 10, 20, 20); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        closeLabel.addMouseListener(this);
        tripTrackingPanel.add(closeLabel);

        tripTrackingPanel.setVisible(true);
    }

    private void renderTrips(){
        if(!Main.anyTripOnGoing())
            return;

        // Render first trip
        if(Main.getOnGoingTrips()[0] != null){
            System.out.println("chale tilin");

            if(Main.getOnGoingTrips()[0].getxVehicle() == 600.0f){
                btnInitDriver1.setEnabled(true);
                btnInitDriver1.addMouseListener(this);
                btnReturn1.setEnabled(false);
                btnReturn1.removeMouseListener(this);
                btnResumeVehicle1.setEnabled(false);
                btnResumeVehicle1.removeMouseListener(this);
            } else if(Main.getOnGoingTrips()[0].getxVehicle() == 0.0f){
                btnInitDriver1.setEnabled(false);
                btnInitDriver1.removeMouseListener(this);
                btnReturn1.setEnabled(true);
                btnReturn1.addMouseListener(this);
                btnResumeVehicle1.setEnabled(false);
                btnResumeVehicle1.removeMouseListener(this);
            } else {
                btnInitDriver1.setEnabled(false);
                btnInitDriver1.removeMouseListener(this);
                btnReturn1.setEnabled(false);
                btnReturn1.removeMouseListener(this);
                btnResumeVehicle1.setEnabled(true);
                btnResumeVehicle1.addMouseListener(this);
            }

            labelInitialPointInfo1 = new JLabel(
                    "<html>" +
                    Main.getOnGoingTrips()[0].getVehicleName() +
                    "<br>" +
                    "Distancia " + Main.getOnGoingTrips()[0].getDistance() + "km" +
                    "<br>" +
                    "Destino " + Main.getOnGoingTrips()[0].getFinalPoint() +
                    "</html>"
            );

            labelInitialPointInfo1.setBounds(50, 50, 150, 90);
            labelInitialPointInfo1.setFont(new Font(labelInitialPointInfo1.getFont().getFontName(), Font.PLAIN, 10));
            labelInitialPointInfo1.setVisible(true);
            tripTrackingPanel.add(labelInitialPointInfo1);

            labelFinalPointInfo1 = new JLabel(
                    "<html>" +
                    "Inicio " + Main.getOnGoingTrips()[0].getStartingPoint()+
                    "</html>"
            );

            labelFinalPointInfo1.setBounds(650, 70, 150, 80);
            labelFinalPointInfo1.setFont(new Font(labelFinalPointInfo1.getFont().getFontName(), Font.PLAIN, 10));
            labelFinalPointInfo1.setVisible(true);
            tripTrackingPanel.add(labelFinalPointInfo1);

            this.labelVehicle1 = new JLabel();
            this.labelVehicle1.setBounds((int) Math.floor(Main.getOnGoingTrips()[0].getxVehicle()), 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
            ImageIcon img1 = new ImageIcon(
                getClass().getResource("../../images/" + selectedImageVehicle(Main.getOnGoingTrips()[0].getVehicleName()))
            );
            Image nuevo1 = img1.getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT);
            this.labelVehicle1.setHorizontalAlignment(SwingConstants.CENTER);
            this.labelVehicle1.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon render = new ImageIcon(nuevo1);
            this.labelVehicle1.setIcon(render);
            this.labelVehicle1.setVisible(true);
            vehicle1 = this.labelVehicle1.getBounds();
            highway1.add(this.labelVehicle1);


            labelCurrentInfo1 = new JLabel(
                "<html>" +
                "Recorrido " + Main.getOnGoingTrips()[0].getDistanceTraveled() +
                "<br>" +
                "Gasolina " + Main.getOnGoingTrips()[0].getVehicle().getGasoline() +
                "</html>"
            );

            labelCurrentInfo1.setBounds((int) Math.floor(Main.getOnGoingTrips()[0].getxInfo()), 0, 100, 40);
            labelCurrentInfo1.setForeground(Color.black);
            labelCurrentInfo1.setFont(new Font(labelCurrentInfo1.getFont().getFontName(), Font.BOLD, 8));
            labelCurrentInfo1.setVisible(true);
            highway1.add(labelCurrentInfo1);

        } else {
            btnInitDriver1.setEnabled(false);
            btnReturn1.setEnabled(false);
        }

        // Render second trip
        if(Main.getOnGoingTrips()[1] != null){

            if(Main.getOnGoingTrips()[1].getxVehicle() == 600.0f){
                btnInitDriver2.setEnabled(true);
                btnInitDriver2.addMouseListener(this);
                btnReturn2.setEnabled(false);
                btnReturn2.removeMouseListener(this);
                btnResumeVehicle2.setEnabled(false);
                btnResumeVehicle2.removeMouseListener(this);
            } else if(Main.getOnGoingTrips()[1].getxVehicle() == 0.0f){
                btnInitDriver2.setEnabled(false);
                btnInitDriver2.removeMouseListener(this);
                btnReturn2.setEnabled(true);
                btnReturn2.addMouseListener(this);
                btnResumeVehicle2.setEnabled(false);
                btnResumeVehicle2.removeMouseListener(this);
            } else {
                btnInitDriver2.setEnabled(false);
                btnInitDriver2.removeMouseListener(this);
                btnReturn2.setEnabled(false);
                btnReturn2.removeMouseListener(this);
                btnResumeVehicle2.setEnabled(true);
                btnResumeVehicle2.addMouseListener(this);
            }

            labelInitialPointInfo2 = new JLabel(
                    "<html>" +
                            Main.getOnGoingTrips()[1].getVehicle().getName()+
                            "<br>" +
                            "Distancia " + Main.getOnGoingTrips()[1].getDistance() + "km" +
                            "<br>" +
                            "Destino " + Main.getOnGoingTrips()[1].getFinalPoint() +
                            "</html>"
            );

            labelInitialPointInfo2.setBounds(50, 210, 150, 90);
            labelInitialPointInfo2.setFont(new Font(labelInitialPointInfo2.getFont().getFontName(), Font.PLAIN, 10));
            labelInitialPointInfo2.setVisible(true);
            tripTrackingPanel.add(labelInitialPointInfo2);

            labelFinalPointInfo2 = new JLabel(
                    "<html>" +
                            "Inicio " + Main.getOnGoingTrips()[1].getStartingPoint()+
                            "</html>"
            );

            labelFinalPointInfo2.setBounds(650, 220, 150, 80);
            labelFinalPointInfo2.setFont(new Font(labelFinalPointInfo2.getFont().getFontName(), Font.PLAIN, 10));
            labelFinalPointInfo2.setVisible(true);
            tripTrackingPanel.add(labelFinalPointInfo2);

            labelVehicle2 = new JLabel();
            labelVehicle2.setBounds((int) Math.floor(Main.getOnGoingTrips()[1].getxVehicle()), 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
            ImageIcon img2 = new ImageIcon(
                    getClass().getResource("../../images/" + selectedImageVehicle(Main.getOnGoingTrips()[1].getVehicle().getName()))
            );
            Image nuevo2 = img2.getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT);
            labelVehicle2.setHorizontalAlignment(SwingConstants.CENTER);
            labelVehicle2.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon render = new ImageIcon(nuevo2);
            labelVehicle2.setIcon(render);
            labelVehicle2.setVisible(true);
            vehicle2 = labelVehicle2.getBounds();
            highway2.add(labelVehicle2);

            labelCurrentInfo2 = new JLabel(
                    "<html>" +
                            "Recorrido " + Main.getOnGoingTrips()[1].getDistanceTraveled() +
                            "<br>" +
                            "Gasolina " + Main.getOnGoingTrips()[1].getVehicle().getGasoline() +
                            "</html>"
            );

            labelCurrentInfo2.setBounds((int) Math.floor(Main.getOnGoingTrips()[1].getxInfo()), 0, 100, 40);
            labelCurrentInfo2.setForeground(Color.black);
            labelCurrentInfo2.setFont(new Font(labelCurrentInfo2.getFont().getFontName(), Font.BOLD, 8));
            labelCurrentInfo2.setVisible(true);
            highway2.add(labelCurrentInfo2);
        } else {
            btnInitDriver2.setEnabled(false);
            btnReturn2.setEnabled(false);
        }

        // Render third trip
        if(Main.getOnGoingTrips()[2] != null){

            if(Main.getOnGoingTrips()[2].getxVehicle() == 600.0f){
                btnInitDriver3.setEnabled(true);
                btnInitDriver3.addMouseListener(this);
                btnReturn3.setEnabled(false);
                btnReturn3.removeMouseListener(this);
                btnResumeVehicle3.setEnabled(false);
                btnResumeVehicle3.removeMouseListener(this);
            } else if(Main.getOnGoingTrips()[2].getxVehicle() == 0.0f){
                btnInitDriver3.setEnabled(false);
                btnInitDriver3.removeMouseListener(this);
                btnReturn3.setEnabled(true);
                btnReturn3.addMouseListener(this);
                btnResumeVehicle3.setEnabled(false);
                btnResumeVehicle3.removeMouseListener(this);
            } else {
                btnInitDriver3.setEnabled(false);
                btnInitDriver3.removeMouseListener(this);
                btnReturn3.setEnabled(false);
                btnReturn3.removeMouseListener(this);
                btnResumeVehicle3.setEnabled(true);
                btnResumeVehicle3.addMouseListener(this);
            }


            labelInitialPointInfo3 = new JLabel(
                    "<html>" +
                            Main.getOnGoingTrips()[2].getVehicle().getName()+
                            "<br>" +
                            "Distancia " + Main.getOnGoingTrips()[2].getDistance() + "km" +
                            "<br>" +
                            "Destino " + Main.getOnGoingTrips()[2].getFinalPoint() +
                            "</html>"
            );

            labelInitialPointInfo3.setBounds(50, 380, 150, 90);
            labelInitialPointInfo3.setFont(new Font(labelInitialPointInfo3.getFont().getFontName(), Font.PLAIN, 10));
            labelInitialPointInfo3.setVisible(true);
            tripTrackingPanel.add(labelInitialPointInfo3);

            labelFinalPointInfo3= new JLabel(
                    "<html>" +
                            "Inicio " + Main.getOnGoingTrips()[2].getStartingPoint() +
                            "</html>"
            );

            labelFinalPointInfo3.setBounds(650, 395, 150, 80);
            labelFinalPointInfo3.setFont(new Font(labelFinalPointInfo3.getFont().getFontName(), Font.PLAIN, 10));
            labelFinalPointInfo3.setVisible(true);
            tripTrackingPanel.add(labelFinalPointInfo3);

            labelVehicle3 = new JLabel();
            labelVehicle3.setBounds((int) Math.floor(Main.getOnGoingTrips()[2].getxVehicle()), 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
            ImageIcon img3 = new ImageIcon(
                    getClass().getResource("../../images/" + selectedImageVehicle(Main.getOnGoingTrips()[2].getVehicle().getName()))
            );
            Image nuevo3 = img3.getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT);
            labelVehicle3.setHorizontalAlignment(SwingConstants.CENTER);
            labelVehicle3.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon render = new ImageIcon(nuevo3);
            labelVehicle3.setIcon(render);
            labelVehicle3.setVisible(true);
            vehicle3 = labelVehicle3.getBounds();
            highway3.add(labelVehicle3);

            labelCurrentInfo3 = new JLabel(
                    "<html>" +
                            "Recorrido " + Main.getOnGoingTrips()[2].getDistanceTraveled() +
                            "<br>" +
                            "Gasolina " + Main.getOnGoingTrips()[2].getVehicle().getGasoline() +
                            "</html>"
            );

            labelCurrentInfo3.setBounds((int) Math.floor(Main.getOnGoingTrips()[2].getxInfo()), 0, 100, 40);
            labelCurrentInfo3.setForeground(Color.black);
            labelCurrentInfo3.setFont(new Font(labelCurrentInfo3.getFont().getFontName(), Font.BOLD, 8));
            labelCurrentInfo3.setVisible(true);
            highway3.add(labelCurrentInfo3);
        } else {
            btnInitDriver3.setEnabled(false);
            btnReturn3.setEnabled(false);
        }
    }

    private String selectedImageVehicle(String vehicle){
        String imageName = "";

        if(vehicle.equals("Motocicleta 1") || vehicle.equals("Motocicleta 2") || vehicle.equals("Motocicleta 3"))
            imageName = "motorcycle.png";
        else if(vehicle.equals("Vehiculo estandar 1") || vehicle.equals("Vehiculo estandar 2") || vehicle.equals("Vehiculo estandar 3"))
            imageName = "standard_car.png";
        else if(vehicle.equals("Vehiculo premium 1") || vehicle.equals("Vehiculo premium 2") || vehicle.equals("Vehiculo premium 3"))
            imageName = "premium_car.png";

        return imageName;
    }

    public void cleanVehicle1Info(){
        btnInitDriver1.setEnabled(false);
        btnInitDriver1.removeMouseListener(this);
        btnReturn1.setEnabled(false);
        btnReturn1.removeMouseListener(this);
        btnResumeVehicle1.setEnabled(false);
        btnResumeVehicle1.removeMouseListener(this);

        labelInitialPointInfo1.setText("");
        labelInitialPointInfo1.setVisible(false);
        tripTrackingPanel.remove(labelInitialPointInfo1);

        labelFinalPointInfo1.setText("");
        labelFinalPointInfo1.setVisible(false);
        tripTrackingPanel.remove(labelFinalPointInfo1);

        labelVehicle1.setText("");
        labelVehicle1.setVisible(false);
        highway1.remove(labelVehicle1);

        labelCurrentInfo1.setText("");
        labelCurrentInfo1.setVisible(false);
        highway1.remove(labelCurrentInfo1);
    }

    public void cleanVehicle2Info(){
        btnInitDriver2.setEnabled(false);
        btnInitDriver2.removeMouseListener(this);
        btnReturn2.setEnabled(false);
        btnReturn2.removeMouseListener(this);
        btnResumeVehicle2.setEnabled(false);
        btnResumeVehicle2.removeMouseListener(this);

        labelInitialPointInfo2.setText("");
        labelInitialPointInfo2.setVisible(false);
        tripTrackingPanel.remove(labelInitialPointInfo2);

        labelFinalPointInfo2.setText("");
        labelFinalPointInfo2.setVisible(false);
        tripTrackingPanel.remove(labelFinalPointInfo2);

        labelVehicle2.setText("");
        labelVehicle2.setVisible(false);
        highway2.remove(labelVehicle2);

        labelCurrentInfo2.setText("");
        labelCurrentInfo2.setVisible(false);
        highway2.remove(labelCurrentInfo2);
    }

    public void cleanVehicle3Info(){
        btnInitDriver3.setEnabled(false);
        btnInitDriver3.removeMouseListener(this);
        btnReturn3.setEnabled(false);
        btnReturn3.removeMouseListener(this);
        btnResumeVehicle3.setEnabled(false);
        btnResumeVehicle3.removeMouseListener(this);

        labelInitialPointInfo3.setText("");
        labelInitialPointInfo3.setVisible(false);
        tripTrackingPanel.remove(labelInitialPointInfo3);

        labelFinalPointInfo3.setText("");
        labelFinalPointInfo3.setVisible(false);
        tripTrackingPanel.remove(labelFinalPointInfo3);

        labelVehicle3.setText("");
        labelVehicle3.setVisible(false);
        highway3.remove(labelVehicle3);

        labelCurrentInfo3.setText("");
        labelCurrentInfo3.setVisible(false);
        highway3.remove(labelCurrentInfo3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == btnGenerateTrip){
            TripsGenerateTripView tripsGenerateTripView = new TripsGenerateTripView();
            tripsGenerateTripView.setVisible(true);
            dispose();

        }else if (e.getSource() == closeLabel) {
            DashboardView dashboardView = new DashboardView();
            dashboardView.setVisible(true);
            dispose();
        } else if (e.getSource() == btnInitDriver1) {
            Main.goVehicle1(this, "go");
        } else if (e.getSource() == btnInitDriver2) {
            Main.goVehicle2(this, "go");
        } else if (e.getSource() == btnInitDriver3) {
            Main.goVehicle3(this, "go");
        } else if (e.getSource() == btnReturn1) {
            Main.returnVehicle1(this);

            btnReturn1.setEnabled(false);
            btnReturn1.removeMouseListener(this);
        } else if (e.getSource() == btnReturn2) {
            Main.returnVehicle2(this);

            btnReturn2.setEnabled(false);
            btnReturn2.removeMouseListener(this);
        } else if (e.getSource() == btnReturn3) {
            Main.returnVehicle3(this);

            btnReturn3.setEnabled(false);
            btnReturn3.removeMouseListener(this);
        } else if (e.getSource() == btnRefillTank1) {
            Main.refillTank1(this);
        } else if (e.getSource() == btnRefillTank2) {
            Main.refillTank2(this);
        } else if (e.getSource() == btnRefillTank3) {
            Main.refillTank3(this);
        } else if (e.getSource() == btnInitAllDrivers) {
            Main.goVehicle1(this, "go");
            Main.goVehicle2(this, "go");
            Main.goVehicle3(this, "go");
            btnReturn1.setEnabled(false);
            btnReturn1.removeMouseListener(this);

            btnReturn2.setEnabled(false);
            btnReturn2.removeMouseListener(this);

            btnReturn2.setEnabled(false);
            btnReturn2.removeMouseListener(this);
        } else if(e.getSource() == btnResumeVehicle1){
            Main.resumeVehicle1(this, Main.getOnGoingTrips()[0].getStatus());
        } else if(e.getSource() == btnResumeVehicle2){
            Main.resumeVehicle2(this, Main.getOnGoingTrips()[1].getStatus());
        } else if(e.getSource() == btnResumeVehicle3){
            Main.resumeVehicle3(this, Main.getOnGoingTrips()[2].getStatus());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
