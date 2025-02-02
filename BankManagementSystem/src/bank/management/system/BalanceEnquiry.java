package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    private final JLabel balanceLabel;
    private final JButton backButton;
    private final String pin;

    public BalanceEnquiry(String pin) {
        this.pin = pin;

        // Background image setup
        ImageIcon atmImage = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledImage = atmImage.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 1550, 830);
        add(background);

        // Header label
        JLabel headerLabel = new JLabel("YOUR CURRENT BALANCE IS:");
        headerLabel.setFont(new Font("System", Font.BOLD, 16));
        headerLabel.setBounds(430, 180, 700, 35);
        headerLabel.setForeground(Color.WHITE);
        background.add(headerLabel);

        // Balance display label
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(430, 220, 400, 35);
        balanceLabel.setForeground(Color.WHITE);
        background.add(balanceLabel);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        background.add(backButton);

        // Fetch and display balance
        fetchAndDisplayBalance();

        // Frame settings
        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    private void fetchAndDisplayBalance() {
        int balance = 0;

        try (Con connection = new Con()) {
            // Query to fetch transactions
            ResultSet resultSet = connection.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int amount = Integer.parseInt(resultSet.getString("amount"));

                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

            // Update balance label
            balanceLabel.setText("Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while fetching balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new MainTransaction(pin); // Navigate back to main transaction screen
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
