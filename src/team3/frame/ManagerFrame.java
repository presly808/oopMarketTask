package team3.frame;

import team3.controller.IAdminController;
import team3.controller.IManagerController;
import team3.model.Admin;
import team3.model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 05.11.2015.
 */
public class ManagerFrame extends JFrame {

    private Manager user;
    private IManagerController iManagerController;


    public ManagerFrame(Manager user, IManagerController iManagerController) {

        this.user = user;
        this.iManagerController = iManagerController;

        setLocationRelativeTo(null);
        setSize(500, 500);
        setTitle(String.format("Manager: %s",user.getLogin()));
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void init() {

        JPanel northListsPanel = new JPanel(new GridLayout(2,2));

        northListsPanel.add(new JLabel("Admins:"));
        northListsPanel.add(new JLabel("Sellers:"));

        JTextArea adminsListArea = new JTextArea(iManagerController.getAdminsString());
        adminsListArea.setEditable(false);
        JScrollPane scrollPaneAdmins = new JScrollPane(adminsListArea);

        JTextArea sellersListArea = new JTextArea(iManagerController.getSellersString());
        sellersListArea.setEditable(false);
        JScrollPane scrollPaneSellers = new JScrollPane(sellersListArea);

        northListsPanel.add(scrollPaneAdmins);
        northListsPanel.add(scrollPaneSellers);


        JPanel southButtonsPanel = new JPanel(new GridLayout(1,3));

        JButton addButton = new JButton("Add user");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        JButton eraseButton = new JButton("Fire");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });


        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        southButtonsPanel.add(addButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(signOutButton);


        getContentPane().add(northListsPanel, BorderLayout.NORTH);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);



    }
}
