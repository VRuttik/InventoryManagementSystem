package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Contact extends JFrame implements ActionListener {

    JMenuItem home, dashboard, about, contact, logout;
    JTextField fullNameField, emailField;
    JTextArea messageArea;

    public Contact() {
        setBounds(300, 100, 800, 600);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("first/icons/Home1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1500, 1000);
        add(image);

        JLabel text = new JLabel("");
        text.setBounds(400, 80, 1000, 50);
        text.setFont(new Font("Tahoma", Font.PLAIN, 46));
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
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 25));
        logout.addActionListener(this);
        mb.add(logout);

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(50, 150, 100, 30);
        fullNameLabel.setForeground(Color.WHITE);
        image.add(fullNameLabel);

        fullNameField = new JTextField();
        fullNameField.setBounds(200, 150, 150, 30);
        image.add(fullNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 200, 100, 30);
        emailLabel.setForeground(Color.WHITE);
        image.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 200, 150, 30);
        image.add(emailField);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(50, 250, 100, 30);
        messageLabel.setForeground(Color.WHITE);
        image.add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setBounds(200, 250, 400, 150);
        messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        image.add(messageArea);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(200, 420, 100, 30);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        image.add(submitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            disposeAndOpenNewFrame(new Home());
        } else if (ae.getSource() == dashboard) {
            disposeAndOpenNewFrame(new Dashboard());
        } else if (ae.getSource() == about) {
            disposeAndOpenNewFrame(new About(getTitle()));
        } else if (ae.getSource() == contact) {
            disposeAndOpenNewFrame(new Contact());
        } else if (ae.getSource() == logout) {
            disposeAndOpenNewFrame(new InventoryManagementSystem());
        } else if (ae.getActionCommand().equals("Submit")) {
            saveContactDetails();
            JOptionPane.showMessageDialog(this, "Message submitted successfully!");
        }
    }

    private void disposeAndOpenNewFrame(JFrame newFrame) {
        if (isVisible()) {
            dispose(); // Close the current frame
        }
        newFrame.setVisible(true); // Open the new frame
    }

    private void saveContactDetails() {
        try {
            Conn c = new Conn();

            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String message = messageArea.getText();

            String query = "INSERT INTO Contact (FullName, Email, Message) VALUES (?, ?, ?)";
            PreparedStatement pstmt = c.c.prepareStatement(query);
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, message);

            pstmt.executeUpdate();

            // Clear fields after submission
            fullNameField.setText("");
            emailField.setText("");
            messageArea.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Contact();
    }
}
