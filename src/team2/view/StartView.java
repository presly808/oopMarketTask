package team2.view;

import team2.controller.StartViewController;
import team2.model.Guest;
import team2.model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dima on 04.11.2015.
 */
public class StartView extends JFrame {
    private User user;
    private JTextField login;
    private JTextField password;

    public StartView(User user, StartViewController startViewController) {
        this.user = user;
    }

    public void startMenu() {
        System.out.println(this.user.toString());

        setTitle("How are you?");
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        login = new JTextField("");
        password = new JTextField("");

        //setLayout(new GridLayout(2,2));
        getContentPane().add(login);
        getContentPane().add(password);

        setVisible(true);
    }

}


