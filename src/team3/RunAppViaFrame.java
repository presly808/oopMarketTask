package team3;

import team3.frame.LoginFrame;
import team3.model.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 1 on 02.11.2015.
 */
public class RunAppViaFrame {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        MarketDB marketDB = new MarketDB();

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("00101", "tea", 15.60));
        products.add(new Product("00111", "sugar", 17.60));
        products.add(new Product("01111", "coffee", 5.60));
        products.add(new Product("11111", "candles", 19.30));
        products.add(new Product("11110", "bread", 7.60));

        marketDB.setProducts(products);

        ArrayList<User> users = new ArrayList<>();
        users.add(new Admin(343, "Kolia", "1234"));
        users.add(new Seller(346, "Ivan", "9856"));

        marketDB.setUsers(users);

        new LoginFrame(marketDB);


    }
}




