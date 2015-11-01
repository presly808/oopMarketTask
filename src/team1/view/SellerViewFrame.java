package team1.view;

/**
 * Created by bizianov on 02.11.2015.
 */
import team1.model.MarketDB;

import javax.swing.*;

public class SellerViewFrame extends JFrame {
    private MarketDB marketDB;

    public SellerViewFrame(MarketDB marketDB){
        this.marketDB = marketDB;
        setTitle("Seller Console");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init() {

    }
}

