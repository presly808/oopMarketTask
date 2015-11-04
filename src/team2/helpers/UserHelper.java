package team2.helpers;

import team2.model.Admin;
import team2.model.Guest;
import team2.model.Seller;
import team2.model.User;
import team2.helpers.IOHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Dima on 01.11.2015.
 */
public class UserHelper {
    public static String usedDBFilePath = "D:\\oopMarketTask\\src\\team2\\users.db!";

    public static ArrayList<User> loadUsersDB() {
        ArrayList<User> usersDB;
        try {
            usersDB = (ArrayList<User>) IOHelper.loadObj(usedDBFilePath);
            return usersDB;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveUsersDB(ArrayList<User> usersDB) {
        IOHelper.saveObj(usedDBFilePath, usersDB);
    }

    public static User getUser(User user) {
        if (user != null) {
            return user;
        }

        return null;//new UserGuest();
    }
/*

    public static void main(String[] args) {
        ArrayList<User> testUserDB = new ArrayList<>();

        testUserDB.add( new Guest(0,"guest","guest"));
        testUserDB.add( new Admin(1,"admin","admin"));
        testUserDB.add( new Seller(2,"cashmen","cashmen"));
        saveUsersDB(testUserDB);

        testUserDB = loadUsersDB();

        System.out.println(testUserDB.get(0).toString());
        System.out.println(testUserDB.get(1).toString());
        System.out.println(testUserDB.get(2).toString());
    }
*/
}

