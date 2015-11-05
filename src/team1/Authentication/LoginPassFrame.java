package team1.Authentication;

import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Seller;
import team1.model.User;
import team1.view.AdminViewFrame;
import team1.view.SellerViewFrame;

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
    private JLabel incorrectPass;

    public LoginPassFrame(MarketDB marketDB) throws HeadlessException {
        this.marketDB = marketDB;
        setTitle("login/pass");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    void init(){

        login = new JTextField("");
        password = new JPasswordField("");
        Font fontForLabels = new Font(null, Font.BOLD, 18);
        Font fontForErrorMessage = new Font(null, Font.BOLD, 20);

        setLayout(new GridLayout(4, 1));

        JPanel loginPanel = new JPanel(new GridLayout(1,2));
        JLabel loginLabel = new JLabel("Login: ");
        loginLabel.setFont(fontForLabels);
        loginPanel.add(loginLabel);
        loginPanel.add(login);

        JPanel passwordPanel = new JPanel(new GridLayout(1,2));
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(fontForLabels);
        passwordPanel.add(passLabel);
        passwordPanel.add(password);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton okButton = new JButton("OK");

        okButton.setFont(fontForLabels);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        okButton.setToolTipText("press after typing login and password");
        okButton.addActionListener(new MyActionListener());


        getContentPane().add(loginPanel);
        getContentPane().add(passwordPanel);
        getContentPane().add(buttonPanel);

        incorrectPass = new JLabel("", SwingConstants.CENTER);
        incorrectPass.setFont(fontForErrorMessage);
        getContentPane().add(incorrectPass);
    }

    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPass loginPass = new LoginPass(marketDB);
            User user = loginPass.loginFrame(login.getText(),password.getText());
            if (user instanceof Admin){
                AdminViewFrame adminFrame = new AdminViewFrame(marketDB);
                setVisible(false);
            } else if (user instanceof Seller){
                SellerViewFrame sellerFrame = new SellerViewFrame(marketDB);
                setVisible(false);
            } else {
                login.setText("");
                password.setText("");
                incorrectPass.setText("invalid login or password");
                incorrectPass.setForeground(Color.red);
            }
        }
    }

}
