package team3.frame;

import javax.swing.*;

/**
 * Created by 1 on 01.11.2015.
 */
public class LoginFrame extends JFrame{

    private String name;
    private JPasswordField passwordField;

    public LoginFrame() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
