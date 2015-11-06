package team1.view;

/**
 * Created by bizianov on 02.11.2015.
 */

import team1.controller.SellerController;
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
    private Random random = new Random();

    public SellerViewFrame(SellerController sellerController, Seller seller){
        this.sellerController = sellerController;
        this.marketDB = sellerController.marketDB;
        this.seller = seller;
        setTitle("Seller Console");
        setSize(500, 800);
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
        JButton scanProduct = new JButton(new ImageIcon("C:\\add.png"));
        JButton undoButton = new JButton(new ImageIcon("C:\\undo1.png"));
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
        info.add(infoRight);


        getContentPane().add(productList);

    }

    private class ScanProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int size = sellerController.marketDB.getProducts().size();
            Product product = sellerController.marketDB.getProducts().get(random.nextInt(size));
            listOfBillProducts.addElement(product);
        }
    }
}

