package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTransaction extends JFrame implements ActionListener {
    JButton depositButton, withdrawButton, fastCashButton, miniStatementButton, pinChangeButton, balanceEnquiryButton, exitButton;
    String pin;

    MainTransaction(String pin) {
        this.pin = pin;
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledImage = icon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1550, 780);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("Please select your Transaction");
        titleLabel.setFont(new Font("System", Font.BOLD, 28));
        titleLabel.setBounds(430, 170, 700, 35);
        titleLabel.setForeground(Color.white);
        backgroundLabel.add(titleLabel);

        depositButton = new JButton("DEPOSIT");
        depositButton.setForeground(Color.white);
        depositButton.setBackground(new Color(65, 125, 128));
        depositButton.setBounds(410, 247, 150, 30);
        depositButton.addActionListener(this);
        backgroundLabel.add(depositButton);

        withdrawButton = new JButton("CASH WITHDRAWAL");
        withdrawButton.setForeground(Color.white);
        withdrawButton.setBackground(new Color(65, 125, 128));
        withdrawButton.setBounds(700, 247, 150, 30);
        withdrawButton.addActionListener(this);
        backgroundLabel.add(withdrawButton);

        fastCashButton = new JButton("FAST CASH");
        fastCashButton.setForeground(Color.white);
        fastCashButton.setBackground(new Color(65, 125, 128));
        fastCashButton.setBounds(410, 295, 150, 30);
        fastCashButton.addActionListener(this);
        backgroundLabel.add(fastCashButton);

        miniStatementButton = new JButton("MINI STATEMENT");
        miniStatementButton.setForeground(Color.white);
        miniStatementButton.setBackground(new Color(65, 125, 128));
        miniStatementButton.setBounds(700, 295, 150, 30);
        miniStatementButton.addActionListener(this);
        backgroundLabel.add(miniStatementButton);

        pinChangeButton = new JButton("PIN CHANGE");
        pinChangeButton.setForeground(Color.white);
        pinChangeButton.setBackground(new Color(65, 125, 128));
        pinChangeButton.setBounds(410, 340, 150, 30);
        pinChangeButton.addActionListener(this);
        backgroundLabel.add(pinChangeButton);

        balanceEnquiryButton = new JButton("BALANCE ENQUIRY");
        balanceEnquiryButton.setForeground(Color.white);
        balanceEnquiryButton.setBackground(new Color(65, 125, 128));
        balanceEnquiryButton.setBounds(700, 340, 150, 30);
        balanceEnquiryButton.addActionListener(this);
        backgroundLabel.add(balanceEnquiryButton);

        exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.white);
        exitButton.setBackground(new Color(65, 125, 128));
        exitButton.setBounds(700, 385, 150, 30);
        exitButton.addActionListener(this);
        backgroundLabel.add(exitButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainTransaction("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == withdrawButton) {
            new CashWithdrawal(pin);
            setVisible(false);
        } else if (e.getSource() == balanceEnquiryButton) {
            new BalanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource() == fastCashButton) {
            new FastCash(pin);
            setVisible(false);
        } else if (e.getSource() == pinChangeButton) {
            new ChangePin(pin);
            setVisible(false);
        } else if (e.getSource() == miniStatementButton) {
            new MiniStatement(pin);
        }
    }
}
