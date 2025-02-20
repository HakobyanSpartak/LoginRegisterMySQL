package GUIs;

import DB.MyJDBC;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends Form {
    public RegisterFormGUI() {
        super("Registration");
        addGuiComponents();
    }

    private void addGuiComponents () {

        // create Register page label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 45));
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        add(registerLabel);

        // create username input label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 300, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        add(usernameLabel);

        // create username input area
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 180, 450,55);
        usernameField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // for fun
        add(usernameField);

        // create password input label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 280, 300, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        add(passwordLabel);

        // create password input area
        JTextField passwordField = new JTextField();
        passwordField.setBounds(30, 320, 450,55);
        passwordField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        add(passwordField);

        // create re-enter the password input label
        JLabel reEnterPasswordLabel = new JLabel("Re-enter the password:");
        reEnterPasswordLabel.setBounds(30, 400, 300, 25);
        reEnterPasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        reEnterPasswordLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        add(reEnterPasswordLabel);

        // create re-enter the password input area
        JTextField reEnterPasswordField = new JTextField();
        reEnterPasswordField.setBounds(30, 440, 450,55);
        reEnterPasswordField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        reEnterPasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        reEnterPasswordField.setForeground(CommonConstants.TEXT_COLOR);
        add(reEnterPasswordField);


        // create register button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));

        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(145, 530, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = passwordField.getText();

                // get re-enter password
                String rePass = reEnterPasswordField.getText();



                if ( validateUserInput(username, password, rePass) ) {
                    // register user to the db
                    if ( MyJDBC.register(username, password) ) {
                        // dispose of this gui
                        RegisterFormGUI.this.dispose();

                        // take user back to the login gui
                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);

                        // create result dialog
                        JOptionPane.showMessageDialog(loginFormGUI,"Registered Account Successfully!");

                    }else {
                        // register failed ( likely due to the user already existing in the db )
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Username already taken");
                        usernameField.setText("");
                        passwordField.setText("");
                        reEnterPasswordField.setText("");
                    }
                }else {
                    // invalid user input
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Error: Username must be at least 6 characters \n + " +
                                    "and/or Password must match" );
                    usernameField.setText("");
                    passwordField.setText("");
                    reEnterPasswordField.setText("");
                }


            }
        });
        add(registerButton);

        // create login label
        JLabel loginLabel = new JLabel("Already have an account? Login here!");
        loginLabel.setBounds(160, 590, 250, 20);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // create
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();

                new LoginFormGUI().setVisible(true);
            }
        });
        add(loginLabel);

    }

    private boolean validateUserInput ( String username, String password, String rePassword ) {
        // all fields must have a value
        if ( username.length() == 0 || password.length() == 0 || rePassword.length() == 0 ) {
            return false;
        }

        // username has to be at least 6 characters long
        if ( username.length() < 6 ) return false;

        // password and rePassword must be the same
        if ( !password.equals(rePassword) ) return false;

        // passed validation
        return true;

    }
}
