package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterPage() {
    	setForeground(new Color(255, 255, 255));
        setTitle("Create Account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 245, 249));
        panel.setBounds(0, 0, 584, 361);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(30, 32, 34));
        usernameLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        usernameField = new JTextField();
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(30, 32, 34));
        passwordLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(30, 32, 34));
        registerButton.setForeground(new Color(240, 245, 249));
        registerButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        backButton = new JButton("Have an account? Log In");
        backButton.setBackground(new Color(30, 32, 34));
        backButton.setForeground(new Color(240, 245, 249));
        backButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("Create Account");
        lblNewLabel_2.setForeground(new Color(30, 32, 34));
        lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(78)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        				.addComponent(backButton, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        					.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
        			.addGap(66))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(216, Short.MAX_VALUE)
        			.addComponent(lblNewLabel_2)
        			.addGap(202))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addComponent(lblNewLabel_2)
        			.addGap(18)
        			.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(42, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        registerButton.addActionListener(this::handleRegister);
        backButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        setVisible(true);
    }

    private void handleRegister(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 4 || password.length() < 4) {
            JOptionPane.showMessageDialog(this, "Username and password must be at least 4 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(username, password);
        if (User.registerUser(newUser)) {
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginPage();
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
