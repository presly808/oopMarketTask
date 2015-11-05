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

                new  EraseProductFrame();

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








    private class AddProductFrame extends JFrame{

        public AddProductFrame(){

            setSize(250,175);
            setTitle("Add product");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init() {

            JLabel barCodeLog = new JLabel("Barcode: ");
            JLabel modelLog = new JLabel("Model: ");
            JLabel priceLog = new JLabel("Price: ");

            JTextField barCode = new JTextField();
            JTextField model = new JTextField();
            JTextField price = new JTextField();

            JButton addProductButton = new JButton("Product adding");
            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {

                        boolean added = iAdminController.addProduct(barCode.getText(), model.getText(), Double.parseDouble(price.getText()));

                        if (added){
                            JOptionPane.showConfirmDialog(AdminFrame.this, "Product has been successfully added.", "SUCCESS", JOptionPane.DEFAULT_OPTION);
                        } else  if (!added){
                            JOptionPane.showMessageDialog(AdminFrame.this, "This product (barcode) already exists in market database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Cannot convert price", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }


                }
            });


            JPanel northInputPanel = new JPanel(new GridLayout(3,2));

            northInputPanel.add(barCodeLog);
            northInputPanel.add(barCode);
            northInputPanel.add(modelLog);
            northInputPanel.add(model);
            northInputPanel.add(priceLog);
            northInputPanel.add(price);

            getContentPane().add(northInputPanel, BorderLayout.NORTH);

            getContentPane().add(addProductButton, BorderLayout.SOUTH);


        }


    }

    private class EraseProductFrame extends JFrame{

        public EraseProductFrame() {

            setSize(250,100);
            setTitle("Product erasing");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init(){

            JLabel barCodeLog = new JLabel("Barcode: ");

            JTextField barCode = new JTextField();

            JButton eraseProductButton = new JButton("Erase");
            eraseProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean erased = iAdminController.deleteProduct(barCode.getText());

                    if (erased) {
                        JOptionPane.showConfirmDialog(AdminFrame.this, "Product has been successfully deleted.", "SUCCESS", JOptionPane.DEFAULT_OPTION);
                    } else if (!erased) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "This product (barcode) doesn't exist in market database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            JPanel northInputPanel = new JPanel(new GridLayout(1,2));

            northInputPanel.add(barCodeLog);
            northInputPanel.add(barCode);

            getContentPane().add(northInputPanel, BorderLayout.NORTH);
            getContentPane().add(eraseProductButton, BorderLayout.SOUTH);


        }
    }
}
