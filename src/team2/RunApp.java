package team2;

import team2.controller.AdminController;
import team2.controller.IAdminController;
import team2.model.Admin;
import team2.model.MarketDB;
import team2.view.AdminView;


public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(343, "Kolia","1234");

        MarketDB marketDB = new MarketDB();
        IAdminController iAdminController = new AdminController(marketDB);
        AdminView adminView = new AdminView(admin, iAdminController);

        adminView.startMenu();

    }
}
