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
    private StartViewController svc;
    private JTextField login;
    private JTextField password;

    public StartView(StartViewController startViewController) {
        this.svc = startViewController;

        startMenu();
    }

    public void startMenu() {

        setTitle("How are you?");
        setSize(600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel loggedUserTxt = new JLabel("Now logged in: ");

        JLabel loggedUser = new JLabel(svc.getMarketDB().getUser().toString());

        JLabel hint = new JLabel("Try: guest-guest, admin-admin,");
        JLabel hint1 = new JLabel("cashmen-cashmen, and any wrong login");

        JLabel pleaseLogin = new JLabel("Please, input login: ");
        JLabel pleasePassw = new JLabel("Please, input password: ");

        login = new JTextField("");
        password = new JTextField("");

        setLayout(new GridLayout(5,2));

        getContentPane().add(loggedUserTxt);
        getContentPane().add(loggedUser);

        getContentPane().add(pleaseLogin);
        getContentPane().add(login);

        getContentPane().add(pleasePassw);
        getContentPane().add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginListener());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitListener());

        getContentPane().add(loginButton);
        getContentPane().add(exitButton);

        getContentPane().add(hint);
        getContentPane().add(hint1);

        setVisible(true);
    }

    private StartView getOuter() {
        return StartView.this;
    }

    private class LoginListener extends Component implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String loginText = login.getText();
            String passwordText = password.getText();
            User theUserWhoTryToLogIn = svc.checkLoginPassw(loginText, passwordText);
            //if ( svc.getMarketDB().getUser().equals(new Guest(0,"guest","guest")) ) { // guest
            if ( theUserWhoTryToLogIn.equals(new Guest(0,"guest","guest")) ) { // guest
                JOptionPane.showMessageDialog(getOuter(),
                        "Login or password is incorrect. try again or go out!",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
            getOuter().dispose();
            svc.getMarketDB().getUser().startView(svc.getMarketDB());

        }
    }

    private class ExitListener extends Component implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getOuter().dispose();
        }
    }

}


