package team3.frame;

import team3.controller.ISellerController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Evgenia on 11/1/15.
 */
public class SellerFrame extends JFrame {

    ISellerController iSellerController;

    private JTextArea fileContentArea;
    private JButton scanButton;
    private JButton billPaidButton;
    private JButton eraseButton;
    private JButton cancelButton;
    private JButton signOutButton;

    public SellerFrame(ISellerController iSellerController){

        this.iSellerController = iSellerController;

        setSize(600,1000);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init(){

        JLabel listTitle = new JLabel();
        JTextArea marketProductListArea = new JTextArea();

        JButton scanButton = new JButton("Scan");
        JButton billPaidButton = new JButton("Bill Paid");
        JButton eraseButton = new JButton("Erase");
        JButton cancelButton = new JButton("Cancel");
        JButton signOutButton = new JButton("Sign out");

        JPanel southButtonsPanel = new JPanel(new GridLayout(1,4));

        southButtonsPanel.add(scanButton);
        southButtonsPanel.add(billPaidButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(cancelButton);
        southButtonsPanel.add(signOutButton);

        getContentPane().add(listTitle, BorderLayout.NORTH);
        getContentPane().add(marketProductListArea, BorderLayout.CENTER);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
    }


}
