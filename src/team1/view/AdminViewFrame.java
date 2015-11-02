package team1.view;

import team1.Authentication.LoginPass;
import team1.controller.AdminController;
import team1.model.MarketDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by Anya on 01.11.2015.
 */
public class AdminViewFrame extends JFrame{

    private MarketDB marketDB; //временная переменная для теста
    private AdminController adminController;

    public AdminViewFrame(MarketDB marketDB)  //marketDB - временный параметр для теста
    {
        setTitle("login/pass");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
        this.marketDB = marketDB;
        adminController = new AdminController(marketDB);
    }

    void init(){

        setLayout(new GridLayout(6, 1));

        JButton showButton = new JButton("Show All");
        showButton.setMnemonic('S');
        showButton.setToolTipText("press to show all products");
        showButton.addActionListener(new showActionListener());
        getContentPane().add(showButton);

        JButton addButton = new JButton("Add");
        addButton.setMnemonic('A');
        addButton.setToolTipText("press to add new product");
        addButton.addActionListener(new addActionListener());
        getContentPane().add(addButton);

        JButton renameButton = new JButton("Rename");
        renameButton.setMnemonic('R');
        renameButton.setToolTipText("press to rename exist product");
        renameButton.addActionListener(new renameActionListener());
        getContentPane().add(renameButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setMnemonic('D');
        deleteButton.setToolTipText("press to delete product");
        deleteButton.addActionListener(new deleteActionListener());
        getContentPane().add(deleteButton);

        JButton findButton = new JButton("Find");
        findButton.setMnemonic('F');
        findButton.setToolTipText("press to find product");
        findButton.addActionListener(new findActionListener());
        getContentPane().add(findButton);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setMnemonic('U');
        addUserButton.setToolTipText("press to add user");
        addUserButton.addActionListener(new addUserActionListener());
        getContentPane().add(addUserButton);
    }

    private class showActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame showJFrame = new JFrame();
            showJFrame.setTitle("Show all products");
            showJFrame.setSize(600, 200);
            showJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            //AdminController adminController = new AdminController(marketDB);
            ArrayList arrayList = adminController.getAll();

            DefaultListModel listModel = new DefaultListModel();
            JList list = new JList(listModel);

            for (Object obj: arrayList){
                listModel.addElement(obj);
            }

            showJFrame.setLayout(new GridLayout(1, 1));
            showJFrame.getContentPane().add(list);
            showJFrame.setVisible(true);
         }
    }

    private class addActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame addJFrame = new JFrame();
            addJFrame.setTitle("Add new product");
            addJFrame.setSize(600, 200);
            addJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JTextField barCode = new JTextField("");
            JTextField model = new JTextField("");
            JTextField price = new JTextField("");

            addJFrame.setLayout(new GridLayout(4, 2));

            addJFrame.getContentPane().add(new JLabel("barcode:"));
            addJFrame.getContentPane().add(barCode);
            addJFrame.getContentPane().add(new JLabel("model:"));
            addJFrame.getContentPane().add(model);
            addJFrame.getContentPane().add(new JLabel("price:"));
            addJFrame.getContentPane().add(price);

            JButton addButton = new JButton("OK");
            addButton.setMnemonic('O');
            addButton.setToolTipText("press after typing all fields");
            //addButton.addActionListener(new addProductActionListener(barCode.getText(), model.getText(), Double.parseDouble(price.getText())));
            addButton.addActionListener(new addProductActionListener(addJFrame));
            addJFrame.getContentPane().add(addButton);

            addJFrame.setVisible(true);

        }
    }

    private class addProductActionListener implements ActionListener{

        private JFrame jFrame;

        public addProductActionListener(JFrame jFrame) {
             this.jFrame = jFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String barCode = jFrame.getContentPane().getComponent(1).getInputContext().toString();
            String model = jFrame.getContentPane().getComponent(3).getInputContext().toString();
            adminController.addProduct(barCode, model, 0.0);
            jFrame.setVisible(false);
        }
    }


    private class renameActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button  Rename is pressed");
        }
    }


    private class findActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button Find is pressed");
        }
    }

    private class deleteActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button Delete is pressed");
        }
    }

    private class addUserActionListener implements ActionListener{
        private JTextField newId;
        private JTextField newLogin;
        private JTextField newPassword;

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame addUserFrame = new JFrame();
            addUserFrame.setTitle("Add User");
            addUserFrame.setSize(300, 150);
            addUserFrame.setLocationRelativeTo(null);
            addUserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            newId = new JTextField("");
            newLogin = new JTextField("");
            newPassword = new JTextField("");
            addUserFrame.setLayout(new GridLayout(4, 2));
            addUserFrame.getContentPane().add(new JLabel("id:"));
            addUserFrame.getContentPane().add(newId);
            addUserFrame.getContentPane().add(new JLabel("login:"));
            addUserFrame.getContentPane().add(newLogin);
            addUserFrame.getContentPane().add(new JLabel("password:"));
            addUserFrame.getContentPane().add(newPassword);
            JButton saveButton = new JButton("Save");
            addUserFrame.getContentPane().add(saveButton);
            addUserFrame.setVisible(true);
        }
    }

}
