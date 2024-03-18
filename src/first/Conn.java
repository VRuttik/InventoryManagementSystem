package first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

	Connection c;
	Statement s;
	public PreparedStatement pstmt;

	public Conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3307/inventory", "root", "Vruttik8460%");
			s = c.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// Handle exceptions more gracefully, log or display an error message
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// You can add testing code here to perform database operations
		Conn conn = new Conn();

		// Add your testing code here...
	}

	public Connection getConnection() {
		// Implement this method to return the actual Connection object
		return c;
	}

	// You can add other methods for database operations using the Statement object
}
