package team2.model;

/**
 * Created by serhii on 25.10.15.
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(int id, String login, String pass) {
        super(id, login, pass);
    }

    public void startView() {
        System.out.println("STARTING ADMIN VIEW!!!!!!!!!!!!!");
    }
}
