package team1.view;

import team1.Authentication.LoginPass;
import team1.controller.AdminController;
import team1.model.MarketDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Anya on 01.11.2015.
 */
public class AdminViewFrame extends JFrame{

    private MarketDB marketDB; //временная переменная для теста

    public AdminViewFrame(MarketDB marketDB)  //marketDB - временный параметр для теста
    {
        setTitle("login/pass");
        setSize(600, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
        this.marketDB = marketDB;
    }

    void init(){

        setLayout(new GridLayout(5, 1));

        JButton showButton = new JButton("Show All");
        showButton.addActionListener(new showActionListener());
        getContentPane().add(showButton);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new addActionListener());
        getContentPane().add(addButton);

        JButton renameButton = new JButton("Rename");
        renameButton.addActionListener(new renameActionListener());
        getContentPane().add(renameButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new deleteActionListener());
        getContentPane().add(deleteButton);

        JButton findButton = new JButton("Find");
        findButton.addActionListener(new findActionListener());
        getContentPane().add(findButton);
    }

    private class showActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame showJFrame = new JFrame();
            showJFrame.setTitle("Show all products");
            showJFrame.setSize(600, 200);
            showJFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            AdminController adminController = new AdminController(marketDB);
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
            System.out.println("Button Add is pressed");
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


}
