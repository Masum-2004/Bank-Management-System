package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CashWithdrawal extends JFrame implements ActionListener {
   private TextField amountField;
   private JButton withdrawButton, backButton;

    private String pin;
    CashWithdrawal(String pin){
        this.pin = pin;
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledImage = icon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel maxWithdrawalLabel = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        maxWithdrawalLabel.setFont(new Font("System", Font.BOLD, 16));
        maxWithdrawalLabel.setBounds(460, 180, 700, 35);
        maxWithdrawalLabel.setForeground(Color.white);
        backgroundLabel.add(maxWithdrawalLabel);

        JLabel enterAmountLabel = new JLabel("PLEASE ENTER YOUR AMOUNT");
        enterAmountLabel.setFont(new Font("System", Font.BOLD, 16));
        enterAmountLabel.setBounds(460, 220, 400, 35);
        enterAmountLabel.setForeground(Color.white);
        backgroundLabel.add(enterAmountLabel);

        amountField = new TextField();
        amountField.setBackground(new Color(65, 125, 128));
        amountField.setForeground(Color.white);
        amountField.setBounds(460, 260, 320, 25);
        amountField.setFont(new Font("Raleway", Font.BOLD, 22));
        backgroundLabel.add(amountField);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(700, 362, 150, 35);
        withdrawButton.setBackground(new Color(65, 125, 128));
        withdrawButton.setForeground(Color.white);
        withdrawButton.addActionListener(this);
        backgroundLabel.add(withdrawButton);

        backButton = new JButton("BACK");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0,0);
        setVisible(true);
    }

    public static void main(String[] args){
        new CashWithdrawal("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            try {
                String amount = amountField.getText();
                Date date = new Date();
                if (amountField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                } else {
                    Con c = new Con();
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin ='" + pin + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    c.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdraw', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "RS. " + amount + " Debited Successfully");
                    setVisible(false);
                    new MainTransaction(pin);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new MainTransaction(pin);
        }
    }
}
