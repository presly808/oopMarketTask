package team3.test.frame;

import team3.frame.LoginFrame;
import team3.model.MarketDB;

/**
 * Created by 1 on 01.11.2015.
 */
public class TestLoginFrame {

    public static void main(String[] args) {

        new LoginFrame(new MarketDB());

    }
}
