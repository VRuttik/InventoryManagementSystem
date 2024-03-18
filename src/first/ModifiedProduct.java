package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModifiedProduct extends JFrame implements ActionListener {

	JTextField tfproductName, tfproductCode, tfcategory, tfdescription, tfquantity, tfunitPrice, tfsupplier, tfDate;
	JButton modify, cancel;

	ModifiedProduct(String enteredProductCode) {
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
		tfDate.setBounds(200, 380, 180, 30); // Adjusted Y-coordinate to avoid overlap
		add(tfDate);

		modify = new JButton("Modify");
		modify.setBackground(Color.BLACK);
		modify.setForeground(Color.WHITE);
		modify.setBounds(60, 430, 150, 30);
		modify.addActionListener(this);
		add(modify);

		cancel = new JButton("Cancel");
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(260, 430, 150, 30);
		cancel.addActionListener(this);
		add(cancel);

		ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("first/icons/tenth.jpg"));
		Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon modifyImage = new ImageIcon(image);
		JLabel imageLabel = new JLabel(modifyImage);
		imageLabel.setBounds(400, 30, 250, 250);
		add(imageLabel);

		// Fetch details from the database and populate text fields
		fetchProductDetails(enteredProductCode);

		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 200, 700, 500);
		setVisible(true);
	}

	private void fetchProductDetails(String productCode) {
		// Fetch details from the database based on the product code and populate the
		// text fields
		try {
			Conn conn = new Conn();
			String query = "SELECT * FROM products WHERE productCode = ?";
			PreparedStatement pstmt = conn.c.prepareStatement(query);
			pstmt.setString(1, productCode);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				tfproductCode.setText(rs.getString("productCode"));
				tfproductName.setText(rs.getString("productName"));
				tfcategory.setText(rs.getString("category"));
				tfdescription.setText(rs.getString("description"));
				tfquantity.setText(rs.getString("quantity"));
				tfunitPrice.setText(rs.getString("unitPrice"));
				tfsupplier.setText(rs.getString("supplier"));
				tfDate.setText(rs.getString("date"));
			} else {
				JOptionPane.showMessageDialog(this, "Product with Product Code " + productCode + " not found.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error fetching product details.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == modify) {
			// Handle modify button action
			// Connect to the database and perform the modification
			if (saveModifiedData()) {
				// If modification is successful, navigate back to the Dashboard
				new Dashboard();
				// Close the current ModifiedProduct window
				dispose();
			}
		} else if (e.getSource() == cancel) {
			// Navigate back to the Dashboard
			new Dashboard();
			// Close the current ModifiedProduct window
			dispose();
		}
	}

	// Add this method to save modified data to the database
	private boolean saveModifiedData() {
		// Implement the logic to save modified data to the database
		try {
			Conn conn = new Conn();
			String updateQuery = "UPDATE products SET productName=?, category=?, description=?, quantity=?, unitPrice=?, supplier=?, date=? WHERE productCode=?";
			PreparedStatement pstmt = conn.c.prepareStatement(updateQuery);

			pstmt.setString(1, tfproductName.getText());
			pstmt.setString(2, tfcategory.getText());
			pstmt.setString(3, tfdescription.getText());
			pstmt.setString(4, tfquantity.getText());
			pstmt.setString(5, tfunitPrice.getText());
			pstmt.setString(6, tfsupplier.getText());
			pstmt.setString(7, tfDate.getText());
			pstmt.setString(8, tfproductCode.getText());

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(this, "Product details updated successfully.");
				return true;
			} else {
				JOptionPane.showMessageDialog(this, "Failed to update product details.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving modified data to the database.");
		}
		return false;
	}

	public static void main(String[] args) {
		// Modify this line to pass the actual product code from where you create the
		// ModifiedProduct object
		new ModifiedProduct("exampleProductCode");
	}
}
