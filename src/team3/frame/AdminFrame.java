package team3.frame;

import org.omg.IOP.ExceptionDetailMessage;
import team3.controller.IAdminController;
import team3.model.Admin;
import team3.model.MarketDB;
import team3.model.Product;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by 1 on 01.11.2015.
 */
public class AdminFrame extends JFrame {

    private Admin user;
    private IAdminController iAdminController;

    public AdminFrame (Admin user, IAdminController iAdminController){

        this.user = user;
        this.iAdminController = iAdminController;

        setLocationRelativeTo(null);
        setSize(500, 500);
        setTitle(String.format("Admin: %s",user.getLogin()));
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {



        JLabel listTitle = new JLabel("Product list:");

        JTextArea marketProductsListArea = new JTextArea(iAdminController.getAllProductsString());
        marketProductsListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(marketProductsListArea);


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

                new FindProductFrame();

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
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);






    }








    private class AddProductFrame extends JFrame{

        public AddProductFrame(){

            setLocationRelativeTo(AdminFrame.this);
            setSize(250,150);
            setTitle("Product adding");
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

            JButton addProductButton = new JButton("Add");
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

            setLocationRelativeTo(AdminFrame.this);
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

    private class FindProductFrame extends JFrame{


        public FindProductFrame() {

            setLocationRelativeTo(AdminFrame.this);
            setSize(250,100);
            setTitle("Product finding");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init() {


            JLabel barCodeLog = new JLabel("Barcode: ");

            JTextField barCode = new JTextField();

            JButton findProductButton = new JButton("Find");
            findProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Product found = iAdminController.findProductByCode(barCode.getText());

                    if (found == null){
                        JOptionPane.showMessageDialog(AdminFrame.this, "This product (barcode) doesn't exist in market database.", "ERROR", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showConfirmDialog(AdminFrame.this, found.toString(), "SUCCESS", JOptionPane.DEFAULT_OPTION);

                    }
                }
            });

            JPanel northInputPanel = new JPanel(new GridLayout(1,2));

            northInputPanel.add(barCodeLog);
            northInputPanel.add(barCode);

            getContentPane().add(northInputPanel, BorderLayout.NORTH);
            getContentPane().add(findProductButton, BorderLayout.SOUTH);


        }


    }
}
