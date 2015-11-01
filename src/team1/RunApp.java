package team1;

import team1.Authentication.LoginPassFrame;
import team1.controller.AdminController;
import team1.controller.SellerController;
import team1.model.*;
import team1.view.AdminView;
import team1.Authentication.LoginPass;
import team1.view.SellerView;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(1, "slava","123456");
        Seller seller = new Seller (2, "serhii", "0987");

        MarketDB marketDB = new MarketDB();
        marketDB.getAdmins().add(admin);
        marketDB.getSellers().add(seller);
        AdminController adminController = new AdminController(marketDB);
        SellerController sellerController = new SellerController(marketDB);
        AdminView adminView = new AdminView(admin,adminController);
        SellerView sellerView = new SellerView(seller,sellerController);

        LoginPass loginPass = new LoginPass(marketDB);

        LoginPassFrame loginPassFrame = new LoginPassFrame(marketDB);
        /*User user = loginPass.login();

        if (user instanceof Admin){
            adminView.startMenu();
        } else if (user instanceof Seller){
            sellerView.startMenu();
        } else {
            System.out.println("Authentication failed");
        }*/


        // serhii comment


    }
}
