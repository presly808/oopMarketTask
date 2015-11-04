package team2;


import team2.controller.AdminController;
import team2.controller.StartViewController;
import team2.helpers.UserHelper;
import team2.model.Admin;
import team2.model.Guest;
import team2.model.MarketDB;
import team2.model.User;
import team2.view.AdminView;
import team2.controller.IAdminController;
import team2.view.StartView;

import javax.swing.*;
import java.awt.*;

public class RunApp {

    public static void main(String[] args) {

        // RUN ONLY FIRST TIME!!!! AND COMMENT!!!!
        //UserHelper.makeDefaultUserDB();

        User user = new Guest(0, "guest", "guest");

        MarketDB marketDB = new MarketDB();
        StartViewController startViewController = new StartViewController(marketDB);
        StartView startView = new StartView(user, startViewController);

        startView.startMenu();
        // tODO перенести вызов  менюшки в контроллер, менять юзера системы при успешном логине, вызывать следующие менюхи.
    }

}
