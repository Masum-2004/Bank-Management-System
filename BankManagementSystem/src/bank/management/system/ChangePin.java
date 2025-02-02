package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePin extends JFrame implements ActionListener {
    private JButton changeButton, backButton;
    private JPasswordField newPinField, confirmPinField;
    private String currentPin;

    public ChangePin(String pin) {
        this.currentPin = pin;

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledImage = icon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, 1550, 830);
        add(background);

        JLabel titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setFont(new Font("System", Font.BOLD, 16));
        titleLabel.setBounds(430, 180, 400, 35);
        titleLabel.setForeground(Color.white);
        background.add(titleLabel);

        JLabel newPinLabel = new JLabel("NEW PIN:");
        newPinLabel.setFont(new Font("System", Font.BOLD, 16));
        newPinLabel.setBounds(430, 220, 400, 35);
        newPinLabel.setForeground(Color.white);
        background.add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setBackground(new Color(65, 125, 128));
        newPinField.setForeground(Color.white);
        newPinField.setBounds(430, 255, 200, 30);
        newPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(newPinField);

        JLabel confirmPinLabel = new JLabel("RE-ENTER NEW PIN:");
        confirmPinLabel.setFont(new Font("System", Font.BOLD, 16));
        confirmPinLabel.setBounds(430, 290, 400, 35);
        confirmPinLabel.setForeground(Color.white);
        background.add(confirmPinLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBackground(new Color(65, 125, 128));
        confirmPinField.setForeground(Color.white);
        confirmPinField.setBounds(430, 320, 200, 30);
        confirmPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(confirmPinField);

        changeButton = new JButton("CHANGE");
        changeButton.setBounds(700, 362, 150, 35);
        changeButton.setBackground(new Color(65, 125, 128));
        changeButton.setForeground(Color.white);
        changeButton.addActionListener(this);
        background.add(changeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        background.add(backButton);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ChangePin("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(null, "Entered PINs do not match");
                return;
            }

            if (e.getSource() == changeButton) {
                if (newPin.isEmpty() || confirmPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "PIN fields cannot be empty");
                    return;
                }
                if (newPin.length() != 4 || !newPin.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits");
                    return;
                }

                Con con = new Con();
                String updateBank = "UPDATE bank SET pin='" + newPin + "' WHERE pin='" + currentPin + "'";
                String updateLogin = "UPDATE login SET pin='" + newPin + "' WHERE pin='" + currentPin + "'";
                String updateSignup = "UPDATE signup3 SET pin='" + newPin + "' WHERE pin='" + currentPin + "'";

                con.statement.executeUpdate(updateBank);
                con.statement.executeUpdate(updateLogin);
                con.statement.executeUpdate(updateSignup);
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new MainTransaction(currentPin);
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new MainTransaction(currentPin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
