package view;

import javax.swing.*;
import model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPage() {
        setForeground(new Color(255, 255, 255));
        setTitle("Login Page");
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
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setForeground(new Color(30, 32, 34));
        usernameField.setBackground(new Color(255, 255, 255));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(30, 32, 34));
        passwordLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setForeground(new Color(30, 32, 34));
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginButton = new JButton("Log In");
        loginButton.setBackground(new Color(30, 32, 34));
        loginButton.setForeground(new Color(240, 245, 249));
        loginButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        registerButton = new JButton("Don't have an account? Register");
        registerButton.setBackground(new Color(30, 32, 34));
        registerButton.setForeground(new Color(221, 230, 237));
        registerButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("Log In");
        lblNewLabel_2.setForeground(new Color(30, 32, 34));
        lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(78)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        				.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        					.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
        			.addGap(66))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(245)
        			.addComponent(lblNewLabel_2)
        			.addContainerGap(247, Short.MAX_VALUE))
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
        			.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(42, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        loginButton.addActionListener(this::handleLogin);
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });

        setVisible(true);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = User.loginUser(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MovieMainMenu(username);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}

