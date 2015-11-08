package team1.controller;

import static team1.constant.ProjectConstants.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import team1.controller.LoginController;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Seller;
import team1.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// TODO set full coorect name
public class LoginWindowController {


    public static final String ADMINS_JSON_PATH = RESOURCES_JSON_PATH + "/AdminUsers.json";
    public static final String SELLER_JSON_PATH = RESOURCES_JSON_PATH + "/SellerUsers.json";

    private MarketDB marketDB;

    private LoginController loginController;
    private String login;
    private String password;

    private User user;

    private JSONParser jsonParser;

    public LoginWindowController(LoginController loginController) {
        this.loginController = loginController;
        this.marketDB = loginController.marketDB;

        jsonParser = new JSONParser();
    }

    public User loginFrame(String login, String password){
        user = null;
        for (Admin admin : marketDB.getAdmins()){
            if (admin.getLogin().equals(login) && admin.getPass().equals(password)){
                user = admin;
            }
        }
        if (user==null){
            for (Seller seller : marketDB.getSellers()){
                if (seller.getLogin().equals(login) && seller.getPass().equals(password)){
                    user = seller;
                }
            }
        }
        return user;
    }

    public void updateAdmins() throws ParseException {
        File adminFile = new File(ADMINS_JSON_PATH);
        Scanner sc = null;

        try {
            sc = new Scanner(adminFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();

            JSONObject obj = (JSONObject) jsonParser.parse(line);

            Admin res = convertAdmin(obj);
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getAdmins().add(res);
        }

    }

    private Admin convertAdmin(JSONObject obj) {
        Admin res = new Admin();
        res.setId((int) (long) obj.get("id"));
        res.setLogin((String) obj.get("login"));
        res.setPass((String) obj.get("pass"));
        return res;
    }

    public void updateSellers() throws ParseException {
        File sellerFile = new File(SELLER_JSON_PATH);
        Scanner sc = null;
        try {
            sc = new Scanner(sellerFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();

            JSONObject obj = (JSONObject) jsonParser.parse(line);

            Seller res = convertSeller(obj);
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getSellers().add(res);
        }

    }

    private Seller convertSeller(JSONObject obj) {
        Seller res = new Seller();
        res.setId((int) (long) obj.get("id"));
        res.setLogin((String) obj.get("login"));
        res.setPass((String) obj.get("pass"));
        return res;
    }

    public User getUser() {
        return user;
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }
}
