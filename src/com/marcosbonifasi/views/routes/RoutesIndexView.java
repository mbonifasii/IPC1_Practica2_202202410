package com.marcosbonifasi.views.routes;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.controllers.RoutesController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoutesIndexView extends JFrame implements MouseListener {

    private JPanel panelRoutes;
    private JButton btnLoadRoutes;
    private JButton btnEditRoute;
    private JTable routesTable;
    private DefaultTableModel routesModel;

    public RoutesIndexView(){
        setSize(700, 500);
        setTitle("Rutas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initRoutesPanel();
    }

    public void initRoutesPanel(){
        panelRoutes = new JPanel();
        panelRoutes.setBounds(0, 0, 800, 600);
        panelRoutes.setBackground(Color.decode("#FFFFFF"));
        panelRoutes.setLayout(null);

        getContentPane().add(panelRoutes);


        // Components
        btnLoadRoutes = new JButton("Cargar rutas (csv)");
        btnLoadRoutes.setBounds(50, 50, 170, 30);
        btnLoadRoutes.setBackground(new Color(163, 196, 243));
        btnLoadRoutes.setForeground(Color.white);
        btnLoadRoutes.setFont(new Font(btnLoadRoutes.getFont().getFontName(), Font.BOLD, 13));
        btnLoadRoutes.setOpaque(true);
        btnLoadRoutes.setBorderPainted(false);
        btnLoadRoutes.addMouseListener(this);
        panelRoutes.add(btnLoadRoutes);

        btnEditRoute = new JButton("Editar distancia");
        btnEditRoute.setBounds(500, 50, 150, 30);
        btnEditRoute.setBackground(new Color(144, 219, 244));
        btnEditRoute.setForeground(Color.white);
        btnEditRoute.setFont(new Font(btnEditRoute.getFont().getFontName(), Font.BOLD, 13));
        btnEditRoute.setOpaque(true);
        btnEditRoute.setBorderPainted(false);
        btnEditRoute.addMouseListener(this);
        panelRoutes.add(btnEditRoute);


        // Table for listing
        String[] columns = {"Id", "Inicio", "Fin", "Distancia (km)" };

        routesModel = new DefaultTableModel(RoutesController.getParsedData(), columns);

        routesTable = new JTable(routesModel);

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columns.length; i++) {
            routesTable.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }

        routesTable.setEnabled(false);
        Main.resizeColumnWidth(routesTable);

        JScrollPane sp1 = new JScrollPane(routesTable);
        sp1.setBounds(50, 150, 600, 250);
        sp1.setVisible(true);
        panelRoutes.add(sp1);


        panelRoutes.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
