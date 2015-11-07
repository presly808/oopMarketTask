package team3.controller;

import team3.exception.NoProductFoundException;
import team3.model.Product;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public interface IAdminController {

    boolean addProduct(String barCode, String model, double price);

    boolean deleteProduct(String barCode);

    ArrayList<Product> getAll();

    String getAllProductsString();

    Product findProductByName(String name);

    Product findProductByCode(String barCode) throws NoProductFoundException;


}
