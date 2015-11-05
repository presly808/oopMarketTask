package team1;

import team1.Authentication.LoginPass;
import team1.Authentication.LoginPassFrame;
import team1.controller.LoginController;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Product;
import team1.model.Seller;

/**
 * Created by serhii on 25.10.15.
 */
public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(1, "slava","123456");
        Seller seller = new Seller (2, "serhii", "0987");

        MarketDB marketDB = new MarketDB();
        marketDB.getProducts().add(new Product("48205236","bread",26.5));
        marketDB.getProducts().add(new Product("48205258","milk",28.5));
        marketDB.getProducts().add(new Product("48205289","apple",37.0));
        marketDB.getProducts().add(new Product("123", "Design Patterns", 222.22));
        marketDB.getProducts().add(new Product("456", "JAVA", 333.33));
        marketDB.getAdmins().add(admin);
        marketDB.getSellers().add(seller);

        LoginPassFrame loginPassFrame = new LoginPassFrame(new LoginPass(new LoginController(marketDB)));


    }
}
