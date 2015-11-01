package team1.controller;

import team1.model.MarketDB;
import team1.model.Product;

import java.util.ArrayList;


public class SellerController implements ISellerController{
    private MarketDB marketDB;

    public SellerController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public ArrayList<Product> getAll() {
        return marketDB.getProducts();
    }

    @Override
    public Product findProductByName(String name) {
        Product tempProductForFind = new Product();
        tempProductForFind.setModel(name);
        int index = marketDB.getProducts().indexOf(tempProductForFind);
        return marketDB.getProducts().get(index);
    }

    @Override
    public Product findProductByCode(String barCode) {
        Product tempProductForFind = new Product();
        tempProductForFind.setBarCode(barCode);
        int index = marketDB.getProducts().indexOf(tempProductForFind);

        return marketDB.getProducts().get(index);
    }
}
