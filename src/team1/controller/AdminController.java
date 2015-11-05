package team1.controller;

import team1.model.*;


import java.util.ArrayList;
import java.util.Arrays;


public class AdminController implements IAdminController {

    private MarketDB marketDB;

    public AdminController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public Product addProduct(String barCode, String model, double price) {
        Product product = new Product(barCode, model, price);
        marketDB.getProducts().add(product);
        return  product;
    }

    @Override
    public void renameProduct(Product product, String barCode, String model, double price) {
        product.setBarCode(barCode);
        product.setModel(model);
        product.setPrice(price);
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
    public ArrayList<User> getAllUsers() {
        ArrayList users = new ArrayList();
        ArrayList admins = marketDB.getAdmins();
        ArrayList sellers = marketDB.getSellers();
        users.addAll(admins);
        users.addAll(sellers);

   //System.arraycopy(admins, 0, users, 0, admins.size());
   //System.arraycopy(sellers, 0, users, admins.size(), sellers.size());
//
       return users;

       //return  admins;
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

        if (index!= -1) {return marketDB.getProducts().get(index);}
        else return null;
    }
}
