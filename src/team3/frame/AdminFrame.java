package team3.frame;

import org.omg.IOP.ExceptionDetailMessage;
import team3.controller.IAdminController;
import team3.model.MarketDB;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by 1 on 01.11.2015.
 */
public class AdminFrame extends JFrame {

    IAdminController iAdminController;

    public AdminFrame (IAdminController iAdminController){

        this.iAdminController = iAdminController;

        setSize(800, 800);
        setTitle("Admin menu");
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {



        JLabel listTitle = new JLabel("Product list:");

        JPanel marketProductsListArea = new JPanel();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddProductFrame();

            }
        });

        JButton eraseButton = new JButton("Erase");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
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




        JPanel southButtonsPanel = new JPanel(new GridLayout(1,4));

        southButtonsPanel.add(addButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(findButton);
        southButtonsPanel.add(signOutButton);

        getContentPane().add(listTitle, BorderLayout.NORTH);
        getContentPane().add(marketProductsListArea, BorderLayout.CENTER);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);






    }

    private class AddProductFrame {

        public AddProductFrame(){

            setSize(250,250);
            setTitle("Add product");
            initAdd();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }

        private void initAdd() {

            JLabel barCodeLog = new JLabel("Barcode: ");
            JLabel modelLog = new JLabel("Model: ");
            JLabel priceLog = new JLabel("Price: ");

            JTextField barCode = new JTextField();
            JTextField model = new JTextField();
            JTextField price = new JTextField();

            JButton addProductButton = new JButton("Add");
            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        iAdminController.addProduct(barCode.getText(), model.getText(), Double.parseDouble(price.getText()) );

                    } catch (ClassCastException e1) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Cannot convert price", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }


                }
            });


        }


    }
}
