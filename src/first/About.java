package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    JMenuItem home, dashboard, about, contact, logout;
    String loggedInUsername;

    About(String username) {
        this.loggedInUsername = username;

        setBounds(300, 100, 800, 600);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("first/icons/Home1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1500, 1000);
        add(image);

        JLabel text = new JLabel("Logged in as: " + loggedInUsername);
        text.setBounds(400, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.PLAIN, 24));
        text.setForeground(Color.WHITE);
        image.add(text);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 15, 1550, 60);
        mb.setBackground(Color.BLACK);
        image.add(mb);

        home = new JMenuItem("HOME");
        home.setBounds(0, 0, 100, 10);
        home.setForeground(Color.WHITE);
        home.setBackground(Color.BLACK);
        home.setFont(new Font("Tahoma", Font.PLAIN, 25));
        home.addActionListener(this);
        mb.add(home);

        dashboard = new JMenuItem("DASHBOARD");
        dashboard.setBounds(0, 0, 100, 10);
        dashboard.setForeground(Color.WHITE);
        dashboard.setBackground(Color.BLACK);
        dashboard.setFont(new Font("Tahoma", Font.PLAIN, 25));
        dashboard.addActionListener(this);
        mb.add(dashboard);

        about = new JMenuItem("ABOUT");
        about.setBounds(0, 0, 100, 10);
        about.setForeground(Color.WHITE);
        about.setBackground(Color.BLACK);
        about.setFont(new Font("Tahoma", Font.PLAIN, 25));
        about.addActionListener(this);
        mb.add(about);

        contact = new JMenuItem("CONTACT");
        contact.setBounds(0, 0, 200, 10);
        contact.setForeground(Color.WHITE);
        contact.setBackground(Color.BLACK);
        contact.setFont(new Font("Tahoma", Font.PLAIN, 25));
        contact.addActionListener(this);
        mb.add(contact);

        logout = new JMenuItem("LOGOUT");
        logout.setBounds(0, 0, 150, 10);
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 25));
        logout.addActionListener(this);
        mb.add(logout);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            openNewFrame(new Home());
        } else if (ae.getSource() == dashboard) {
            openNewFrame(new Dashboard());
        } else if (ae.getSource() == about) {
            openNewFrame(new About(loggedInUsername));
        } else if (ae.getSource() == contact) {
            openNewFrame(new Contact());
        } else if (ae.getSource() == logout) {
            openNewFrame(new InventoryManagementSystem());
        }
    }
    
    private void openNewFrame(JFrame newFrame) {
        if (isVisible()) {
            dispose(); // Close the current frame
        }
        newFrame.setVisible(true); // Open the new frame
    }
    
    private void disposeAndOpenNewFrame(JFrame newFrame) {
        if (isVisible()) {
            dispose(); // Close the current frame
        }
        newFrame.setVisible(true); // Open the new frame
    }

    public static void main(String[] args) {
        // Update this with the username from the Login page
        new About("exampleUsername");
    }
}


