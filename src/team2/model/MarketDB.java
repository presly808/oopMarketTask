package team2.model;

import team2.helpers.UserHelper;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public class MarketDB {

    User user;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Bill> bills = new ArrayList<>();
    private double amountMoney;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public MarketDB() {
        this.users = UserHelper.loadUsersDB();
        this.user = new Guest(0,"guest","guest");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
/*
    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }
*/
    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public User isUserValid(User user){
        for (int i = 0; i < users.size(); i++) {
            if ( users.get(i).equals(user) ) {
                return users.get(i);
            }
        }
        return users.get(0); /// users[0] - always guest. default user.
    }
}
