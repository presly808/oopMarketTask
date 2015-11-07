package team3.controller;

import team3.model.Admin;
import team3.model.MarketDB;
import team3.model.Seller;
import team3.model.User;

import java.util.ArrayList;


/**
 * Created by 1 on 05.11.2015.
 */
public class ManagerController implements IManagerController{

    private MarketDB marketDB;

    public ManagerController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public ArrayList<Admin> getAdmins() {

        ArrayList<Admin> admins = new ArrayList<>();

        for (User user : marketDB.getUsers()){
            if (user instanceof Admin){
                admins.add((Admin) user);
            }
        }

        return admins;
    }

    @Override
    public ArrayList<Seller> getSellers() {

        ArrayList<Seller> sellers = new ArrayList<>();

        for (User user : marketDB.getUsers()){
            if (user instanceof Seller){
                sellers.add((Seller) user);
            }
        }

        return sellers;
    }

    @Override
    public String getAdminsString() {

        String admins = "";

        for (Admin admin : getAdmins()){
            admins += admin.toString() + "\n";
        }

        return admins;
    }

    @Override
    public String getSellersString() {

        String sellers = "";

        for (Seller seller : getSellers()){
            sellers += seller.toString() + "\n";
        }

        return sellers;
    }

    @Override
    public boolean addAdmin(int id, String login, String pass) {

        for (User user : marketDB.getUsers()){
            if (user.getId() == id){
                return false;
            }
        }

        return marketDB.getUsers().add(new Admin(id,login,pass));
    }

    @Override
    public boolean addSeller(int id, String login, String pass) {

        for (User user : marketDB.getUsers()){
            if (user.getId() == id){
                return false;
            }
        }

        return marketDB.getUsers().add(new Seller(id,login,pass));
    }

    @Override
    public boolean deleteUser(int id) {

        for (User user : marketDB.getUsers()){
            if (user.getId() == id){
                return marketDB.getUsers().remove(user);
            }
        }

        return false;

    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    public void setMarketDB(MarketDB marketDB) {
        this.marketDB = marketDB;
    }
}
