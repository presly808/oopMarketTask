package team1.controller;

import team1.model.Product;
import team1.model.User;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public interface IAdminController {

    Product addProduct(String barCode, String model, double price);

    void renameProduct(Product product, String barCode, String model, double price);

    boolean deleteProduct(String barCode);

    ArrayList<Product> getAll();

    Product findProductByName(String name);

    Product findProductByCode(String barCode);

    ArrayList<User> getAllUsers();


}
