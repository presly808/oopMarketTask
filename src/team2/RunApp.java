package team2;


import team2.controller.AdminController;
import team2.controller.StartViewController;
import team2.model.Admin;
import team2.model.Guest;
import team2.model.MarketDB;
import team2.model.User;
import team2.view.AdminView;
import team2.controller.IAdminController;
import team2.view.StartView;

public class RunApp {

    public static void main(String[] args) {
        User user = new Guest(0,"guest","guest");

        MarketDB marketDB = new MarketDB();
        StartViewController startViewController = new StartViewController(marketDB);
        StartView StartView = new StartView(user, startViewController);

        StartView.startMenu();

    }
}
