package team2.model;

import team2.controller.StartViewController;
import team2.view.StartView;

/**
 * Created by Dima on 04.11.2015.
 */
public class Guest extends User {

    public Guest() {
    }

    public Guest(int id, String login, String pass) {
        super(id, login, pass);
    }

    public void startView(MarketDB marketDB) {
        System.out.println("START GUEST VIEW !!!!!!!!!!!!!!!!!");
        StartViewController startViewController = new StartViewController(marketDB);
        new StartView(startViewController);
    }
}
