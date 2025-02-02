package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {
    private JRadioButton r1, r2, r3, r4;
    private JCheckBox c1, c2, c3, c4, c5, c6, c7;
    private JButton submit, cancel;
    private final String formno;

    public Signup3(String formno) {
        super("APPLICATION FORM");

        this.formno = formno;

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image scaledIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(scaledIcon));
        image.setBounds(150, 5, 100, 100);
        add(image);

        JLabel title = new JLabel("Page 3: Account Details");
        title.setFont(new Font("Raleway", Font.BOLD, 20));
        title.setBounds(280, 40, 400, 40);
        add(title);

        JLabel formNumberLabel = new JLabel("Form No: " + formno);
        formNumberLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        formNumberLabel.setBounds(600, 90, 300, 30);
        add(formNumberLabel);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        accountTypeLabel.setBounds(100, 140, 200, 30);
        add(accountTypeLabel);

        r1 = new JRadioButton("Saving Account");
        r2 = new JRadioButton("Fixed Deposit Account");
        r3 = new JRadioButton("Current Account");
        r4 = new JRadioButton("Recurring Deposit Account");

        r1.setBackground(new Color(92, 199, 193, 255));
        r2.setBackground(new Color(92, 199, 193, 255));
        r3.setBackground(new Color(92, 199, 193, 255));
        r4.setBackground(new Color(92, 199, 193, 255));


        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(r1);
        accountTypeGroup.add(r2);
        accountTypeGroup.add(r3);
        accountTypeGroup.add(r4);

        r1.setBounds(100, 180, 200, 30);
        r2.setBounds(350, 180, 250, 30);
        r3.setBounds(100, 220, 250, 30);
        r4.setBounds(350, 220, 250, 30);

        add(r1);
        add(r2);
        add(r3);
        add(r4);

        JLabel cardLabel = new JLabel("Card Number: XXXX-XXXX-XXXX-4841");
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 15));
        cardLabel.setBounds(100, 300, 400, 30);
        add(cardLabel);

        JLabel pinLabel = new JLabel("PIN: XXXX");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 15));
        pinLabel.setBounds(100, 370, 200, 30);
        add(pinLabel);

        JLabel servicesLabel = new JLabel("Services Required:");
        servicesLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        servicesLabel.setBounds(100, 450, 200, 30);
        add(servicesLabel);

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email Alerts");
        c5 = new JCheckBox("Check Book");
        c6 = new JCheckBox("E-Statement");
        c7 = new JCheckBox("I hereby declare that the details provided are accurate.", true);

        c1.setBackground(new Color(92, 199, 193, 255));
        c2.setBackground(new Color(92, 199, 193, 255));
        c3.setBackground(new Color(92, 199, 193, 255));
        c4.setBackground(new Color(92, 199, 193, 255));
        c5.setBackground(new Color(92, 199, 193, 255));
        c6.setBackground(new Color(92, 199, 193, 255));
        c7.setBackground(new Color(92, 199, 193, 255));


        c1.setBounds(100, 500, 200, 30);
        c2.setBounds(350, 500, 200, 30);
        c3.setBounds(100, 550, 200, 30);
        c4.setBounds(350, 550, 200, 30);
        c5.setBounds(100, 600, 200, 30);
        c6.setBounds(350, 600, 200, 30);
        c7.setBounds(100, 680, 600, 20);

        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
        add(c6);
        add(c7);

        submit = new JButton("Submit");
        cancel = new JButton("Cancel");

        submit.setBounds(250, 720, 100, 30);
        cancel.setBounds(420, 720, 100, 30);

        submit.addActionListener(this);
        cancel.addActionListener(this);

        add(submit);
        add(cancel);

        getContentPane().setBackground(new Color(92, 199, 193, 255));
        setSize(850, 800);
        setLayout(null);
        setLocation(400, 20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // Validate account type selection
            if (!r1.isSelected() && !r2.isSelected() && !r3.isSelected() && !r4.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select an account type.");
                return;
            }

            String atype = r1.isSelected() ? "Saving Account" :
                    r2.isSelected() ? "Fixed Deposit Account" :
                            r3.isSelected() ? "Current Account" :
                                    "Recurring Deposit Account";

            // Ensure at least one service is selected
            if (!c1.isSelected() && !c2.isSelected() && !c3.isSelected() && !c4.isSelected() && !c5.isSelected() && !c6.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select at least one service.");
                return;
            }

            // Ensure user agrees to the declaration
            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "You must agree to the declaration before proceeding.");
                return;
            }

            // Generate random card number and PIN
            Random ran = new Random();
            String cardno = "" + Math.abs((ran.nextLong() % 90000000L) + 1409963000000000L);
            String pin = "" + Math.abs((ran.nextLong() % 9000L) + 1000L);

            // Get selected services
            String services = (c1.isSelected() ? "ATM CARD, " : "") +
                    (c2.isSelected() ? "Internet Banking, " : "") +
                    (c3.isSelected() ? "Mobile Banking, " : "") +
                    (c4.isSelected() ? "Email Alerts, " : "") +
                    (c5.isSelected() ? "Check Book, " : "") +
                    (c6.isSelected() ? "E-Statement" : "");

            try {
                Con con = new Con();
                con.statement.executeUpdate("INSERT INTO signup3 VALUES('" + formno + "', '" + atype + "', '" + cardno + "', '" + pin + "', '" + services + "')");
                con.statement.executeUpdate("INSERT INTO login VALUES('" + formno + "', '" + cardno + "', '" + pin + "')");

                JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\nPIN: " + pin);
                new Deposit(pin);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while processing your request.");
            }
        } else if (e.getSource() == cancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Signup3("");
    }
}
