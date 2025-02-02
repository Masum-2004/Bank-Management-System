package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    private JButton button100, button500, button1000, button2000, button5000, button10000, backButton;
    private String pin;
    FastCash(String pin){
        this.pin = pin;

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledImage = icon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0,0,1550, 780);
        add(backgroundLabel);

        JLabel titleLabel = new JLabel("SELECT WITHDRAWAL AMOUNT");
        titleLabel.setFont(new Font("System", Font.BOLD,22));
        titleLabel.setBounds(435, 170, 700, 35);
        titleLabel.setForeground(Color.white);
        backgroundLabel.add(titleLabel);

        button100 = new JButton("RS. 100");
        button100.setForeground(Color.white);
        button100.setBackground(new Color(65, 125, 128));
        button100.setBounds(410, 247, 150, 30);
        button100.addActionListener(this);
        backgroundLabel.add(button100);

        button500 = new JButton("RS. 500");
        button500.setForeground(Color.white);
        button500.setBackground(new Color(65, 125, 128));
        button500.setBounds(700, 247, 150, 30);
        button500.addActionListener(this);
        backgroundLabel.add(button500);

        button1000 = new JButton("RS. 1000");
        button1000.setForeground(Color.white);
        button1000.setBackground(new Color(65, 125, 128));
        button1000.setBounds(410, 295, 150, 30);
        button1000.addActionListener(this);
        backgroundLabel.add(button1000);

        button2000 = new JButton("RS. 2000");
        button2000.setForeground(Color.white);
        button2000.setBackground(new Color(65, 125, 128));
        button2000.setBounds(700, 295, 150, 30);
        button2000.addActionListener(this);
        backgroundLabel.add(button2000);

        button5000 = new JButton("RS. 5000");
        button5000.setForeground(Color.white);
        button5000.setBackground(new Color(65, 125, 128));
        button5000.setBounds(410, 340, 150, 30);
        button5000.addActionListener(this);
        backgroundLabel.add(button5000);

        button10000 = new JButton("RS. 10,000");
        button10000.setForeground(Color.white);
        button10000.setBackground(new Color(65, 125, 128));
        button10000.setBounds(700, 340, 150, 30);
        button10000.addActionListener(this);
        backgroundLabel.add(button10000);

        backButton = new JButton("BACK");
        backButton.setForeground(Color.white);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setBounds(700, 385, 150, 30);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    public static void main(String[] args){
        new FastCash("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            setVisible(false);
            new MainTransaction(pin);
        } else {
            String amount = ((JButton)e.getSource()).getText().substring(4);
            Con c = new Con();
            Date date = new Date();
            try {
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '"+pin+"'");
                int balance = 0;
                while (resultSet.next()){
                    if (resultSet.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                c.statement.executeUpdate("INSERT INTO bank VALUES('"+pin+"', '"+date+"', 'Withdrawal', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "RS. "+amount+" Debited Successfully");
            } catch (Exception ex){
                ex.printStackTrace();
            }
            setVisible(false);
            new MainTransaction(pin);
        }
    }
}
