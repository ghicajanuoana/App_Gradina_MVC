package View;

import javax.swing.JFrame;

import Presenter.PresenterLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin extends JFrame implements IViewLogin {
	JButton loginButton = new JButton("Login");
    JTextField usernameField = new JTextField();
    JTextField passwordField = new JPasswordField();
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    JLabel loginLabel = new JLabel("Sign In");
    JComboBox c;

    PresenterLogin prezentareLogin;

    public ViewLogin() {
        prezentareLogin = new PresenterLogin(this);
        loginLabel.setForeground(Color.DARK_GRAY);
        loginLabel.setBounds(171,29, 100, 25);
        loginLabel.setFont(new Font("Serif",Font.BOLD,20));
        usernameLabel.setBounds(26,78,75,25);
        passwordLabel.setBounds(26,125,75,25);
        messageLabel.setBounds(124,286,171,35);
        messageLabel.setFont(new Font(null, Font.ITALIC,18));
        messageLabel.setForeground(Color.green);

        usernameField.setBounds(111,78,200,25);
        passwordField.setBounds(111,125,200,25);

        loginButton.setBounds(148,250,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
            	prezentareLogin.login();}
        });

        String[] roles = {"administrator","angajat"};
        c = new JComboBox(roles);
        c.setBackground(Color.ORANGE);
        c.setBounds(111,186,200,25);

        getContentPane().add(usernameLabel);
        getContentPane().add(passwordLabel);
        getContentPane().add(messageLabel);
        getContentPane().add(usernameField);
        getContentPane().add(passwordField);
        getContentPane().add(loginButton);
        getContentPane().add(loginLabel);
        getContentPane().add(c);

        this.getContentPane().setBackground(new Color(224,224,224));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420,420);
        getContentPane().setLayout(null);
        
        JButton vizitatorBtn = new JButton("Vizitator");
        vizitatorBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		prezentareLogin.vizitatorButton();
        	}
        });
        vizitatorBtn.setBounds(148, 333, 100, 23);
        getContentPane().add(vizitatorBtn);
        this.setTitle("Log In");
        this.setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }
    public String getPassword() {
        return String.valueOf(passwordField.getText());
    }
    public JComboBox getComboBox() {
        return c;
    }
    public void setSuccessMessageLabel(String text) {
        messageLabel.setForeground(Color.pink);
        messageLabel.setText(text);
    }
    public void setFailMessageLabel(String text) {
        messageLabel.setForeground(Color.black);
        messageLabel.setText(text);
    }
}
