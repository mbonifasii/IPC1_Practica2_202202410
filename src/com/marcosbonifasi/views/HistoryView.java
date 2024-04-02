package com.marcosbonifasi.views;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.controllers.HistoriesController;
import com.marcosbonifasi.controllers.RoutesController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HistoryView extends JFrame implements MouseListener {


    private JPanel panelHistory;
    private JLabel closeLabel;
    private DefaultTableModel historyModel;
    private JTable historyTable;
    
    public HistoryView(){
        setSize(900, 500);
        setTitle("Historial");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // do not modify the size

        initHistoryPanel();
    }

    private void initHistoryPanel(){
        panelHistory = new JPanel();
        panelHistory.setBounds(0, 0, 700, 500);
        panelHistory.setBackground(Color.decode("#F5F3F4"));
        panelHistory.setLayout(null);

        getContentPane().add(panelHistory);

        // Components
        JLabel labelRoutesListing = new JLabel("Listado historial");
        labelRoutesListing.setBounds(50, 30, 150, 30);
        labelRoutesListing.setOpaque(true);
        labelRoutesListing.setFont(new Font(labelRoutesListing.getFont().getFontName(), Font.PLAIN, 18));
        labelRoutesListing.setVisible(true);
        panelHistory.add(labelRoutesListing);


        // Table for listing
        String[] columns = {"Id", "Fecha y hora de inicio", "Fecha y hora de fin", "Distancia (km)", "Vehiculo", "Gasolina consumida" };

        historyModel = new DefaultTableModel(HistoriesController.getParsedData(), columns);

        historyTable = new JTable(historyModel);

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columns.length; i++) {
            historyTable.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }

        historyTable.setEnabled(false);
        Main.resizeColumnWidth(historyTable);

        JScrollPane sp1 = new JScrollPane(historyTable);
        sp1.setBounds(50, 90, 800, 350);
        sp1.setVisible(true);
        panelHistory.add(sp1);
        
        
        // Carga la imagen
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/close.png"));
        // Ajusta el tamaño de la imagen (puedes cambiar los valores según tus necesidades)
        Image imageDimension = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Crea un nuevo ImageIcon con la imagen ajustada
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        // Crea un JLabel para mostrar la imagen
        closeLabel = new JLabel(adjustedImageIcon);
        closeLabel.setBounds(830, 10, 20, 20); // (x, y, width, height) aqui el ancho y la altura deben ser las mismas que cuando redimensionamos
        closeLabel.addMouseListener(this);
        panelHistory.add(closeLabel);


        panelHistory.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == closeLabel){
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
