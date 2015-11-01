package team1.Authentication;

import team1.model.Admin;
import team1.model.MarketDB;
import team1.model.Seller;
import team1.model.User;
import team1.view.AdminView;

import java.util.Scanner;

public class LoginPass {
    private MarketDB marketDB;
    private String login;
    private String password;
    private User user;

    public LoginPass(MarketDB marketDB) {
        this.marketDB = marketDB;
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

}
