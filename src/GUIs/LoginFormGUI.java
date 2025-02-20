package GUIs;

import DB.MyJDBC;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class LoginFormGUI extends Form{
    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents() {

        // creating logging label
        JLabel loginLabel = new JLabel("Login");

        // configure component's x,y position and width/height values relative to
        loginLabel.setBounds(0, 25, 520, 100);

        // change the font color
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        // change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add component GUI
        add(loginLabel);

        // create username label
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 150, 300, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 24));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));


        add(usernameLabel);
        add(usernameField);


        // create password label
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(30, 295, 300, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 24));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 325, 450, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));


        // create my thing
        JLabel saySomethingLabel = new JLabel("Free to text");
        saySomethingLabel.setBounds(30, 415, 230,40);
//        saySomething.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        saySomethingLabel.setForeground(CommonConstants.TEXT_COLOR);
        saySomethingLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  20));

        JTextField saySomethingField = new JTextField();
        saySomethingField.setBounds(30, 455, 450, 55);
//        saySomethingField.setBorder( BorderFactory.createLoweredSoftBevelBorder());
        saySomethingField.setBackground(CommonConstants.SECONDARY_COLOR);
        saySomethingField.setForeground(CommonConstants.TEXT_COLOR);
        saySomethingField.setFont(new Font(Font.DIALOG, Font.PLAIN, 22 ));

        add(passwordLabel);
        add(passwordField);
        add(saySomethingLabel);
        add(saySomethingField);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));

        // change the cursor to a hand hover over the button
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setBounds(145, 530, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = new String(passwordField.getPassword());

                if (MyJDBC.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Welcome " +
                                                                username + "!");
                }else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Wrong username or password");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        add(loginButton);


        // creating register label ( used to load the register GUI )
        JLabel registerLabel = new JLabel("Don't have an account? Register here!");
        registerLabel.setBounds(160, 590, 250, 20);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);

        // add functionality so that when clicked it will launch the register for gui
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI
                LoginFormGUI.this.dispose();

                // launch the register GUI
                new RegisterFormGUI().setVisible(true);
            }

        });
        add(registerLabel);



    }
}
