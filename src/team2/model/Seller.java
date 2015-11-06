package team2.model;

/**
 * Created by serhii on 25.10.15.
 */
public class Seller extends User {

    public Seller(int id, String login, String pass) {
        super(id, login, pass);
    }

    public Seller() {
    }

    public void startView() {
        System.out.println("START SELLER VIEW !!!!!!!!!!!!!!!!!");
    }
}
