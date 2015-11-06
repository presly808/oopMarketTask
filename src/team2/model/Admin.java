package team2.model;

import team2.controller.AdminController;
import team2.view.AdministratorView;

/**
 * Created by serhii on 25.10.15.
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(int id, String login, String pass) {
        super(id, login, pass);
    }

    public void startView(MarketDB marketDB) {
        System.out.println("START ADMIN VIEW !!!!!!!!!!!!!!!!!");
        AdminController adminController = new AdminController(marketDB);
        new AdministratorView(adminController);
    }
}
