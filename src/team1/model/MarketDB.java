package team1.model;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public class MarketDB {


    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Seller> sellers = new ArrayList<>();
    private ArrayList<Bill> bills = new ArrayList<>();
    private double amountMoney;

    public MarketDB() {
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

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
}
