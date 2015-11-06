package team2.view;

import team2.controller.SellerController;

/**
 * Created by dima on 06.11.2015.
 */
public class SellerView {
    private SellerController sellerController;

    public SellerView(SellerController sellerController) {
        this.sellerController = sellerController;
        startView();
    }

    public void startView() {
        System.out.println("now logged in: " + sellerController.getMarketDB().getUser().toString());
        System.out.println("Seller view started");
    }
}
