package team2.model;

import team2.controller.SellerController;
import team2.view.SellerView;

/**
 * Created by serhii on 25.10.15.
 */
public class Seller extends User {

    public Seller(int id, String login, String pass) {
        super(id, login, pass);
    }

    public Seller() {
    }

    public void startView(MarketDB marketDB) {
        System.out.println("START SELLER VIEW !!!!!!!!!!!!!!!!!");
        SellerController sellerController = new SellerController(marketDB);
        new SellerView(sellerController);
    }
}
