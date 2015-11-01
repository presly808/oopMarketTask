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

    public User login(){
        user = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("login: ");
        login = sc.nextLine();
        for (Admin admin : marketDB.getAdmins()){
            if (admin.getLogin().equals(login)){
                user = admin;
            }
        }
        if (user==null){
            for (Seller seller : marketDB.getSellers()){
                if (seller.getLogin().equals(login)){
                    user = seller;
                }
            }
        }


        if (user==null){
            System.out.println("User doesn't exist");
            return null;
        } else {
            System.out.println("password: ");
            password = sc.nextLine();
            if (user.getPass().equals(password)){
                return user;
            } else {
                System.out.println("Wrong password\n");
                return null;
            }
        }

    }
}
