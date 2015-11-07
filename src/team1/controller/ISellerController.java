package team1.controller;

import team1.model.Product;

import java.util.ArrayList;

/**
 * Created by serhii on 25.10.15.
 */
public interface ISellerController {

    ArrayList<Product> getAll();

    Product findProductByName(String name);

    Product findProductByCode(String barCode);


}
