package team1;

import team1.controller.AdminController;
import team1.controller.IAdminController;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.view.AdminView;


public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(343, "Kolia","1234");

        MarketDB marketDB = new MarketDB();
        IAdminController iAdminController = new AdminController(marketDB);
        AdminView adminView = new AdminView(admin, iAdminController);

        adminView.startMenu();

    }
}
