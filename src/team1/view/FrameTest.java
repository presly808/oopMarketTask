package team1.view;

import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Product;
import team1.model.Seller;

/**
 * Created by 1 on 01.11.2015.
 */
public class FrameTest {
    public static void main(String[] args) {
        Admin admin = new Admin(1, "slava","123456");
        Seller seller = new Seller (2, "serhii", "0987");
        Product product1 = new Product("123", "Design Patterns", 222.22);
        Product product2 = new Product("456", "JAVA", 333.33);

        MarketDB marketDB = new MarketDB();
        marketDB.getAdmins().add(admin);
        marketDB.getSellers().add(seller);

        marketDB.getProducts().add(product1);
        marketDB.getProducts().add(product2);

        AdminViewFrame adminViewFrame = new AdminViewFrame(marketDB);
    }

}
