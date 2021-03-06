package team3.frame;

import team3.controller.*;
import team3.model.*;
import team3.model.Admin;
import team3.model.MarketDB;
import team3.model.Seller;
import team3.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 01.11.2015.
 */
public class LoginFrame extends JFrame{

    private MarketDB marketDB;

    private JTextField loginField;
    private JPasswordField passField;
    private JButton enterButton;

    public LoginFrame(MarketDB marketDB){

        this.marketDB = marketDB;

        setLocationRelativeTo(null);
        setSize(250, 150);
        setTitle("Authorization");
        init();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {

        JLabel login = new JLabel("Login: ");
        JLabel pass = new JLabel("Password: ");

        loginField = new JTextField();
        passField = new JPasswordField();

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String login = loginField.getText();
                String pass = String.valueOf(passField.getPassword());

                User tempUserToAuthorize = new User();
                tempUserToAuthorize.setLogin(login);
                tempUserToAuthorize.setPass(pass);

                int index = marketDB.getUsers().indexOf(tempUserToAuthorize);

                if (index != -1 ) {

                    if (marketDB.getUsers().get(index) instanceof Admin) {
                        Admin enteredAs = (Admin) marketDB.getUsers().get(index);
                        IAdminController iAdminController = new AdminController(marketDB);
                        new AdminFrame(enteredAs, iAdminController);
                        dispose();

                    } else if (marketDB.getUsers().get(index) instanceof Seller) {
                        Seller enteredAs = (Seller) marketDB.getUsers().get(index);
                        ISellerController iSellerController = new SellerController(marketDB);
                        new SellerFrame(enteredAs, iSellerController);
                        dispose();
                    } else if (marketDB.getUsers().get(index) instanceof Manager) {
                        Manager enteredAs = (Manager) marketDB.getUsers().get(index);
                        IManagerController iManagerController = new ManagerController(marketDB);
                        new ManagerFrame(enteredAs, iManagerController);
                        dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,"Wrong login or password.","Error",JOptionPane.ERROR_MESSAGE);

                }
            }


        });


        JPanel northInputPanel = new JPanel(new GridLayout(2,2));
        northInputPanel.add(login);
        northInputPanel.add(loginField);
        northInputPanel.add(pass);
        northInputPanel.add(passField);

        getContentPane().add(northInputPanel, BorderLayout.NORTH);
        getContentPane().add(enterButton, BorderLayout.SOUTH);




    }



}
