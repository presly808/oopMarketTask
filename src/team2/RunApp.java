package team2;


import team2.helpers.UserHelper;
import team2.model.MarketDB;

public class RunApp {

    public static void main(String[] args) {

        // RUN ONLY FIRST TIME!!!! AND COMMENT!!!!
        //UserHelper.makeDefaultUserDB();

        MarketDB marketDB = new MarketDB();
        marketDB.getUser().startView(marketDB);

    }

}
