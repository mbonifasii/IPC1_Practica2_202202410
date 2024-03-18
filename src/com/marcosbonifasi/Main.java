package com.marcosbonifasi;


import com.marcosbonifasi.models.Route;
import com.marcosbonifasi.views.DashboardView;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Route> routes = new ArrayList<Route>();

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();
        dashboardView.setVisible(true);
    }

    public static ArrayList<Route> getRoutes(){
        return routes;
    }

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
