package team2.controller;

import team2.model.Guest;
import team2.model.MarketDB;
import team2.model.User;

/**
 * Created by Dima on 04.11.2015.
 */
public class StartViewController {
    private MarketDB marketDB;

    public StartViewController(MarketDB db){
        this.marketDB = db;
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    public User checkLoginPassw(String login, String password) {
        User userWhoTryToEnter = new User(0,login,password);
        userWhoTryToEnter = marketDB.isUserValid(userWhoTryToEnter);
         if ( userWhoTryToEnter instanceof Guest ) {
             System.out.println( "User guest or not valid" );
             return userWhoTryToEnter;
         } else {
             marketDB.setUser(userWhoTryToEnter);
             //System.out.println( "valid user " + marketDB.getUser().toString());
             return marketDB.getUser();

         }
    }
}
