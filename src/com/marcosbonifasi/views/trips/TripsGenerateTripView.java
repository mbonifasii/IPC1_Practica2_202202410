package com.marcosbonifasi.views.trips;

import com.marcosbonifasi.controllers.RoutesController;
import com.marcosbonifasi.controllers.VehiclesController;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TripsGenerateTripView extends JFrame implements MouseListener {

    private JPanel generatePanelTrip;
    private JComboBox comboStartingPoints;
    private JComboBox comboFinalPoints;
    private JComboBox comboVehicles;
    private JLabel closeLabel;
    private JLabel labelStartingPoint;
    private JLabel labelFinalPoint;
    private JLabel labelVehicle;
    private JButton btnGenerateTrip;
    private String selectedStartingPoint;
    private String selectedFinalPoint;
    private String selectedVehicle;

    public TripsGenerateTripView(){
        setSize(500, 400);
        setTitle("Generar Viaje");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initGenerateTripPanel();
    }

    private void initGenerateTripPanel(){
        selectedStartingPoint = "";
        selectedFinalPoint = "";
        selectedVehicle = "";

        generatePanelTrip = new JPanel();
        generatePanelTrip.setBounds(0, 0, 500, 400);
        generatePanelTrip.setBackground(Color.decode("#F5F3F4"));
        generatePanelTrip.setLayout(null);

        getContentPane().add(generatePanelTrip);

        // -- Components --
        labelStartingPoint = new JLabel("Punto inicial: ");
        labelStartingPoint.setBounds(50, 50, 150, 30);
        labelStartingPoint.setFont(new Font(labelStartingPoint.getFont().getFontName(), Font.PLAIN, 14));
        labelStartingPoint.setVisible(true);
        generatePanelTrip.add(labelStartingPoint);
        comboStartingPoints = new JComboBox(RoutesController.getPoints(""));
        comboStartingPoints.setBounds(50,90, 150, 30);
        comboStartingPoints.setVisible(true);
        comboStartingPoints.addMouseListener(this);
        generatePanelTrip.add(comboStartingPoints);
        comboStartingPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> cb = (JComboBox<?>) e.getSource();
                selectedStartingPoint = (String) cb.getSelectedItem();

                if(selectedStartingPoint != null){
                    comboFinalPoints.removeItem(selectedStartingPoint);
                }
            }
        });

        labelFinalPoint = new JLabel("Punto final: ");
        labelFinalPoint.setBounds(270, 50, 120, 30);
        labelFinalPoint.setFont(new Font(labelFinalPoint.getFont().getFontName(), Font.PLAIN, 14));
        labelFinalPoint.setVisible(true);
        generatePanelTrip.add(labelFinalPoint);
        comboFinalPoints = new JComboBox(RoutesController.getPoints(""));
        comboFinalPoints.setBounds(270,90, 150, 30);
        comboFinalPoints.setVisible(true);
        comboFinalPoints.addMouseListener(this);
        generatePanelTrip.add(comboFinalPoints);
        comboFinalPoints.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> cb = (JComboBox<?>) e.getSource();
                selectedFinalPoint = (String) cb.getSelectedItem();

                if(selectedFinalPoint != null){
                    comboStartingPoints.removeItem(selectedFinalPoint);
                }
            }
        });

        labelVehicle = new JLabel("Vehiculos: ");
        labelVehicle.setBounds(50, 160, 150, 30);
        labelVehicle.setFont(new Font(labelVehicle.getFont().getFontName(), Font.PLAIN, 14));
        labelVehicle.setVisible(true);
        generatePanelTrip.add(labelVehicle);
        comboVehicles = new JComboBox(VehiclesController.getVehicles(""));
        comboVehicles.setBounds(50,200, 150, 30);
        comboVehicles.setVisible(true);
        comboVehicles.addMouseListener(this);
        generatePanelTrip.add(comboVehicles);
        comboVehicles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<?> cb = (JComboBox<?>) e.getSource();
                selectedVehicle = (String) cb.getSelectedItem();
            }
        });

        btnGenerateTrip = new JButton("Generar viaje");
        btnGenerateTrip.setBounds(50, 280, 370, 30);
        btnGenerateTrip.setBackground(new Color(144, 219, 244));
        btnGenerateTrip.setForeground(Color.white);
        btnGenerateTrip.setFont(new Font(btnGenerateTrip.getFont().getFontName(), Font.BOLD, 13));
        btnGenerateTrip.setOpaque(true);
        btnGenerateTrip.setBorderPainted(false);
        btnGenerateTrip.addMouseListener(this);
        generatePanelTrip.add(btnGenerateTrip);

        JLabel labelNoDriversAvailable = new JLabel("No hay pilotos disponibles :(");
        labelNoDriversAvailable.setBounds(150, 310, 200, 30);
        labelNoDriversAvailable.setForeground(Color.red);
        labelNoDriversAvailable.setFont(new Font(labelNoDriversAvailable.getFont().getFontName(), Font.PLAIN, 12));
        labelNoDriversAvailable.setVisible(true);
        generatePanelTrip.add(labelNoDriversAvailable);



        // Carga la imagen
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("../../images/close.png"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimension = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        // Crea un JLabel para mostrar la imagen
        closeLabel = new JLabel(adjustedImageIcon);
        closeLabel.setBounds(430, 10, 20, 20); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        closeLabel.addMouseListener(this);
        generatePanelTrip.add(closeLabel);

        generatePanelTrip.setVisible(true);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(false){

        }else if (e.getSource() == closeLabel) {
            TripsTrackingView tripsTrackingView = new TripsTrackingView();
            tripsTrackingView.setVisible(true);
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
}
