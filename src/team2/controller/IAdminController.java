package market.controller;

import market.model.Product;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public interface IAdminController {

    void addProduct(String barCode, String model, double price);
    boolean deleteProduct(String barCode);
    ArrayList<Product> getAll();
    Product findProductByName(String name);
    Product findProductByCode(String barCode);


}
