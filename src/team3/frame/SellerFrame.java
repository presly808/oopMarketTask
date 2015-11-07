package team3.frame;

import team3.controller.ISellerController;
import team3.controller.ManagerController;
import team3.controller.SellerController;
import team3.model.Bill;
import team3.model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 06.11.2015.
 */
public class SellerFrame extends JFrame {

    Seller user;
    ISellerController iSellerController;


    public SellerFrame(Seller user, ISellerController iSellerController){

        this.user = user;
        this.iSellerController = iSellerController;

        setLocationRelativeTo(null);
        setSize(500, 500);
        setTitle(String.format("Seller: %s", user.getLogin()));
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init(){

        JLabel listTitle = new JLabel();
        JTextArea marketProductListArea = new JTextArea();

        JTextField filePathField = new JTextField();

        JButton scanButton = new JButton("Scan");
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ScanProductFrame();

            }});

        JButton PaymentButton = new JButton("Payment");
        PaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new PaymentFrame();

            }});

        JButton eraseButton = new JButton("Erase");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new EraseProductFrame();

            }});

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(SellerFrame.this, "Are you sure you want to cancel?", "CANCEL",JOptionPane.YES_NO_OPTION);

            }});

        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                dispose();
                new LoginFrame(((SellerController) iSellerController).getMarketDB());

            }});


        JPanel southButtonsPanel = new JPanel(new GridLayout(1,4));

        southButtonsPanel.add(scanButton);
        southButtonsPanel.add(PaymentButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(cancelButton);
        southButtonsPanel.add(signOutButton);

        getContentPane().add(listTitle, BorderLayout.NORTH);
        getContentPane().add(marketProductListArea, BorderLayout.CENTER);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
    }


    private class ScanProductFrame extends JFrame {

        public ScanProductFrame(){

            setLocationRelativeTo(SellerFrame.this);
            setSize(250,100);
            setTitle("Scan Product");
            initScan();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }

        private void initScan(){

            JLabel barCodeLog = new JLabel("Barcode:");
            JTextField barCode = new JTextField();

            JButton scanProductButton = new JButton("Enter");
            scanProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JPanel inputPanel = new JPanel(new GridLayout(1,2));

            inputPanel.add(barCodeLog);
            inputPanel.add(barCode);


            getContentPane().add(inputPanel, BorderLayout.NORTH);
            getContentPane().add(scanProductButton, BorderLayout.SOUTH);

        }}

    private class PaymentFrame extends JFrame{

        public PaymentFrame(){

            setLocationRelativeTo(SellerFrame.this);
            setSize(250,150);
            setTitle("Payment");
            initPayment();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }

        private void initPayment() {

            JLabel totalLog = new JLabel("Total: ");
            JLabel cashReceivedLog = new JLabel("Cash received: ");


            JLabel total = new JLabel("some value");
            JTextField cashReceived = new JTextField();




            JButton paymentButton = new JButton("Calculate change");
            paymentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){

                    cashReceived.setEditable(false);

                    JOptionPane.showConfirmDialog(SellerFrame.this, String.valueOf("some value"), "CHANGE", JOptionPane.DEFAULT_OPTION);

                    paymentButton.setText("Print bill");
                    paymentButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {



                        }
                    });

                }
            });

            JPanel inputPanel = new JPanel(new GridLayout(2,2));

            inputPanel.add(totalLog);
            inputPanel.add(total);
            inputPanel.add(cashReceivedLog);
            inputPanel.add(cashReceived);

            getContentPane().add(inputPanel, BorderLayout.NORTH);
            getContentPane().add(paymentButton, BorderLayout.SOUTH);
        };
    }

    private class EraseProductFrame extends JFrame{

        public EraseProductFrame() {

            setLocationRelativeTo(SellerFrame.this);
            setSize(250, 100);
            setTitle("Erase product");
            initErase();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }

        private void initErase() {

            JLabel barCodeLog = new JLabel("Barcode:");
            JTextField barCode = new JTextField();


            JButton eraseProductButton = new JButton("Erase");
            eraseProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });

            JPanel inputPanel = new JPanel(new GridLayout(1,2));

            inputPanel.add(barCodeLog);
            inputPanel.add(barCode);


            getContentPane().add(inputPanel, BorderLayout.NORTH);
            getContentPane().add(eraseProductButton, BorderLayout.SOUTH);


        }
    }


}
