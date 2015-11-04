package team2.view;

import team2.controller.StartViewController;
import team2.model.Guest;
import team2.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dima on 04.11.2015.
 */
public class StartView extends JFrame {
    private User user;
    private JTextField login;
    private JTextField password;

    public StartView(User user, StartViewController startViewController) {
        this.user = user;
    }

    public void startMenu() {
        System.out.println(this.user.toString());

        setTitle("How are you?");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel loggedUserTxt = new JLabel("Now logged in: ");
        JLabel loggedUser = new JLabel(this.user.toString());
        JLabel pleaseLogin = new JLabel("Please, input login: ");
        JLabel pleasePassw = new JLabel("Please, input password: ");

        login = new JTextField("");
        password = new JTextField("");

        setLayout(new GridLayout(4,2));

        getContentPane().add(loggedUserTxt);
        getContentPane().add(loggedUser);

        getContentPane().add(pleaseLogin);
        getContentPane().add(login);

        getContentPane().add(pleasePassw);
        getContentPane().add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginListener());
        JButton exitButton = new JButton("Exit");
        //exitButton //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(loginButton);
        getContentPane().add(exitButton);

        setVisible(true);
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String loginText = login.getText();
            String passwordText = password.getText();
            System.out.println("LOGIN Button is pressed!!!" + loginText + " " + passwordText);
        }
    }

}


