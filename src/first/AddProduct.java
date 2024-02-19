package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class AddProduct extends JFrame implements ActionListener {
	
	JTextField tfproductName, tfproductCode, tfcategory, tfdescription, tfquantity, tfunitPrice, tfsupplier,tfDate;
	JButton submit, cancel;

	AddProduct() {
		setLayout(null);

		JLabel lblproductName = new JLabel("Product Name");
		lblproductName.setBounds(60, 30, 120, 30);
		lblproductName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblproductName);

		tfproductName = new JTextField();
		tfproductName.setBounds(200, 30, 150, 30);
		add(tfproductName);

		JLabel lblproductCode = new JLabel("Product Code");
		lblproductCode.setBounds(60, 80, 120, 30);
		lblproductCode.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblproductCode);

		tfproductCode = new JTextField();
		tfproductCode.setBounds(200, 80, 150, 30);
		add(tfproductCode);

		JLabel lblcategory = new JLabel("Category");
		lblcategory.setBounds(60, 130, 120, 30);
		lblcategory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblcategory);

		tfcategory = new JTextField();
		tfcategory.setBounds(200, 130, 150, 30);
		add(tfcategory);

		JLabel lbldescription = new JLabel("Description");
		lbldescription.setBounds(60, 180, 120, 30);
		lbldescription.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lbldescription);

		tfdescription = new JTextField();
		tfdescription.setBounds(200, 180, 150, 30);
		add(tfdescription);

		JLabel lblquantity = new JLabel("Quantity");
		lblquantity.setBounds(60, 230, 120, 30);
		lblquantity.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblquantity);

		tfquantity = new JTextField();
		tfquantity.setBounds(200, 230, 150, 30);
		add(tfquantity);

		JLabel lblunitPrice = new JLabel("Unit Price");
		lblunitPrice.setBounds(60, 280, 120, 30);
		lblunitPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblunitPrice);

		tfunitPrice = new JTextField();
		tfunitPrice.setBounds(200, 280, 150, 30);
		add(tfunitPrice);

		JLabel lblsupplier = new JLabel("Supplier");
		lblsupplier.setBounds(60, 330, 120, 30);
		lblsupplier.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblsupplier);

		tfsupplier = new JTextField();
		tfsupplier.setBounds(200, 330, 150, 30);
		add(tfsupplier);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(60, 380, 120, 30);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblDate);

		tfDate = new JTextField();
		tfDate.setBounds(200, 380, 180, 30);  // Adjusted Y-coordinate to avoid overlap
		add(tfDate);

		submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(60, 430, 150, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(260, 430, 150, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("first/icons/tenth.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        ImageIcon productImage = new ImageIcon(image);
        JLabel imageLabel = new JLabel(productImage);
        imageLabel.setBounds(500, 30, 250, 250);
        add(imageLabel);

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 500); // Adjusted width for better visibility
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            addProductToDatabase();
        } else if (e.getSource() == cancel) {
            // Handle cancel button action if needed
        }
    }

    private void addProductToDatabase() {
        String productName = tfproductName.getText();
        String productCode = tfproductCode.getText();
        String category = tfcategory.getText();
        String description = tfdescription.getText();
        String quantity = tfquantity.getText();
        String unitPrice = tfunitPrice.getText();
        String supplier = tfsupplier.getText();
        String date = tfDate.getText();

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO products (productCode, productName, category, description, quantity, unitPrice, supplier, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, productCode);
            pstmt.setString(2, productName);
            pstmt.setString(3, category);
            pstmt.setString(4, description);
            pstmt.setString(5, quantity);
            pstmt.setString(6, unitPrice);
            pstmt.setString(7, supplier);
            pstmt.setString(8, date);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product added successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding product to the database");
        }
    }

    public static void main(String[] args) {
        new AddProduct();
    }
}