package team1.view;

/**
 * Created by bizianov on 02.11.2015.
 */
import team1.controller.SellerController;
import team1.model.MarketDB;

import javax.swing.*;
import java.awt.*;

public class SellerViewFrame extends JFrame {
    private SellerController sellerController;
    private MarketDB marketDB;
    private DefaultListModel productListModel = new DefaultListModel();
    private JList products = new JList(productListModel);

    public SellerViewFrame(SellerController sellerController){
        this.sellerController = sellerController;
        this.marketDB = sellerController.marketDB;
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

        actionButtons.setPreferredSize(new Dimension(500, 100));
        productList.setLayout(new BorderLayout(5, 5));
        actionButtons.setLayout(new GridLayout(1, 4, 5, 0));
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
        getContentPane().add(productList);

    }
}

