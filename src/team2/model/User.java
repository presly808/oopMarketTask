package team2.model;

import java.io.Serializable;

/**
 * Created by serhii on 25.10.15.
 */
public class User implements Serializable{

    private int id;
    private String login;
    private String pass;

    public User() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return pass.equals(user.pass);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + pass.hashCode();
        return result;
    }

    public void startView(MarketDB marketDB){

    }

    public boolean isLoginTheSame(String login) {
        return this.login.equals(login);
    }
}
