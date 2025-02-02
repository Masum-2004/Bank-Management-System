package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    private String pin;
    private TextField textField;
    private JButton depositButton, backButton;

    public Deposit(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1550, 830);
        add(background);

        JLabel label = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setBounds(460, 180, 400, 35);
        label.setForeground(Color.white);
        background.add(label);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.white);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(textField);

        depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(700, 362, 150, 35);
        depositButton.setBackground(new Color(65, 125, 128));
        depositButton.setForeground(Color.white);
        depositButton.addActionListener(this);
        background.add(depositButton);

        backButton = new JButton("BACK");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        background.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Deposit("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();

            if (e.getSource() == depositButton) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Con con = new Con();
                    con.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    setVisible(false);
                    new MainTransaction(pin);
                }
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new MainTransaction(pin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
