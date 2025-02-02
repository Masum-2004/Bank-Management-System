package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JLabel label1, label2, label3;
    private JTextField textField2;
    private JPasswordField passwordField3;
    private JButton button1, button2, button3;

    public Login() {
        super("Bank Management System");

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image scaledIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(scaledIcon));
        image.setBounds(350, 10, 100, 100);
        add(image);

        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 36));
        label1.setBounds(230, 125, 400, 40);
        add(label1);

        label2 = new JLabel("CARD NO:");
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150, 190, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325, 190, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        label3 = new JLabel("PIN:");
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150, 250, 375, 30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325, 250, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("SIGN IN");
        button1.setBounds(300, 300, 100, 30);
        button1.setBackground(new Color(0, 102, 204));
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setBounds(430, 300, 100, 30);
        button2.setBackground(Color.RED);
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("SIGN UP");
        button3.setBounds(300, 350, 100, 30);
        button3.setBackground(new Color(34, 139, 34));
        button3.setForeground(Color.WHITE);
        button3.addActionListener(this);
        add(button3);

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image backgroundImg = backgroundIcon.getImage().getScaledInstance(850, 480, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundImg));
        background.setBounds(0, 0, 850, 480);
        add(background);

        setLayout(null);
        setSize(850, 480);
        setLocation(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            Con con = new Con();
            String cardno = textField2.getText();
            String pin = new String(passwordField3.getPassword());
            String query = "SELECT * FROM login WHERE card_number = '" + cardno + "' AND pin = '" + pin + "'";
            try {
                ResultSet rs = con.statement.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new MainTransaction(pin);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Card Number or PIN!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while signing in!");
            }
        } else if (e.getSource() == button2) {
            textField2.setText("");
            passwordField3.setText("");
        } else if (e.getSource() == button3) {
            new Signup();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
