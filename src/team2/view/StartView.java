package team2.view;

import team2.controller.StartViewController;
import team2.model.Guest;
import team2.model.User;

import javax.swing.*;

/**
 * Created by Dima on 04.11.2015.
 */
public class StartView extends JFrame {
    private User user;

    public StartView(User user, StartViewController startViewController) {
        this.user = user;
    }

    public void startMenu() {
        System.out.println(this.user.toString());

        setTitle("How are you?");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

}


