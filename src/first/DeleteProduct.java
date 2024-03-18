package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class DeleteProduct extends JFrame implements ActionListener {

	JTextField tfproductName, tfproductCode, tfcategory, tfdescription, tfquantity, tfunitPrice, tfsupplier;
	JButton modify, cancel;
	private Object delete;
	private String productCode;

	DeleteProduct(String enteredProductCode) {
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

		modify = new JButton("Modify");
		modify.setBackground(Color.BLACK);
		modify.setForeground(Color.WHITE);
		modify.setBounds(60, 400, 150, 30);
		modify.addActionListener(this);
		add(modify);

		cancel = new JButton("Cancel");
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(260, 400, 150, 30);
		cancel.addActionListener(this);
		add(cancel);

		// Image on the right side
		ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("first/icons/tenth.jpg"));
		Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon modifyImage = new ImageIcon(image);
		JLabel imageLabel = new JLabel(modifyImage);
		imageLabel.setBounds(400, 30, 250, 250);
		add(imageLabel);

		this.productCode = enteredProductCode;

		// Code to delete the product from the database using productCode
		deleteProductFromDatabase(productCode);

		// Display a popup saying "Product successfully deleted"
		JOptionPane.showMessageDialog(this, "Product successfully deleted");

		// Navigate back to the Dashboard

		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 200, 700, 300);
		setVisible(true);
	}

	private void deleteProductFromDatabase(String productCode) {
		// Code to delete the product from the database based on the product code
		try {
			Conn conn = new Conn();
			String query = "DELETE FROM products WHERE productCode = ?";
			PreparedStatement pstmt = conn.c.prepareStatement(query);
			pstmt.setString(1, productCode);

			int rowsDeleted = pstmt.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Product with Product Code " + productCode + " deleted successfully.");
			} else {
				System.out.println("Product with Product Code " + productCode + " not found.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error deleting product from the database.");
		}
	}

	public static void main(String[] args) {
		// Corrected the syntax to pass the product code as a string argument
		new DeleteProduct("exampleProductCode");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// Rest of the DeleteProduct class remains the same...
}