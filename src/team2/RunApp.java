package team2;


import team2.controller.StartViewController;
import team2.model.MarketDB;
import team2.view.StartView;

public class RunApp {

    public static void main(String[] args) {

        // RUN ONLY FIRST TIME!!!! AND COMMENT!!!!
        //UserHelper.makeDefaultUserDB();

        MarketDB marketDB = new MarketDB();
        StartViewController startViewController = new StartViewController(marketDB);

        new StartView(startViewController);

    }

}
