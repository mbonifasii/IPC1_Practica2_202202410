package com.marcosbonifasi.views;

import com.marcosbonifasi.views.routes.RoutesIndexView;
import com.marcosbonifasi.views.trips.TripsTrackingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DashboardView extends JFrame implements MouseListener {

    private JPanel panelDashboard;
    private JPanel panelCardTrips;
    private JPanel panelCardHistory;
    private JPanel panelCardRoutes;
    private JLabel labelTrips;
    private JLabel labelRoutes;
    private JLabel labelHistory;
    
    public DashboardView(){
        setSize(700, 500);
        setTitle("Dashbaord");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initDashboardPanel();
        initCardHistoryPanel();
        initCardRoutesPanel();
        initCardTripsPanel();
    }

    private void initDashboardPanel(){
        panelDashboard = new JPanel();
        panelDashboard.setBounds(0, 0, 800, 600);
        panelDashboard.setBackground(Color.decode("#F5F3F4"));
        panelDashboard.setLayout(null);

        getContentPane().add(panelDashboard);

        panelDashboard.setVisible(true);
    }
    
    private void initCardTripsPanel(){
        panelCardTrips = new JPanel();
        panelCardTrips.setBounds(100, 100, 230, 130);
        panelCardTrips.setBackground(Color.decode("#EF476F"));
        panelDashboard.add(panelCardTrips);

        labelTrips = new JLabel("              Viajes              ");
        labelTrips.setBounds(100, 100, 200, 50);
        labelTrips.setOpaque(true);
        labelTrips.setBackground(Color.decode("#BD0C36"));
        labelTrips.setForeground(Color.decode("#FFFFFF"));
        labelTrips.setFont(new Font(labelTrips.getFont().getFontName(), Font.PLAIN, 18));
        labelTrips.setVisible(true);

        panelCardTrips.add(labelTrips);

        // Events
        panelCardTrips.addMouseListener(this);

        panelCardTrips.setVisible(true);
    }
    private void initCardHistoryPanel(){
        panelCardHistory = new JPanel();
        panelCardHistory.setBounds(350, 100, 230, 130);
        panelCardHistory.setBackground(Color.decode("#06D6A0"));

        panelDashboard.add(panelCardHistory);

        labelHistory = new JLabel("            Historial            ");
        labelHistory.setBounds(100, 100, 200, 50);
        labelHistory.setOpaque(true);
        labelHistory.setBackground(Color.decode("#087457"));
        labelHistory.setForeground(Color.decode("#FFFFFF"));
        labelHistory.setFont(new Font(labelHistory.getFont().getFontName(), Font.PLAIN, 18));
        labelHistory.setVisible(true);
        panelCardHistory.add(labelHistory);

        // Events
        panelCardHistory.addMouseListener(this);

        panelCardHistory.setVisible(true);
    }
    private void initCardRoutesPanel(){
        panelCardRoutes = new JPanel();
        panelCardRoutes.setBounds(225, 250, 230, 130);
        panelCardRoutes.setBackground(Color.decode("#FFD166"));

        panelDashboard.add(panelCardRoutes);

        labelRoutes = new JLabel("              Rutas              ");
        labelRoutes.setBounds(100, 100, 200, 50);
        labelRoutes.setOpaque(true);
        labelRoutes.setBackground(Color.decode("#E9A300"));
        labelRoutes.setForeground(Color.decode("#FFFFFF"));
        labelRoutes.setFont(new Font(labelRoutes.getFont().getFontName(), Font.PLAIN, 18));
        labelRoutes.setVisible(true);
        panelCardRoutes.add(labelRoutes);

        // Events
        panelCardRoutes.addMouseListener(this);

        panelCardRoutes.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == panelCardTrips) {
            TripsTrackingView tripsTrackingView = new TripsTrackingView();
            tripsTrackingView.setVisible(true);
            dispose();

        }else if(e.getSource() == panelCardHistory) {

        }else if(e.getSource() == panelCardRoutes) {
            RoutesIndexView routesIndexView = new RoutesIndexView();
            routesIndexView.setVisible(true);
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

        if(e.getSource() == panelCardTrips)
            panelCardTrips.setBackground(Color.decode("#BD0C36"));
        else if(e.getSource() == panelCardHistory)
            panelCardHistory.setBackground(Color.decode("#087457"));
        else if(e.getSource() == panelCardRoutes)
            panelCardRoutes.setBackground(Color.decode("#E9A300"));
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if(e.getSource() == panelCardTrips)
            panelCardTrips.setBackground(Color.decode("#EF476F"));
        else if(e.getSource() == panelCardHistory)
            panelCardHistory.setBackground(Color.decode("#06D6A0"));
        else if(e.getSource() == panelCardRoutes)
            panelCardRoutes.setBackground(Color.decode("#FFD166"));
    }
}