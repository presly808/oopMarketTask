package team2.controller;

import static team2.view.CodeContainer.*;

import team2.helpers.UserHelper;
import team2.model.*;

import java.util.ArrayList;

public class AdminController implements IAdminController {

    private MarketDB marketDB;

    public AdminController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public void addProduct(String barCode, String model, double price) {
        Product product = new Product(barCode, model, price);
        marketDB.getProducts().add(product);
    }

    @Override
    public boolean deleteProduct(String barCode) {
        Product tempProductForDel = new Product();
        tempProductForDel.setBarCode(barCode);
        return marketDB.getProducts().remove(tempProductForDel);
    }

    @Override
    public ArrayList<Product> getAll() {
        return marketDB.getProducts();
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public Product findProductByCode(String barCode) {
        Product tempProductForFind = new Product();
        tempProductForFind.setBarCode(barCode);
        int index = marketDB.getProducts().indexOf(tempProductForFind);

        return marketDB.getProducts().get(index);
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    @Override
    public int createUser(String login, String passw, String role) {
        User newUser = null;

        if ((login.isEmpty()) || (passw.isEmpty())) return FIELD_EMPTY_CODE;

        if (role.equals("Guest")) return TRY_CREATE_GUEST_CODE;

        if (marketDB.getUserByLogin(login) != null) return FIELD_EMPTY_CODE;

        int newUserId = marketDB.getUsers().size();

        if (role.equals("Admin")) {
            newUser = new Admin(newUserId, login, passw);
        } else if (role.equals("Seller")) {
            newUser = new Seller(newUserId, login, passw);
        }

        marketDB.getUsers().add(newUser);
        UserHelper.saveUsersDB(marketDB.getUsers());

        return newUserId;
    }
}
