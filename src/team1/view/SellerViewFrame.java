package team1.view;

/**
 * Created by bizianov on 02.11.2015.
 */

import team1.controller.SellerController;
import team1.model.Bill;
import team1.model.MarketDB;
import team1.model.Product;
import team1.model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SellerViewFrame extends JFrame {
    private SellerController sellerController;
    private MarketDB marketDB;
    private DefaultListModel listOfBillProducts = new DefaultListModel();
    private JList products = new JList(listOfBillProducts);
    private Seller seller;
    private int billCounter=1;
    private Random random = new Random();
    private Bill currentBill = new Bill(billCounter,seller);
    private double totalMoney;
    private JLabel totalPrice = new JLabel("Total: 0.0");
    private JButton scanProduct = new JButton(new ImageIcon("C:\\add.png"));
    private JButton undoButton = new JButton(new ImageIcon("C:\\undo1.png"));

    public SellerViewFrame(SellerController sellerController, Seller seller){
        this.sellerController = sellerController;
        this.marketDB = sellerController.marketDB;
        this.seller = seller;
        setTitle("Seller Console");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init() {
        JPanel productList = new JPanel();
        JPanel actionButtons = new JPanel();
        JPanel info = new JPanel();
        JPanel infoLeft = new JPanel();
        JPanel infoRight = new JPanel();

        actionButtons.setPreferredSize(new Dimension(500, 100));
        info.setPreferredSize(new Dimension(500, 32));
        productList.setLayout(new BorderLayout(5, 5));
        actionButtons.setLayout(new GridLayout(1, 4, 5, 0));
        productList.add(info, BorderLayout.NORTH);
        productList.add(actionButtons, BorderLayout.SOUTH);
        productList.add(new JScrollPane(products), BorderLayout.CENTER);

        JButton newBill = new JButton(new ImageIcon("C:\\new.png"));

        JButton saveButton = new JButton(new ImageIcon("C:\\save.png"));
        newBill.setBorder(BorderFactory.createTitledBorder("New"));
        scanProduct.setBorder(BorderFactory.createTitledBorder("Scan"));
        undoButton.setBorder(BorderFactory.createTitledBorder("Remove"));
        saveButton.setBorder(BorderFactory.createTitledBorder("Save"));
        actionButtons.add(newBill);
        actionButtons.add(scanProduct);
        actionButtons.add(undoButton);
        actionButtons.add(saveButton);

        scanProduct.addActionListener(new ScanProductListener());
        newBill.addActionListener(new NewBillListener());
        undoButton.addActionListener(new UndoListener());

        JLabel userIcon = new JLabel(new ImageIcon("C:\\login.png"));
        JLabel userName = new JLabel(seller.getLogin());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        JLabel currentDate = new JLabel(sdf.format(new Date()));
        JLabel timeIcon = new JLabel(new ImageIcon("C:\\date.png"));

        info.setLayout(new GridLayout(1,2));
        infoLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        infoLeft.add(userIcon);
        infoLeft.add(userName);
        infoRight.add(currentDate);
        infoRight.add(timeIcon);
        info.add(infoLeft);
        info.add(totalPrice);
        info.add(infoRight);

        getContentPane().add(productList);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scanProduct.setEnabled(false);
                undoButton.setEnabled(false);
            }
        });

        newBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scanProduct.setEnabled(true);
                undoButton.setEnabled(true);
            }
        });

    }

    private class ScanProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int size = sellerController.marketDB.getProducts().size();
            Product product = sellerController.marketDB.getProducts().get(random.nextInt(size));
            listOfBillProducts.addElement(product);
            totalMoney += product.getPrice();
            totalPrice.setText(String.format("Total: %.2f",totalMoney));
        }
    }

    private class NewBillListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            currentBill = new Bill(billCounter,seller);
            currentBill.setDate(new Date());
            totalMoney = 0;
            totalPrice.setText("Total: 0.0");
            listOfBillProducts.removeAllElements();
        }
    }

    private class UndoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (products.isSelectionEmpty()){
                JFrame dialogFrame = new JFrame();
                JOptionPane.showMessageDialog(dialogFrame, "Please select product to remove");
            } else {
                JFrame dialogFrame = new JFrame();
                Object[] options = {"Yes","No"};
                int n = JOptionPane.showOptionDialog(dialogFrame, "Are you sure you want to delete?","Confirmation",JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (n==0) {
                    Product product = (Product) products.getSelectedValue();
                    listOfBillProducts.removeElement(product);
                    totalMoney -= product.getPrice();
                    totalPrice.setText(String.format("Total: %.2f", totalMoney));
                }

            }
        }
    }

    private class SaveBillListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            currentBill.setAmountPrice(totalMoney);
            int size = sellerController.marketDB.getDayWish().size();
            String wish = sellerController.marketDB.getDayWish().get(random.nextInt(size));
            currentBill.setDayWish(wish);
            sellerController.marketDB.getBills().add(currentBill);
            billCounter++;

        }
    }
}

