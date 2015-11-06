package team2.controller;

import team2.model.MarketDB;

/**
 * Created by dima on 06.11.2015.
 */
public class SellerController {
    private MarketDB marketDB;

    public SellerController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }
}
