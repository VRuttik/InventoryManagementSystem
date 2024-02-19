package first;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementSystem extends JFrame {

    InventoryManagementSystem() {
        setSize(1366, 565);
        setLocation(100, 100);
        setLayout(null);

        // Replace "icons/first.jpg" with the correct path to your image
        // Make sure the image file is in the correct location
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("first/icons/Home1.jpg"));

        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 1065);
        add(image);
        
        JLabel text = new JLabel("<html>INVENTORY MANAGEMENT<br>SYSTEM</html>");
        text.setBounds(60, 330, 600, 100);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 40));
        image.add(text);
        
        JButton next = new JButton("Sign In");
        next.setBounds(60, 450, 150, 50);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Handle the button click event here
            	 setVisible(false);
          	   new Login();
            }
        });
        
        next.setFont(new Font("serif", Font.PLAIN, 20));
        image.add(next);
        
        JButton next1 = new JButton("Sign Up");
        next1.setBounds(220, 450, 150, 50);
        next1.setBackground(Color.WHITE);
        next1.setForeground(Color.BLACK);
        
        next1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Handle the button click event here
            	 setVisible(false);
          	   new SignUp();
            }
        });

        next1.setFont(new Font("serif", Font.PLAIN, 20));
        image.add(next1);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryManagementSystem());
    }
}
