package team3.frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by 1 on 01.11.2015.
 */
public class AdminFrame extends JFrame {


    public AdminFrame (){
        setSize(600,600);
        setTitle("Admin menu");

        init();

        setVisible(true);
    }

    private void init() {

        JLabel listTitle = new JLabel();
        JTextArea marketProductsListArea = new JTextArea();

        JButton addButton = new JButton("Add");
        JButton eraseButton = new JButton("Erase");
        JButton findButton = new JButton("Find");
        JButton signOutButton = new JButton("Sign out");


        JPanel southButtonsPanel = new JPanel(new GridLayout(1,4));

        southButtonsPanel.add(addButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(findButton);
        southButtonsPanel.add(signOutButton);


        getContentPane().add(listTitle, BorderLayout.NORTH);
        getContentPane().add(marketProductsListArea, BorderLayout.CENTER);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);






    }
}
