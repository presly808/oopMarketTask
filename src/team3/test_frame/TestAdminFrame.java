package team3.test_frame;

import team3.frame.AdminFrame;

import team3.controller.AdminController;
import team3.model.MarketDB;

/**
 * Created by 1 on 01.11.2015.
 */
public class TestAdminFrame {

    public static void main(String[] args) {
        new AdminFrame(new AdminController(new MarketDB()));
    }

}
