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
        setSize(800, 620);
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

        //  -- Components --


        // First highway
        // Carga la imagen
        ImageIcon imgHighway1 = new ImageIcon(getClass().getResource("../../images/highway.jpeg"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimensionHighway = imgHighway1.getImage().getScaledInstance(700, 80, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageHighwayIcon = new ImageIcon(imageDimensionHighway);
        // Crea un JLabel para mostrar la imagen
        JLabel highway1 = new JLabel(adjustedImageHighwayIcon);
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
        JLabel highway2 = new JLabel(adjustedImageHighway2Icon);
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
        JLabel highway3 = new JLabel(adjustedImageHighway3Icon);
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
