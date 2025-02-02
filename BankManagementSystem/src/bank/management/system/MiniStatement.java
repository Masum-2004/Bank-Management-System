package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {
    private String pin;
    private JButton exitButton;

    public MiniStatement(String pin) {
        this.pin = pin;

        setSize(420, 650);
        setLocation(50, 50);
        getContentPane().setBackground(new Color(200, 230, 201));
        setLayout(null);

        JLabel bankLabel = new JLabel("MB Bank");
        bankLabel.setBounds(150, 10, 200, 30);
        bankLabel.setFont(new Font("System", Font.BOLD, 20));
        add(bankLabel);

        JLabel cardLabel = new JLabel();
        cardLabel.setBounds(20, 70, 350, 25);
        add(cardLabel);

        JLabel transactionLabel = new JLabel();
        transactionLabel.setBounds(20, 120, 360, 350);
        add(transactionLabel);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(30, 480, 350, 25);
        add(balanceLabel);

        try (Con con = new Con();
             PreparedStatement ps1 = con.connection.prepareStatement("SELECT * FROM login WHERE pin = ?");
             PreparedStatement ps2 = con.connection.prepareStatement("SELECT * FROM bank WHERE pin = ?")) {

            ps1.setString(1, pin);
            try (ResultSet rs1 = ps1.executeQuery()) {
                while (rs1.next()) {
                    cardLabel.setText("Card Number: " +
                            rs1.getString("card_number").substring(0, 4) +
                            "XXXXXXXX" +
                            rs1.getString("card_number").substring(12));
                }
            }

            int balance = 0;
            ps2.setString(1, pin);
            try (ResultSet rs2 = ps2.executeQuery()) {
                StringBuilder html = new StringBuilder("<html>");
                while (rs2.next()) {
                    html.append(rs2.getString("date")).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(rs2.getString("type")).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(rs2.getString("amount")).append("<br>");
                    if (rs2.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs2.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs2.getString("amount"));
                    }
                }
                html.append("</html>");
                transactionLabel.setText(html.toString());
                balanceLabel.setText("Your Total Balance is RS " + balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        exitButton = new JButton("Exit");
        exitButton.setBounds(150, 530, 100, 30);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
