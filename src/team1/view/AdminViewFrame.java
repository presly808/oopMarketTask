package team1.view;

import static team1.constant.ProjectConstants.*;

import org.json.simple.parser.ParseException;
import team1.controller.AdminController;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Product;
import team1.model.Seller;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Anya on 01.11.2015.
 */
public class AdminViewFrame extends JFrame{

    public static final String PRINT_IMG_FILE = "src\\team1\\images\\print.png";
    public static final String SRC_TEAM1_IMAGES_ADD_PRODUCT_PNG = "src\\team1\\images\\addProduct.png";
    public static final String SRC_TEAM1_IMAGES_RENAME_PNG = "src\\team1\\images\\rename.png";
    public static final String SRC_TEAM1_IMAGES_DELETE_PNG = "src\\team1\\images\\delete.png";
    public static final String SRC_TEAM1_IMAGES_FIND_PNG = "src\\team1\\images\\find.png";
    public static final String SRC_TEAM1_IMAGES_ADD_PRODUCT_PNG1 = "src\\team1\\images\\addProduct.png";


    private MarketDB marketDB; //временная переменная для теста
    private AdminController adminController;
    private DefaultListModel productListModel = new DefaultListModel();
    private DefaultListModel userListModel = new DefaultListModel();
    private JList productList = new JList(productListModel);

