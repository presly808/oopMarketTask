package team3.frame;

import team3.controller.AdminController;
import team3.controller.IAdminController;
import team3.controller.ISellerController;
import team3.controller.SellerController;
import team3.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by 1 on 01.11.2015.
 */
public class LoginFrame extends JFrame{

    private MarketDB marketDB = new MarketDB();










    // In future it has to load Database from .txt file by means of serialization each time it is run

    private JTextField loginField;
    private JPasswordField passField;
    private JButton enterButton;

    public LoginFrame(){
        setSize(80, 150);
        setTitle("Authorization");
        init();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void init() {

        loginField = new JTextField();
        passField = new JPasswordField();

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User tempUserToAuthorize = new User();
                tempUserToAuthorize.setLogin(loginField.getText());
                tempUserToAuthorize.setPass(passField.getPassword().toString());

                int index = marketDB.getUsers().indexOf(tempUserToAuthorize);

                if (index != -1 ) {

                    if (marketDB.getUsers().get(index) instanceof Admin) {
                        IAdminController iAdminController = new AdminController(marketDB);
                        new AdminFrame(iAdminController);
                        dispose();

                    } else if (marketDB.getUsers().get(index) instanceof Seller) {
                        ISellerController iSellerController = new SellerController(marketDB);
                        new SellerFrame(iSellerController);
                        dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,"Wrong login or password.","Error",JOptionPane.ERROR_MESSAGE);

                }
            }
        });



    }



}
