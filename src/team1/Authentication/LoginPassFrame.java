package team1.Authentication;

import team1.model.MarketDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bizianov on 01.11.2015.
 */
public class LoginPassFrame extends JFrame {
    private MarketDB marketDB;
    private JTextField login;
    private JTextField password;

    public LoginPassFrame(MarketDB marketDB) throws HeadlessException {
        this.marketDB = marketDB;
        setTitle("login/pass");
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    void init(){
        login = new JTextField("");
        password = new JTextField("");
        setLayout(new GridLayout(3, 2));

        getContentPane().add(new JLabel("login:"));
        getContentPane().add(login);
        getContentPane().add(new JLabel("password:"));
        getContentPane().add(password);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new MyActionListener());
        getContentPane().add(okButton);
    }

    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPass loginPass = new LoginPass(marketDB);
            loginPass.loginFrame(login.getText(), password.getText());
        }
    }

}
