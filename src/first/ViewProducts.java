package first;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ViewProducts extends JFrame {
    JTable table;
    DefaultTableModel model;

    public ViewProducts() {
        setTitle("View Products");
        setSize(800, 400);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        model.addColumn("Product Code");
        model.addColumn("Product Name");
        model.addColumn("Category");
        model.addColumn("Description");
        model.addColumn("Quantity");
        model.addColumn("Unit Price");
        model.addColumn("Supplier");

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("productCode"),
                        rs.getString("productName"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("quantity"),
                        rs.getString("unitPrice"),
                        rs.getString("supplier")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewProducts();
    }
}


