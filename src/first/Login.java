package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

	JTextField username;
	JPasswordField password;
	JButton login, cancel;

	Login() {
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		JLabel user = new JLabel("Username:");
		user.setBounds(40, 20, 100, 30);
		user.setFont(new Font("Arial", Font.BOLD, 18));
		add(user);

		username = new JTextField();
		username.setBounds(150, 20, 150, 30);
		add(username);

		JLabel pass = new JLabel("Password:");
		pass.setBounds(40, 70, 100, 30);
		pass.setFont(new Font("Arial", Font.BOLD, 18));
		add(pass);

		password = new JPasswordField();
		password.setBounds(150, 70, 150, 30);
		add(password);

		login = new JButton("Login");
		login.setBounds(40, 150, 120, 30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		add(login);

		cancel = new JButton("Cancel");
		cancel.setBounds(180, 150, 120, 30);
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
		JLabel signUpLink = new JLabel("No account? SignUp");
		signUpLink.setBounds(40, 190, 150, 30);
		signUpLink.setFont(new Font("Arial", Font.PLAIN, 14));
		signUpLink.setForeground(Color.BLUE);
		signUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		signUpLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				signUpLinkClicked(evt);
			}
		});
		add(signUpLink);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("first/icons/second.jpg"));
		Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(350, 10, 200, 200);
		add(image);

		setBounds(500, 200, 600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == login) {
			String user = username.getText();
			String pass = new String(password.getPassword());

			try {
				Conn c = new Conn();

				String query = "select * from login where username = '" + user + "' and password = '" + pass + "'";

				ResultSet rs = c.s.executeQuery(query);

				if (rs.next()) {
					setVisible(false);
					new Home();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password.");
					setVisible(false);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getSource() == cancel) {
			setVisible(false);
		}
	}

	private void signUpLinkClicked(java.awt.event.MouseEvent evt) {
		setVisible(false);
		new SignUp();
	}

	public static void main(String[] args) {
		new Login();
	}
}
