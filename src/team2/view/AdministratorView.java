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

    public AdministratorView(AdminController ac) {
        this.adminController = ac;

        startAdminView();
    }

    public void startAdminView() {
        System.out.println("now logged in: " + adminController.getMarketDB().getUser().toString());
        System.out.println("Admin view started!!!");

        JFrame frame = new JFrame("Administrator view");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JMenuItem delUser = new JMenuItem("Remove user");
        usersMenu.add(delUser);
        JMenuItem showAllUser = new JMenuItem("Show all users");
        usersMenu.add(showAllUser);
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
}
