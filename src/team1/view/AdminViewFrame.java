package team1.view;

import team1.Authentication.LoginPass;
import team1.controller.AdminController;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Seller;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Anya on 01.11.2015.
 */
public class AdminViewFrame extends JFrame{



    private MarketDB marketDB; //временная переменная для теста
    private AdminController adminController;
    private DefaultListModel productListModel = new DefaultListModel();

    public AdminViewFrame(MarketDB marketDB)  //marketDB - временный параметр для теста
    {
        setTitle("Admin view");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.marketDB = marketDB;
        adminController = new AdminController(marketDB);
        init();
        setVisible(true);

    }

    void init(){

        JPanel contentProducts = new JPanel();
        JPanel contentUsers = new JPanel();

        contentProducts.setLayout(new GridLayout(2, 1));

        JList productList = new JList(productListModel);
        ArrayList productsArrayList = adminController.getAll();
        for (Object obj: productsArrayList){
            productListModel.addElement(obj);
        }
        contentProducts.add(productList);
        contentProducts.add(new JScrollPane(productList), BorderLayout.CENTER);

        contentUsers.setLayout(new GridLayout(2, 1));

        DefaultListModel userListModel = new DefaultListModel();
        JList userList = new JList(userListModel);
        ArrayList usersArrayList = adminController.getAllUsers();
        for (Object obj: usersArrayList){
            userListModel.addElement(obj);
        }
        contentUsers.add(userList);
        contentUsers.add(new JScrollPane(userList), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        contentProducts.add(buttonsPanel, BorderLayout.SOUTH);

        JButton showButton = new JButton("Show All");
        showButton.setMnemonic('S');
        showButton.setToolTipText("press to show all products");
        showButton.addActionListener(new showActionListener());
        buttonsPanel.add(showButton);

        JButton addButton = new JButton("Add");
        addButton.setMnemonic('A');
        addButton.setToolTipText("press to add new product");
        addButton.addActionListener(new addActionListener());
        buttonsPanel.add(addButton);

        JButton renameButton = new JButton("Rename");
        renameButton.setMnemonic('R');
        renameButton.setToolTipText("press to rename exist product");
        renameButton.addActionListener(new renameActionListener());
        buttonsPanel.add(renameButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setMnemonic('D');
        deleteButton.setToolTipText("press to delete product");
        deleteButton.addActionListener(new deleteActionListener());
        buttonsPanel.add(deleteButton);

        JButton findButton = new JButton("Find");
        findButton.setMnemonic('F');
        findButton.setToolTipText("press to find product");
        findButton.addActionListener(new findActionListener());
        buttonsPanel.add(findButton);

        contentUsers.setLayout(new GridLayout(1, 1));

        JButton addUserButton = new JButton("Add User");
        addUserButton.setMnemonic('U');
        addUserButton.setToolTipText("press to add user");
        addUserButton.addActionListener(new addUserActionListener());
        contentUsers.add(addUserButton);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Products ", contentProducts);
        tabbedPane.addTab("Users ", contentUsers);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(content);

    }

    private class showActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame showJFrame = new JFrame();
            showJFrame.setTitle("Show all products");
            showJFrame.setSize(600, 200);
            showJFrame.setLocationRelativeTo(null);
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
        JFrame addJFrame = new JFrame();
        JTextField barCode = new JTextField("");
        JTextField model = new JTextField("");
        JTextField price = new JTextField("");

        @Override
        public void actionPerformed(ActionEvent e) {


            addJFrame.setTitle("Add new product");
            addJFrame.setSize(600, 200);
            addJFrame.setLocationRelativeTo(null);
            addJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

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
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    productListModel.addElement(adminController.addProduct(barCode.getText(), model.getText(), Double.parseDouble(price.getText())));
                    addJFrame.setVisible(false);
                 }
            });

            addJFrame.getContentPane().add(addButton);
            addJFrame.setVisible(true);

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
        private JTextField newLogin, newPassword;
        private JFormattedTextField newId;
        private JComboBox type;
        private JLabel infoMessage;
        private String login, password;
        private int id;
        boolean doesExist;

        @Override
        public void actionPerformed(ActionEvent e) {

            NumberFormat format = NumberFormat.getInstance();
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setValueClass(Integer.class);
            formatter.setAllowsInvalid(false);

            JFrame addUserFrame = new JFrame();
            addUserFrame.setTitle("Add User");
            addUserFrame.setSize(350, 180);
            addUserFrame.setLocationRelativeTo(null);
            addUserFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            newId = new JFormattedTextField(formatter);
            newLogin = new JTextField("");
            newPassword = new JTextField("");
            addUserFrame.setLayout(new GridLayout(5, 2));
            addUserFrame.getContentPane().add(new JLabel("id:"));
            addUserFrame.getContentPane().add(newId);
            addUserFrame.getContentPane().add(new JLabel("login:"));
            addUserFrame.getContentPane().add(newLogin);
            addUserFrame.getContentPane().add(new JLabel("password:"));
            addUserFrame.getContentPane().add(newPassword);
            addUserFrame.getContentPane().add(new JLabel("type:"));
            String[] typeList = {"Admin","Seller"};
            type = new JComboBox(typeList);
            type.setSelectedIndex(1);
            addUserFrame.getContentPane().add(type);
            JButton saveButton = new JButton("Save");
            addUserFrame.getContentPane().add(saveButton);
            infoMessage = new JLabel("", SwingConstants.CENTER);
            addUserFrame.getContentPane().add(infoMessage);
            addUserFrame.setVisible(true);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    id = (int) newId.getValue();
                    login = newLogin.getText();
                    password = newPassword.getText();
                    if (type.getSelectedIndex() == 0) {
                        for (Admin admin : marketDB.getAdmins()) {
                            if (admin.getId() == id || admin.getLogin().equals(login)) {
                                doesExist = true;
                            }
                        }
                        if (doesExist == false) {
                            marketDB.getAdmins().add(new Admin(id, login, password));
                            userAdded();
                            System.out.println(marketDB.getAdmins());
                        } else {
                            errorMessage();
                        }
                    } else {
                        for (Seller seller : marketDB.getSellers()) {
                            if (seller.getId() == id || seller.getLogin().equals(login)) {
                                doesExist = true;
                            }
                        }
                        if (doesExist == false) {
                            marketDB.getSellers().add(new Seller(id, login, password));
                            userAdded();
                            System.out.println(marketDB.getSellers());
                        } else {
                            errorMessage();
                        }
                    }
                }

                private void userAdded() {
                    infoMessage.setText(login + " added");
                    infoMessage.setForeground(new Color(0, 150, 0));
                    newId.setBorder(newPassword.getBorder());
                    newLogin.setBorder(newPassword.getBorder());
                    newId.setValue(null);
                    newLogin.setText("");
                    newPassword.setText("");
                }

                private void errorMessage() {
                    infoMessage.setText("login or id already exists");
                    infoMessage.setForeground(Color.red);
                    newId.setBorder(BorderFactory.createLineBorder(Color.red));
                    newLogin.setBorder(BorderFactory.createLineBorder(Color.red));
                    newPassword.setText("");
                    doesExist=false;
                }
            });
        }
    }

}
