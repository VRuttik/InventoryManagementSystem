package first;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

public class SignUp extends JFrame implements ActionListener {

	JTextField firstName, lastName, username, password, emailAddress, mobileNumber, address;
	JButton signUp, cancel;

	SignUp() {
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		// First Name
		JLabel firstLabel = new JLabel("First Name:");
		firstLabel.setBounds(40, 20, 120, 30);
		firstLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(firstLabel);

		firstName = new JTextField();
		firstName.setBounds(150, 20, 150, 30);
		add(firstName);

		// Last Name
		JLabel lastLabel = new JLabel("Last Name:");
		lastLabel.setBounds(40, 70, 100, 30);
		lastLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(lastLabel);

		lastName = new JTextField();
		lastName.setBounds(150, 70, 150, 30);
		add(lastName);

		// Username
		JLabel user = new JLabel("Username:");
		user.setBounds(40, 120, 100, 30);
		user.setFont(new Font("Arial", Font.BOLD, 18));
		add(user);

		username = new JTextField();
		username.setBounds(150, 120, 150, 30);
		add(username);

		// Password
		JLabel pass = new JLabel("Password:");
		pass.setBounds(40, 170, 100, 30);
		pass.setFont(new Font("Arial", Font.BOLD, 18));
		add(pass);

		password = new JPasswordField();
		password.setBounds(150, 170, 150, 30);
		add(password);

		// Email Address
		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setBounds(40, 220, 160, 30);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(emailLabel);

		emailAddress = new JTextField();
		emailAddress.setBounds(180, 220, 150, 30);
		add(emailAddress);

		// Mobile Number
		JLabel mobileLabel = new JLabel("Mobile Number:");
		mobileLabel.setBounds(40, 270, 160, 30);
		mobileLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(mobileLabel);

		mobileNumber = new JTextField();
		mobileNumber.setBounds(180, 270, 150, 30);
		add(mobileNumber);

		// Address
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBounds(40, 320, 100, 30);
		addressLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(addressLabel);

		address = new JTextField();
		address.setBounds(150, 320, 150, 30);
		add(address);

		signUp = new JButton("Sign Up");
		signUp.setBounds(40, 380, 120, 30);
		signUp.setBackground(Color.BLACK);
		signUp.setForeground(Color.WHITE);
		signUp.addActionListener(this);
		add(signUp);

		cancel = new JButton("Cancel");
		cancel.setBounds(180, 380, 120, 30);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		add(cancel);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(true);
				new InventoryManagementSystem();
			}
		});

		// Create a JLabel as a hyperlink
		JLabel signInLink = new JLabel("Already have an account? SignIn.");
		signInLink.setBounds(40, 420, 300, 30);
		signInLink.setFont(new Font("Arial", Font.PLAIN, 14));
		signInLink.setForeground(Color.BLUE);
		signInLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		signInLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				signInLinkClicked(evt);
			}
		});
		add(signInLink);

		setBounds(500, 200, 600, 500);
		setVisible(true);
	}

	private void signInLinkClicked(java.awt.event.MouseEvent evt) {
		// Open the login page when the link is clicked
		setVisible(false);
		new Login(); // Assuming you have a Login class for the login page
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == signUp) {
			String firstNameText = firstName.getText();
			String lastNameText = lastName.getText();
			String user = username.getText();
			String pass = password.getText();
			String email = emailAddress.getText();
			String mobile = mobileNumber.getText();
			String userAddress = address.getText();

			try {
				Conn c = new Conn();

				// Modify the query to insert data into the SignUp table
				String insertQuery = "INSERT INTO login (first_name, last_name, username, password, email, mobile, address) "
						+ "VALUES ('" + firstNameText + "', '" + lastNameText + "', '" + user + "', '" + pass + "', '"
						+ email + "', '" + mobile + "', '" + userAddress + "')";

				// Execute the insert query
				int rowsAffected = c.s.executeUpdate(insertQuery);

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Sign Up successful! Welcome, " + user + "!");
					setVisible(false);
					new Dashboard();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to sign up. Please try again.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getSource() == cancel) {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SignUp());
	}
}
