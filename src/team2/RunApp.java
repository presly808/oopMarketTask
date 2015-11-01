package market;

import market.controller.AdminController;
import market.controller.IAdminController;
import market.model.Admin;
import market.model.MarketDB;
import market.view.AdminView;


public class RunApp {

    public static void main(String[] args) {
        Admin admin = new Admin(343, "Kolia","1234");

        MarketDB marketDB = new MarketDB();
        IAdminController iAdminController = new AdminController(marketDB);
        AdminView adminView = new AdminView(admin, iAdminController);

        adminView.startMenu();

    }
}
