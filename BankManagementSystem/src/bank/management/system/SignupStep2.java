package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupStep2 extends JFrame implements ActionListener {
    JComboBox<String> religionDropdown, categoryDropdown, incomeDropdown, educationDropdown, occupationDropdown;
    JTextField panField, aadharField;
    JRadioButton seniorCitizenYes, seniorCitizenNo, existingAccountYes, existingAccountNo;
    JButton nextButton;
    String formNumber;

    public SignupStep2(String formNumber) {
        super("Application Form - Step 2");
        this.formNumber = formNumber;

        // Add bank logo at the top
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 5, 100, 100);
        add(image);

        setLayout(null);
        setSize(850, 750);
        setLocation(450, 80);
        getContentPane().setBackground(new Color(200, 220, 240));

        JLabel formNoLabel = new JLabel("Form No: " + formNumber);
        formNoLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        formNoLabel.setBounds(700, 45, 150, 30);
        add(formNoLabel);

        JLabel pageTitle = new JLabel("Step 2: Additional Details");
        pageTitle.setFont(new Font("Raleway", Font.BOLD, 22));
        pageTitle.setBounds(300, 30, 600, 40);
        add(pageTitle);

        JLabel religionLabel = new JLabel("Religion:");
        religionLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        religionLabel.setBounds(100, 120, 100, 30);
        add(religionLabel);

        String[] religions = {"Select", "Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionDropdown = new JComboBox<>(religions);
        religionDropdown.setBounds(350, 120, 320, 30);
        add(religionDropdown);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        categoryLabel.setBounds(100, 170, 100, 30);
        add(categoryLabel);

        String[] categories = {"Select", "General", "OBC", "SC", "ST", "Other"};
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.setBounds(350, 170, 320, 30);
        add(categoryDropdown);

        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        incomeLabel.setBounds(100, 220, 100, 30);
        add(incomeLabel);

        String[] incomes = {"Select", "Null", "<1,50,000>", "<2,50,000>", "<5,00,000>", "<10,00,000>", "<10,00,000>"};
        incomeDropdown = new JComboBox<>(incomes);
        incomeDropdown.setBounds(350, 220, 320, 30);
        add(incomeDropdown);

        JLabel educationLabel = new JLabel("Education:");
        educationLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        educationLabel.setBounds(100, 270, 150, 30);
        add(educationLabel);

        String[] educations = {"Select", "Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        educationDropdown = new JComboBox<>(educations);
        educationDropdown.setBounds(350, 270, 320, 30);
        add(educationDropdown);

        JLabel occupationLabel = new JLabel("Occupation:");
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        occupationLabel.setBounds(100, 320, 150, 30);
        add(occupationLabel);

        String[] occupations = {"Select", "Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupationDropdown = new JComboBox<>(occupations);
        occupationDropdown.setBounds(350, 320, 320, 30);
        add(occupationDropdown);

        JLabel panLabel = new JLabel("PAN Number:");
        panLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        panLabel.setBounds(100, 370, 150, 30);
        add(panLabel);

        panField = new JTextField();
        panField.setBounds(350, 370, 320, 30);
        add(panField);

        JLabel aadharLabel = new JLabel("Aadhar Number:");
        aadharLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        aadharLabel.setBounds(100, 420, 150, 30);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(350, 420, 320, 30);
        add(aadharField);

        JLabel seniorCitizenLabel = new JLabel("Senior Citizen:");
        seniorCitizenLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        seniorCitizenLabel.setBounds(100, 470, 150, 30);
        add(seniorCitizenLabel);

        seniorCitizenYes = new JRadioButton("Yes");
        seniorCitizenNo = new JRadioButton("No");

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorCitizenYes);
        seniorGroup.add(seniorCitizenNo);

        seniorCitizenYes.setBounds(350, 470, 100, 30);
        seniorCitizenNo.setBounds(460, 470, 100, 30);
        add(seniorCitizenYes);
        add(seniorCitizenNo);

        JLabel existingAccountLabel = new JLabel("Existing Account:");
        existingAccountLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        existingAccountLabel.setBounds(100, 520, 200, 30);
        add(existingAccountLabel);

        existingAccountYes = new JRadioButton("Yes");
        existingAccountNo = new JRadioButton("No");

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(existingAccountYes);
        accountGroup.add(existingAccountNo);

        existingAccountYes.setBounds(350, 520, 100, 30);
        existingAccountNo.setBounds(460, 520, 100, 30);
        add(existingAccountYes);
        add(existingAccountNo);

        nextButton = new JButton("Next");
        nextButton.setBounds(570, 640, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (religionDropdown.getSelectedIndex() == 0 || categoryDropdown.getSelectedIndex() == 0 ||
                incomeDropdown.getSelectedIndex() == 0 || educationDropdown.getSelectedIndex() == 0 ||
                occupationDropdown.getSelectedIndex() == 0 || panField.getText().trim().isEmpty() ||
                aadharField.getText().trim().isEmpty() || (!seniorCitizenYes.isSelected() && !seniorCitizenNo.isSelected()) ||
                (!existingAccountYes.isSelected() && !existingAccountNo.isSelected())) {

            JOptionPane.showMessageDialog(null, "All fields must be filled!");
            return;
        }

        if (!panField.getText().matches("[A-Z0-9]{10}")) {
            JOptionPane.showMessageDialog(null, "Invalid PAN Number! It must be exactly 10 alphanumeric characters.");
            return;
        }

        if (!aadharField.getText().matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null, "Invalid Aadhar Number! It must be exactly 12 digits.");
            return;
        }

        try {
            Con connection = new Con();
            String query = "INSERT INTO signup2 (formno, religion, category, income, education, occupation, pan_no, aadhar_no, seniorcitizen, existing_account) " +
                    "VALUES ('" + formNumber + "', '" + religionDropdown.getSelectedItem() + "', '" + categoryDropdown.getSelectedItem() + "', '" +
                    incomeDropdown.getSelectedItem() + "', '" + educationDropdown.getSelectedItem() + "', '" + occupationDropdown.getSelectedItem() +
                    "', '" + panField.getText() + "', '" + aadharField.getText() + "', '" + (seniorCitizenYes.isSelected() ? "Yes" : "No") +
                    "', '" + (existingAccountYes.isSelected() ? "Yes" : "No") + "')";

            connection.statement.executeUpdate(query);
            new Signup3(formNumber);
            setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignupStep2("");
    }
}
