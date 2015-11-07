package team2.view;

import team2.controller.AdminController;
import team2.model.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dima on 06.11.2015.
 */
public class AdministratorView extends JFrame{

    private AdminController adminController;

    private JFrame frame = new JFrame("Administrator view");

    public AdministratorView(AdminController ac) {
        this.adminController = ac;

        startAdminView();
    }

    public void startAdminView() {
        System.out.println("now logged in: " + adminController.getMarketDB().getUser().toString());
        System.out.println("Admin view started!!!");

        //JFrame frame = new JFrame("Administrator view");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /// ???? ////// JFrame.setDefaultLookAndFeelDecorated(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new  JMenu("File");
        ///////////
        JMenuItem changeUser = new JMenuItem("Change User");
        fileMenu.add(changeUser);
        changeUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                adminController.getMarketDB().setUser( new Guest(0,"guest","guest") );
                adminController.getMarketDB().getUser().startView( adminController.getMarketDB() );
            }
        });

        JMenuItem exitMarket = new JMenuItem("Exit");
        fileMenu.add(exitMarket);
        exitMarket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /////////
        menuBar.add(fileMenu);

        JMenu usersMenu = new  JMenu("Users");
        ///////////
        JMenuItem addUser = new JMenuItem("Add user");
        usersMenu.add(addUser);
        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUserMethod(frame);
            }
        });

        JMenuItem delUser = new JMenuItem("Remove user");
        usersMenu.add(delUser);

        JMenuItem showAllUser = new JMenuItem("Show all users");
        usersMenu.add(showAllUser);
        showAllUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllUserMethod(frame);
            }
        });

        /////////
        menuBar.add(usersMenu);

        JMenu prodMenu = new  JMenu("Products");
        ///////////
        JMenuItem addProd = new JMenuItem("Add product");
        prodMenu.add(addProd);
        JMenuItem delProd = new JMenuItem("Remove product");
        prodMenu.add(delProd);
        JMenuItem showAllProd = new JMenuItem("Show all products");
        prodMenu.add(showAllProd);
        /////////
        menuBar.add(prodMenu);

        frame.setJMenuBar(menuBar);

        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void addUserMethod(JFrame frame) {
        JTextField login = new JTextField("",20);
        JTextField password = new JTextField("",20);
        JTextArea textArea = new JTextArea(10, 20);

        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(8,1));

        content.add(new JLabel("Enter new user login: "));
        content.add(login);
        content.add(new JLabel("Enter new user password: "));
        content.add(password);
        content.add(new JLabel("Choose new user role: "));
        String[] items = {"Seller", "Admin", "Guest"};
        JComboBox roles = new JComboBox(items);
        content.add(roles);

        JButton createButton = new JButton("Create");
        content.add(createButton);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newUserId = adminController.createUser(login.getText(), password.getText(), roles.getSelectedItem().toString());
                String msg = "Unknown error.";
                if ( newUserId == -5 ) {
                    msg = "You try to make guest. One guest already registred in system.";
                } else if ( newUserId == -4 ) {
                    msg = "The login you enter is already registered";
                } else if ( newUserId == -3 ) {
                    msg = "The login or password will have lenght more than 1 character.";
                } else if ( newUserId > 0 ) {
                    msg = "User ID: " + newUserId + " login: " + login.getText() + " was registered successfully.";
                }
                String old = textArea.getText();
                textArea.setText(old + "\n " + msg);
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);
        content.add(scrollPane);

        frame.getContentPane().add(content);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        System.out.println("adding new user");
    }

    private void showAllUserMethod(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel content = new JPanel();
        JTextArea textArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        content.add(scrollPane);

        String txt = "";
        for (int i = 0; i < adminController.getMarketDB().getUsers().size(); i++) {
            txt = txt + "\n" + adminController.getMarketDB().getUsers().get(i).toString();
        }
        textArea.setText(txt);

        frame.getContentPane().add(content);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
