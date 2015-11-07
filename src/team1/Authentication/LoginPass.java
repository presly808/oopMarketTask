package team1.Authentication;

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

public class LoginPass {
    private MarketDB marketDB;
    private LoginController loginController;
    private String login;
    private String password;
    private User user;

    public LoginPass(LoginController loginController) {
        this.loginController = loginController;
        this.marketDB = loginController.marketDB;
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
        String path = "C:\\AdminUsers.json";
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
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getAdmins().add(res);
        }

    }

    public void updateSellers() throws ParseException {
        String path = "C:\\SellerUsers.json";
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
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getSellers().add(res);
        }

    }

    public User getUser() {
        return user;
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }
}
