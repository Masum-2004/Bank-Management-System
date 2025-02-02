package bank.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Signup extends JFrame implements ActionListener {

    private  JTextField nameField, fatherNameField, emailField, addressField, cityField, pinCodeField, stateField;
    private  JRadioButton maleRadio, femaleRadio, marriedRadio, singleRadio, otherRadio;
    private JButton nextButton;
    private JDateChooser dateChooser;
    private final String formNumber;

    public Signup() {
        super("APPLICATION FORM");

        // Generate a unique form number
        Random random = new Random();
        long randomNumber = (random.nextLong() % 9000L) + 1000L;
        formNumber = " " + Math.abs(randomNumber);

        // Frame Properties
        setLayout(null);
        setSize(850, 800);
        setLocation(360, 40);
        getContentPane().setBackground(new Color(68, 218, 214));

        // Bank Logo
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image scaledIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel bankLogo = new JLabel(new ImageIcon(scaledIcon));
        bankLogo.setBounds(25, 10, 100, 100);
        add(bankLogo);

        // Application Title
        JLabel formTitle = new JLabel("APPLICATION FORM NO. " + formNumber);
        formTitle.setBounds(160, 20, 600, 40);
        formTitle.setFont(new Font("Raleway", Font.BOLD, 38));
        add(formTitle);

        JLabel pageTitle = new JLabel("Page 1: Personal Details");
        pageTitle.setFont(new Font("Raleway", Font.BOLD, 22));
        pageTitle.setBounds(290, 80, 600, 30);
        add(pageTitle);

        // Name Field
        addLabel("Full Name:", 100, 150);
        nameField = addTextField(300, 150);

        // Father's Name Field
        addLabel("Father's Name:", 100, 200);
        fatherNameField = addTextField(300, 200);

        // Date of Birth
        addLabel("Date of Birth:", 100, 250);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 250, 400, 30);
        dateChooser.setForeground(new Color(161, 232, 230));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        add(dateChooser);

        // Gender Selection
        addLabel("Gender:", 100, 300);
        maleRadio = addRadioButton("Male", 300, 300);
        femaleRadio = addRadioButton("Female", 450, 300);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Email Address
        addLabel("Email Address:", 100, 350);
        emailField = addTextField(300, 350);

        // Marital Status
        addLabel("Marital Status:", 100, 400);
        marriedRadio = addRadioButton("Married", 300, 400);
        singleRadio = addRadioButton("Single", 450, 400);
        otherRadio = addRadioButton("Other", 600, 400);
        ButtonGroup maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(marriedRadio);
        maritalStatusGroup.add(singleRadio);
        maritalStatusGroup.add(otherRadio);

        // Address
        addLabel("Address:", 100, 450);
        addressField = addTextField(300, 450);

        // City
        addLabel("City:", 100, 500);
        cityField = addTextField(300, 500);

        // Pin Code
        addLabel("Pin Code:", 100, 550);
        pinCodeField = addTextField(300, 550);

        // State
        addLabel("State:", 100, 600);
        stateField = addTextField(300, 600);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Raleway", Font.BOLD, 14));
        nextButton.setBackground(new Color(255, 255, 255, 216));
        nextButton.setForeground(Color.BLACK);
        nextButton.setBounds(620, 660, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().trim();
        String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
        String email = emailField.getText().trim();
        String maritalStatus = marriedRadio.isSelected() ? "Married" :
                (singleRadio.isSelected() ? "Single" : (otherRadio.isSelected() ? "Other" : ""));
        String address = addressField.getText().trim();
        String city = cityField.getText().trim();
        String pinCode = pinCodeField.getText().trim();
        String state = stateField.getText().trim();

        // Validation Checks
        if (name.isEmpty() || fatherName.isEmpty() || dob.isEmpty() || gender.isEmpty() ||
                email.isEmpty() || maritalStatus.isEmpty() || address.isEmpty() || city.isEmpty() ||
                pinCode.isEmpty() || state.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Invalid Email Address!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date selectedDate = dateFormat.parse(dob);
            Date currentDate = new Date();

            // Calculate the date 18 years ago
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.YEAR, -18);
            Date minDateAllowed = calendar.getTime();

            // Validate DOB
            if (selectedDate.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "Date of Birth cannot be in the future!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (selectedDate.after(minDateAllowed)) {
                JOptionPane.showMessageDialog(this, "You must be at least 18 years old!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Proceed to next step if validation passes
        try {
            Con con = new Con();
            String query = "INSERT INTO signup (formno, name, father_name, date_of_Birth, gender, email, marid_status, address, city, pincode, state) " +
                    "VALUES ('" + formNumber + "', '" + name + "', '" + fatherName + "', '" + dob + "', '" + gender + "', '" + email + "', " +
                    "'" + maritalStatus + "', '" + address + "', '" + city + "', '" + pinCode + "', '" + state + "')";
            con.statement.executeUpdate(query);
            new SignupStep2(formNumber);
            setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 20));
        label.setBounds(x, y, 200, 30);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Raleway", Font.BOLD, 14));
        textField.setBounds(x, y, 400, 30);
        add(textField);
        return textField;
    }

    private JRadioButton addRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Raleway", Font.BOLD, 14));
        radioButton.setBackground(new Color(68, 218, 214));
        radioButton.setBounds(x, y, 100, 30);
        add(radioButton);
        return radioButton;
    }

    public static void main(String[] args) {
        new Signup();
    }
}
