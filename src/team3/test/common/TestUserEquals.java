package team3.test.common;

import team3.model.*;

import java.util.ArrayList;

/**
 * Created by 1 on 02.11.2015.
 */
public class TestUserEquals {

    public static void main(String[] args) {


        User user0 = new Admin(346, "Ivan", "9856");
        User user1 = new Seller(346, "Ivan", "9856");
        User user2 = new User();
        user2.setLogin("Petr");
        user2.setPass("8888");
        User user3 = new Manager(348, "Petr", "8888");


        System.out.println(user0.equals(user1));
        System.out.println(user1.equals(user0));

        System.out.println(user0.equals(user2));

        System.out.println(user1.equals(user2));
        System.out.println(user2.equals(user1));

        System.out.println(user3.equals(user0));
        System.out.println(user3.equals(user1));
        System.out.println(user3.equals(user2));
        System.out.println(user2.equals(user3));




        ArrayList<User> users = new ArrayList<>();




        users.add(user0);
        users.add(user1);
        users.add(user3);

//        User userTemp = new User();
//        userTemp.setLogin("Petr");
//        userTemp.setPass("8888");

        System.out.println(users.indexOf(user2));
//
//        MarketDB marketDB = new MarketDB();
//
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.add(new Product("00101", "tea", 15.60));
//        products.add(new Product("00111", "sugar", 17.60));
//        products.add(new Product("01111", "coffee", 5.60));
//        products.add(new Product("11111", "candles", 19.30));
//        products.add(new Product("11110", "bread", 7.60));
//
//        marketDB.setProducts(products);
//
//        ArrayList<User> users = new ArrayList<>();
//        users.add(new Admin(343, "Kolia", "1234"));
//
//
//        users.add(new Seller(346, "Ivan", "9856"));
//
//        marketDB.setUsers(users);
//
//        marketDB.getUsers().indexOf()
    }
}
