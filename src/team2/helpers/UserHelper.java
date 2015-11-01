package team2;

import team2.model.User;
import team2.helpers.IOHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Dima on 01.11.2015.
 */
public class UserHelper {
    public static String usedDBFilePath = "C:\\!J\\ArtCode\\ACO8\\src\\market\\users.db!";

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

        testUserDB.add( new Admin(0,"guest","guest"));
        testUserDB.add( new Admin(1,"admin","admin"));
        saveUsersDB(testUserDB);

        testUserDB = loadUsersDB();

        System.out.println(testUserDB.get(0).toString());
        System.out.println(testUserDB.get(1).toString());
    }
*/
}