    public AdminViewFrame(AdminController adminController)  //marketDB - временный параметр для теста
    {
        this.adminController = adminController;
        this.marketDB = adminController.getMarketDB();
        setTitle("Admin view");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);

    }

    void init(){

        JPanel contentProducts = new JPanel();
        JPanel contentUsers = new JPanel();

        //contentProducts.setLayout(new GridLayout(2, 1));
        contentProducts.setLayout(new BorderLayout(5, 5));

        try {
            adminController.readFromProductFile();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList productsArrayList = adminController.getAll();
        for (Object obj: productsArrayList){
            productListModel.addElement(obj);
        }
        //contentProducts.add(productList, BorderLayout.CENTER);
        contentProducts.add(new JScrollPane(productList), BorderLayout.CENTER);

        //contentUsers.setLayout(new GridLayout(2, 1));
        contentUsers.setLayout(new BorderLayout(5, 5));


        JList userList = new JList(userListModel);
        ArrayList usersArrayList = adminController.getAllUsers();
        for (Object obj: usersArrayList){
            userListModel.addElement(obj);
        }
        contentUsers.add(userList);
        contentUsers.add(new JScrollPane(userList), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 5, 5, 0));
        contentProducts.add(buttonsPanel, BorderLayout.SOUTH);

        JButton showButton = new JButton(new ImageIcon(PRINT_IMG_FILE));
        showButton.setBorder(BorderFactory.createTitledBorder("Show all"));
        showButton.setMnemonic('S');
        showButton.setToolTipText("press to show all products");
        showButton.addActionListener(new showActionListener());
        buttonsPanel.add(showButton);

        JButton addButton = new JButton(new ImageIcon(SRC_TEAM1_IMAGES_ADD_PRODUCT_PNG));
        addButton.setBorder(BorderFactory.createTitledBorder("Add"));
        addButton.setMnemonic('A');
        addButton.setToolTipText("press to add new product");
        addButton.addActionListener(new addActionListener());
        buttonsPanel.add(addButton);

        JButton renameButton = new JButton(new ImageIcon(SRC_TEAM1_IMAGES_RENAME_PNG));
        renameButton.setBorder(BorderFactory.createTitledBorder("Rename"));
        renameButton.setMnemonic('R');
        renameButton.setToolTipText("press to rename exist product");
        renameButton.addActionListener(new renameActionListener());
        buttonsPanel.add(renameButton);

        JButton deleteButton = new JButton(new ImageIcon(SRC_TEAM1_IMAGES_DELETE_PNG));
        deleteButton.setBorder(BorderFactory.createTitledBorder("Delete"));
        deleteButton.setMnemonic('D');
        deleteButton.setToolTipText("press to delete product");
        deleteButton.addActionListener(new deleteActionListener());
        buttonsPanel.add(deleteButton);

        JButton findButton = new JButton(new ImageIcon(SRC_TEAM1_IMAGES_FIND_PNG));
        findButton.setBorder(BorderFactory.createTitledBorder("Find"));
        findButton.setMnemonic('F');
        findButton.setToolTipText("press to find product");
        findButton.addActionListener(new findActionListener());
        buttonsPanel.add(findButton);

        //contentUsers.setLayout(new GridLayout(1, 1));
        JPanel buttonsUserPanel = new JPanel();
        buttonsUserPanel.setLayout(new GridLayout(1, 1));
        contentUsers.add(buttonsUserPanel, BorderLayout.SOUTH);

        //JButton addUserButton = new JButton("Add User");
        JButton addUserButton = new JButton(new ImageIcon(SRC_TEAM1_IMAGES_ADD_PRODUCT_PNG1));
        addUserButton.setBorder(BorderFactory.createTitledBorder("Add User"));
        addUserButton.setMnemonic('U');
        addUserButton.setToolTipText("press to add user");
        addUserButton.addActionListener(new addUserActionListener());
        buttonsUserPanel.add(addUserButton);

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

            addJFrame.getContentPane().removeAll();
            addJFrame.setTitle("Add new product");
            addJFrame.setSize(300, 200);
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
                    adminController.addToProductFile(new Product(barCode.getText(), model.getText(), Double.parseDouble(price.getText())));
                 }
            });

            addJFrame.getContentPane().add(addButton);
            addJFrame.setVisible(true);

        }


    }

   private class renameActionListener implements ActionListener{
       JFrame renameJFrame = new JFrame();
       JTextField barCode = new JTextField("");
       JTextField model = new JTextField("");
       JTextField price = new JTextField("");

        @Override
        public void actionPerformed(ActionEvent e) {
           if (productList.isSelectionEmpty()){
                System.out.println("To rename, you need to select an item in the list!");
                return;};

            renameJFrame.getContentPane().removeAll();
            renameJFrame.setTitle("Remove product");
            renameJFrame.setSize(300, 200);
            renameJFrame.setLocationRelativeTo(null);
            renameJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(5, 5));
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(4, 2));

            fieldPanel.add(new JLabel("barcode:"));
            fieldPanel.add(barCode);
            fieldPanel.add(new JLabel("model:"));
            fieldPanel.add(model);
            fieldPanel.add(new JLabel("price:"));
            fieldPanel.add(price);

            mainPanel.add(fieldPanel, BorderLayout.CENTER);

            Product product = (Product) productList.getSelectedValue();
            barCode.setText(product.getBarCode());
            model.setText(product.getModel());
            price.setText("" + product.getPrice());

            JButton addButton = new JButton("OK");
            addButton.setMnemonic('O');
            addButton.setToolTipText("press after typing all fields");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adminController.renameProduct((Product) productList.getSelectedValue(), barCode.getText(), model.getText(), Double.parseDouble(price.getText()));
                    renameJFrame.setVisible(false);
                }
            });

            JButton cancelButton = new JButton("CANCEL");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   renameJFrame.setVisible(false);
                }
            });


            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

            buttonsPanel.add(addButton);
            buttonsPanel.add(cancelButton);

            renameJFrame.add(mainPanel);
            renameJFrame.setVisible(true);

        }
    }

    private class findActionListener implements ActionListener{

        JFrame findJFrame = new JFrame();
        JTextField barCode = new JTextField("");

        @Override
        public void actionPerformed(ActionEvent e) {

            findJFrame.getContentPane().removeAll();
            findJFrame.setTitle("Find product");
            findJFrame.setSize(450, 120);
            findJFrame.setLocationRelativeTo(null);
            findJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(5, 5));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(1, 2));

            jPanel.add(new JLabel("barcode:"));
            jPanel.add(barCode);
            mainPanel.add(jPanel, BorderLayout.CENTER);

            JButton addButton = new JButton("OK");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Product product = adminController.findProductByCode(barCode.getText());
                    if (product != null) {
                        System.out.println("Such a product exist! - " + product);
                        productList.setSelectedValue(product, true);}
                    else {System.out.println("Such a product does not exist!");}

                }
            });

            JButton cancelButton = new JButton("CANCEL");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    findJFrame.setVisible(false);
                }
            });


            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

            buttonsPanel.add(addButton);
            buttonsPanel.add(cancelButton);

            findJFrame.add(mainPanel);
            findJFrame.setVisible(true);

          }
    }

    private class deleteActionListener implements ActionListener{
        JFrame deleteJFrame = new JFrame();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (productList.isSelectionEmpty()){
                System.out.println("To remove, you need to select an item in the list!");
                return;};

            deleteJFrame.getContentPane().removeAll();
            deleteJFrame.setTitle("Delete product");
            deleteJFrame.setSize(550, 100);
            deleteJFrame.setLocationRelativeTo(null);
            deleteJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(5, 5));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            mainPanel.add(new JLabel("Are you sure you want to delete " + productList.getSelectedValue() + "?"), BorderLayout.CENTER);
            JButton addButton = new JButton("OK");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Product product = (Product) productList.getSelectedValue();
                    adminController.deleteProduct(product.getBarCode());
                    productListModel.removeElement(productList.getSelectedValue());
                    deleteJFrame.setVisible(false);
                }
            });

            JButton cancelButton = new JButton("CANCEL");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteJFrame.setVisible(false);
                }
            });

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(1, 2, 5, 1));
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

            buttonsPanel.add(addButton);
            buttonsPanel.add(cancelButton);
            deleteJFrame.add(mainPanel);
            deleteJFrame.setVisible(true);

            };
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
                            Admin newAdmin = new Admin(id, login, password);
                            marketDB.getAdmins().add(newAdmin);
                            userListModel.addElement(newAdmin);
                            userAdded();
                            try {
                                adminController.updateAdmins();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            adminController.addToAdminFile(new Admin(id, login, password));
                            //System.out.println(marketDB.getAdmins());
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
                            Seller newSeller = new Seller(id, login, password);
                            marketDB.getSellers().add(newSeller);
                            userListModel.addElement(newSeller);
                            userAdded();
                            try {
                                adminController.updateSellers();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            adminController.addToSellerFile(new Seller(id, login, password));
                            //System.out.println(marketDB.getSellers());
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
