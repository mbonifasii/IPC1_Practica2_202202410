package com.marcosbonifasi.views.routes;

import com.marcosbonifasi.controllers.RoutesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RouteEditView extends JFrame implements MouseListener {

    private JPanel panelRoute;
    private JLabel labelId;
    private JLabel labelDistance;
    private JTextField fieldId;
    private JTextField fieldDistance;
    private JButton btnAccept;
    private JButton btnCancel;
    
    public RouteEditView(){
        setSize(390, 300);
        setTitle("Editar distancia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initRoutePanel();
    }

    public void initRoutePanel(){
        panelRoute = new JPanel();
        panelRoute.setBounds(0, 0, 800, 600);
        panelRoute.setBackground(Color.decode("#F5F3F4"));
        panelRoute.setLayout(null);

        getContentPane().add(panelRoute);


        // Components
        labelId = new JLabel("Id");
        labelId.setBounds(50, 50, 80, 30);
        labelId.setFont(new Font(labelId.getFont().getFontName(), Font.PLAIN, 18));
        labelId.setVisible(true);
        panelRoute.add(labelId);
        fieldId = new JTextField();
        fieldId.setBounds(160, 50, 160, 30);
        fieldId.setVisible(true);
        panelRoute.add(fieldId);

        labelDistance = new JLabel("Distancia");
        labelDistance.setBounds(50, 100, 100, 30);
        labelDistance.setFont(new Font(labelId.getFont().getFontName(), Font.PLAIN, 18));
        labelDistance.setVisible(true);
        panelRoute.add(labelDistance);
        fieldDistance = new JTextField();
        fieldDistance.setBounds(160, 100, 160, 30);
        fieldDistance.setVisible(true);
        panelRoute.add(fieldDistance);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(50, 180, 130, 30);
        btnCancel.setBackground(new Color(255, 255, 255));
        btnCancel.setForeground(Color.black );
        btnCancel.setFont(new Font(btnCancel.getFont().getFontName(), Font.BOLD, 13));
        btnCancel.setOpaque(true);
        btnCancel.setBorderPainted(false);
        btnCancel.addMouseListener(this);
        panelRoute.add(btnCancel);

        btnAccept = new JButton("Aceptar");
        btnAccept.setBounds(190, 180, 130, 30);
        btnAccept.setBackground(new Color(163, 196, 243));
        btnAccept.setForeground(Color.white);
        btnAccept.setFont(new Font(btnAccept.getFont().getFontName(), Font.BOLD, 13));
        btnAccept.setOpaque(true);
        btnAccept.setBorderPainted(false);
        btnAccept.addMouseListener(this);
        panelRoute.add(btnAccept);


        panelRoute.setVisible(true);
    }

    private boolean isDistanceValid(){
        return !fieldDistance.getText().isEmpty() && Integer.parseInt(fieldDistance.getText()) > 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == btnCancel){
            RoutesIndexView routesIndexView = new RoutesIndexView();
            routesIndexView.setVisible(true);
            dispose();
        } else if (e.getSource() == btnAccept) {
            RoutesController routesController = new RoutesController(Integer.parseInt(fieldId.getText()));

            if(!isDistanceValid()){
                JOptionPane.showMessageDialog(null, "Distancia debe ser llena y > 0");
                return;
            }

            if(routesController.found()){
                routesController.update(
                        Integer.parseInt(fieldDistance.getText())
                );
                JOptionPane.showMessageDialog(null, "Actualizacion exitosa");

                RoutesIndexView routesIndexView = new RoutesIndexView();
                routesIndexView.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Ruta no encontrada");
            }
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
