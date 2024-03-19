package com.marcosbonifasi.views.trips;

import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TripsTrackingView extends JFrame implements MouseListener {

    private JPanel tripTrackingPanel;
    private JLabel closeLabel;
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

    public TripsTrackingView(){
        setSize(700, 500);
        setTitle("Viajes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initTripTrackingPanel();
    }

    private void initTripTrackingPanel(){
        tripTrackingPanel = new JPanel();
        tripTrackingPanel.setBounds(0, 0, 800, 600);
        tripTrackingPanel.setBackground(Color.decode("#F5F3F4"));
        tripTrackingPanel.setLayout(null);

        getContentPane().add(tripTrackingPanel);

        // Components



        // Carga la imagen
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("../../images/close.png"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimension = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        // Crea un JLabel para mostrar la imagen
        closeLabel = new JLabel(adjustedImageIcon);
        closeLabel.setBounds(630, 10, 20, 20); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        closeLabel.addMouseListener(this);
        tripTrackingPanel.add(closeLabel);

        tripTrackingPanel.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(false){

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
}
