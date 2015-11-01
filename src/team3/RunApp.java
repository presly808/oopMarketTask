package team3;

import team3.controller.AdminController;
import team3.controller.IAdminController;
import team3.model.Admin;
import team3.model.MarketDB;
import team3.view.AdminView;


public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(343, "Kolia","1234");

        MarketDB marketDB = new MarketDB();
        IAdminController iAdminController = new AdminController(marketDB);
        AdminView adminView = new AdminView(admin, iAdminController);

        adminView.startMenu();

    }
}
