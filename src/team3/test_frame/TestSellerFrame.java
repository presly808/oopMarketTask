package team3.test_frame;

import team3.frame.SellerFrame;

import team3.controller.SellerController;
import team3.model.MarketDB;
import team3.model.Seller;

/**
 * Created by Evgenia on 11/1/15.
 */
public class TestSellerFrame {

    public static void main(String[] args) {
        new SellerFrame(new Seller(), new SellerController(new MarketDB()));

    }
}
