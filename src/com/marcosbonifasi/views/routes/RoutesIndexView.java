package com.marcosbonifasi.views.routes;

import com.marcosbonifasi.Main;
import com.marcosbonifasi.controllers.RoutesController;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class RoutesIndexView extends JFrame implements MouseListener {

    private JPanel panelRoutes;
    private JButton btnLoadRoutes;
    private JButton btnEditRoute;
    private JTable routesTable;
    private DefaultTableModel routesModel;
    private JFileChooser fileChooser;
    private File fileSelected;
    private JLabel closeLabel;

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
        panelRoutes.setBackground(Color.decode("#F5F3F4"));
        panelRoutes.setLayout(null);

        getContentPane().add(panelRoutes);


        // Components
        JLabel labelRoutesListing = new JLabel("Listado rutas");
        labelRoutesListing.setBounds(50, 60, 150, 30);
        labelRoutesListing.setOpaque(true);
        labelRoutesListing.setFont(new Font(labelRoutesListing.getFont().getFontName(), Font.PLAIN, 18));
        labelRoutesListing.setVisible(true);
        panelRoutes.add(labelRoutesListing);

        btnLoadRoutes = new JButton("Cargar rutas (csv)");
        btnLoadRoutes.setBounds(480, 60, 170, 30);
        btnLoadRoutes.setBackground(new Color(163, 196, 243));
        btnLoadRoutes.setForeground(Color.white);
        btnLoadRoutes.setFont(new Font(btnLoadRoutes.getFont().getFontName(), Font.BOLD, 13));
        btnLoadRoutes.setOpaque(true);
        btnLoadRoutes.setBorderPainted(false);
        btnLoadRoutes.addMouseListener(this);
        panelRoutes.add(btnLoadRoutes);

        btnEditRoute = new JButton("Editar distancia");
        btnEditRoute.setBounds(500, 100, 150, 30);
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
        sp1.setBounds(50, 160, 600, 250);
        sp1.setVisible(true);
        panelRoutes.add(sp1);

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
        panelRoutes.add(closeLabel);

        panelRoutes.setVisible(true);
    }

    private void chooseCSVFile() throws IOException {
        // Create a file chooser
        fileChooser = new JFileChooser();

        // Set a file filter if needed (optional)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files","csv");
        fileChooser.setFileFilter(filter);

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if a file was chosen
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            fileSelected = fileChooser.getSelectedFile();

            // Print the path of the selected file
            System.out.println("Selected File: " + fileSelected.getAbsolutePath());

            // Perform CSV read
            readCSV();
        } else {
            System.out.println("No file selected");
        }
    }

    private void readCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader(fileSelected.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Splitting the line by comma to get individual values
                String[] values = line.split(",");

                // We proceed to create the routes
                RoutesController routesController = new RoutesController(
                        values[0],
                        values[1],
                        Integer.parseInt(values[2])
                );

                routesController.create();
            }

            JOptionPane.showMessageDialog(null, "Rutas cargadas correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            refreshRoutesTable();
        } catch (IOException e) {
            System.out.println("SOMETHING WENT WRONG: \n" + e);
            e.printStackTrace();
        }
    }

    private void refreshRoutesTable(){
        String[] columns = {"Id", "Inicio", "Fin", "Distancia (km)" };

        routesModel.setRowCount(0);

        routesModel.setDataVector(RoutesController.getParsedData(), columns);

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columns.length; i++) {
            routesTable.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == btnLoadRoutes){
            try {
                chooseCSVFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnEditRoute) {
            RouteEditView routeEditView = new RouteEditView();
            routeEditView.setVisible(true);
            dispose();
        } else if (e.getSource() == closeLabel) {
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
