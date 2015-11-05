package team3.model;

/**
 * Created by serhii on 25.10.15.
 */
public class User {

    private int id;
    private String login;
    private String pass;

    public User() {
    }

    public User(String pass, String login) {
        this.pass = pass;
        this.login = login;
    }

    public User(int id,String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != User.class && obj.getClass() != Admin.class  && obj.getClass() != Seller.class && obj.getClass() != Manager.class)
            return false;

        if ((getClass() == Admin.class && obj.getClass() == Seller.class) ||
                (getClass() == Admin.class && obj.getClass() == Manager.class) ||
                (getClass() == Seller.class && obj.getClass() == Admin.class) ||
                (getClass() == Seller.class && obj.getClass() == Manager.class) ||
                (getClass() == Manager.class && obj.getClass() == Admin.class) ||
                (getClass() == Manager.class && obj.getClass() == Seller.class))
            return false;


        User user = (User) obj;

        return login != null && pass != null ? login.equals(user.login) && pass.equals(user.pass) : false;

    }
}
