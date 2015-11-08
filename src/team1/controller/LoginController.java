package team1.controller;


import team1.constant.ProjectConstants;
import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Seller;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginController {

    public static final String ADMIN_USERS_JSON = ProjectConstants.RESOURCES_JSON_PATH +
            "/AdminUsers.json";
    public static final String SELLER_USERS_JSON = ProjectConstants.RESOURCES_JSON_PATH +
            "/SellerUsers.json";
    public MarketDB marketDB;

    public LoginController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    public void updateAdmins() throws ParseException {
        String path = ADMIN_USERS_JSON;
        File adminFile = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(adminFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(line);
            Admin res = new Admin();
            res.setId((int) (long) obj.get("id"));
            res.setLogin((String) obj.get("login"));
            res.setPass((String) obj.get("pass"));
            System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getAdmins().add(res);
        }

    }

    public void updateSellers() throws ParseException {
        String path = SELLER_USERS_JSON;
        File sellerFile = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(sellerFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(line);
            Seller res = new Seller();
            res.setId((int) (long) obj.get("id"));
            res.setLogin((String) obj.get("login"));
            res.setPass((String) obj.get("pass"));
            System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getSellers().add(res);
        }

    }
}
