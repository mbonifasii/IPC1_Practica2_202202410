package com.marcosbonifasi.views.trips;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TripsTrackingView extends JFrame implements MouseListener {

    private JPanel tripTrackingPanel;
    private JLabel closeLabel;
    private JLabel labelInitialPointInfo1;
    private JLabel labelFinalPointInfo1;
    private JLabel labelCurrentInfo1;
    private JLabel labelVehicle1;
    private JLabel labelVehicle2;
    private JLabel labelVehicle3;
    private JLabel labelInitialPointInfo2;
    private JLabel labelFinalPointInfo2;
    private JLabel labelCurrentInfo2;
    private JLabel labelInitialPointInfo3;
    private JLabel labelFinalPointInfo3;
    private JLabel labelCurrentInfo3;
    private JButton btnGenerateTrip;
    private JButton btnInitAllDrivers;
    private JButton btnInitDriver1;
    private JButton btnInitDriver2;
    private JButton btnInitDriver3;
    private JButton btnRefillTank1;
    private JButton btnRefillTank2;
    private JButton btnRefillTank3;
    private JButton btnReturn1;
    private JButton btnReturn2;
    private JButton btnReturn3;
    private Rectangle vehicle1;
    private Rectangle vehicle2;
    private Rectangle vehicle3;
    private JLabel highway1;
    private JLabel highway2;
    private JLabel highway3;

    public TripsTrackingView(){
        setSize(800, 620);
        setTitle("Viajes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initTripTrackingPanel();
        renderTrips();
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
        if(!Main.driversAvailable())
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

        btnInitAllDrivers = new JButton("Iniciar todos");
        btnInitAllDrivers.setBounds(410, 20, 150, 30);
        btnInitAllDrivers.setBackground(new Color(144, 219, 244));
        btnInitAllDrivers.setForeground(Color.white);
        btnInitAllDrivers.setFont(new Font(btnInitAllDrivers.getFont().getFontName(), Font.BOLD, 13));
        btnInitAllDrivers.setOpaque(true);
        btnInitAllDrivers.setBorderPainted(false);
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
        highway1.addMouseListener(this);
        tripTrackingPanel.add(highway1);

        btnInitDriver1 = new JButton("Iniciar");
        btnInitDriver1.setBounds(50, 200, 100, 30);
        btnInitDriver1.setBackground(new Color(128, 237, 153));
        btnInitDriver1.setForeground(Color.black);
        btnInitDriver1.setFont(new Font(btnInitDriver1.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver1.setOpaque(true);
        btnInitDriver1.setBorderPainted(false);
        btnInitDriver1.addMouseListener(this);
        tripTrackingPanel.add(btnInitDriver1);

        btnReturn1 = new JButton("Regresar");
        btnReturn1.setBounds(640, 200, 110, 30);
        btnReturn1.setBackground(new Color(11, 57, 84));
        btnReturn1.setForeground(Color.white);
        btnReturn1.setFont(new Font(btnReturn1.getFont().getFontName(), Font.BOLD, 13));
        btnReturn1.setOpaque(true);
        btnReturn1.setBorderPainted(false);
        btnReturn1.addMouseListener(this);
        tripTrackingPanel.add(btnReturn1);

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
        highway2.addMouseListener(this);
        tripTrackingPanel.add(highway2);

        btnInitDriver2 = new JButton("Iniciar");
        btnInitDriver2.setBounds(50, 360, 100, 30);
        btnInitDriver2.setBackground(new Color(128, 237, 153));
        btnInitDriver2.setForeground(Color.black);
        btnInitDriver2.setFont(new Font(btnInitDriver2.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver2.setOpaque(true);
        btnInitDriver2.setBorderPainted(false);
        btnInitDriver2.addMouseListener(this);
        tripTrackingPanel.add(btnInitDriver2);

        btnReturn2 = new JButton("Regresar");
        btnReturn2.setBounds(640, 360, 110, 30);
        btnReturn2.setBackground(new Color(11, 57, 84));
        btnReturn2.setForeground(Color.white);
        btnReturn2.setFont(new Font(btnReturn2.getFont().getFontName(), Font.BOLD, 13));
        btnReturn2.setOpaque(true);
        btnReturn2.setBorderPainted(false);
        btnReturn2.addMouseListener(this);
        tripTrackingPanel.add(btnReturn2);

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
        highway3.addMouseListener(this);
        tripTrackingPanel.add(highway3);

        btnInitDriver3 = new JButton("Iniciar");
        btnInitDriver3.setBounds(50, 530, 100, 30);
        btnInitDriver3.setBackground(new Color(128, 237, 153));
        btnInitDriver3.setForeground(Color.black);
        btnInitDriver3.setFont(new Font(btnInitDriver3.getFont().getFontName(), Font.BOLD, 13));
        btnInitDriver3.setOpaque(true);
        btnInitDriver3.setBorderPainted(false);
        btnInitDriver3.addMouseListener(this);
        tripTrackingPanel.add(btnInitDriver3);

        btnReturn3 = new JButton("Regresar");
        btnReturn3.setBounds(640, 530, 110, 30);
        btnReturn3.setBackground(new Color(11, 57, 84));
        btnReturn3.setForeground(Color.white);
        btnReturn3.setFont(new Font(btnReturn3.getFont().getFontName(), Font.BOLD, 13));
        btnReturn3.setOpaque(true);
        btnReturn3.setBorderPainted(false);
        btnReturn3.addMouseListener(this);
        tripTrackingPanel.add(btnReturn3);


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

        System.out.println("Rendering");

        // Render first trip
        if(Main.getOnGoingTrips()[0] != null){
            btnReturn1.setEnabled(false);

            labelInitialPointInfo1 = new JLabel(
                    "<html>" +
                    Main.getOnGoingTrips()[0].getVehicle().getName()+
                    "<br>" +
                    "DISTANCIA" +
                    "<br>" +
                    "Destino " + Main.getOnGoingTrips()[0].getFinalPoint()+
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

            labelVehicle1 = new JLabel();
            labelVehicle1.setBounds(600, 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
            ImageIcon img1 = new ImageIcon(
                getClass().getResource("../../images/" + selectedImageVehicle(Main.getOnGoingTrips()[0].getVehicle().getName()))
            );
            Image nuevo1 = img1.getImage().getScaledInstance(100, 80, Image.SCALE_DEFAULT);
            labelVehicle1.setHorizontalAlignment(SwingConstants.CENTER);
            labelVehicle1.setVerticalAlignment(SwingConstants.CENTER);
            ImageIcon render = new ImageIcon(nuevo1);
            labelVehicle1.setIcon(render);
            labelVehicle1.setVisible(true);
            vehicle1 = labelVehicle1.getBounds();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            labelVehicle1.setBorder(border);
            highway1.add(labelVehicle1);

        } else {
            btnInitDriver1.setEnabled(false);
            btnReturn1.setEnabled(false);
        }

        // Render second trip
        if(Main.getOnGoingTrips()[1] != null){
            btnReturn2.setEnabled(false);

            labelInitialPointInfo2 = new JLabel(
                    "<html>" +
                            Main.getOnGoingTrips()[1].getVehicle().getName()+
                            "<br>" +
                            "DISTANCIA" +
                            "<br>" +
                            "Destino " + Main.getOnGoingTrips()[1].getFinalPoint()+
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
            labelVehicle2.setBounds(600, 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
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
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            labelVehicle2.setBorder(border);
            highway2.add(labelVehicle2);
        } else {
            btnInitDriver2.setEnabled(false);
            btnReturn2.setEnabled(false);
        }

        // Render third trip
        if(Main.getOnGoingTrips()[2] != null){
            btnReturn3.setEnabled(false);

            labelInitialPointInfo3 = new JLabel(
                    "<html>" +
                            Main.getOnGoingTrips()[2].getVehicle().getName()+
                            "<br>" +
                            "DISTANCIA" +
                            "<br>" +
                            "Destino " + Main.getOnGoingTrips()[2].getFinalPoint()+
                            "</html>"
            );

            labelInitialPointInfo3.setBounds(50, 380, 150, 90);
            labelInitialPointInfo3.setFont(new Font(labelInitialPointInfo3.getFont().getFontName(), Font.PLAIN, 10));
            labelInitialPointInfo3.setVisible(true);
            tripTrackingPanel.add(labelInitialPointInfo3);

            labelFinalPointInfo3= new JLabel(
                    "<html>" +
                            "Inicio " + Main.getOnGoingTrips()[2].getStartingPoint()+
                            "</html>"
            );

            labelFinalPointInfo3.setBounds(650, 395, 150, 80);
            labelFinalPointInfo3.setFont(new Font(labelFinalPointInfo3.getFont().getFontName(), Font.PLAIN, 10));
            labelFinalPointInfo3.setVisible(true);
            tripTrackingPanel.add(labelFinalPointInfo3);

            labelVehicle3 = new JLabel();
            labelVehicle3.setBounds(600, 30, 100, 50); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
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
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            labelVehicle3.setBorder(border);
            highway3.add(labelVehicle3);
        } else {
            btnInitDriver3.setEnabled(false);
            btnReturn3.setEnabled(false);
        }
    }

    private String selectedImageVehicle(String vehicle){
        String imageName = "";

        if(vehicle.equals("Motocicleta 1") || vehicle.equals("Motocicleta 2") || vehicle.equals("Motocicleta 3")){
            imageName = "motorcicle.png";
        }else if(vehicle.equals("Vehiculo estandar 1") || vehicle.equals("Vehiculo estandar 2") || vehicle.equals("Vehiculo estandar 3")){
            imageName = "standard_car.png";
        } else if(vehicle.equals("Vehiculo premium 1") || vehicle.equals("Vehiculo premium 2") || vehicle.equals("Vehiculo premium 3")) {
            imageName = "premium_car.png";
        }

        return imageName;
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

    class BackgroundImage extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("../imgs/fondo.gif")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
