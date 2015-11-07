package team3.controller;

import team3.model.MarketDB;
import team3.model.Product;

import java.util.ArrayList;


public class AdminController implements IAdminController {

    private MarketDB marketDB;

    public AdminController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public boolean addProduct(String barCode, String model, double price) {
        Product product = new Product(barCode, model, price);
        for (Product tempProduct : marketDB.getProducts()){
            if (tempProduct.getBarCode().equals(product.getBarCode())){
                return false;
            }
        }
        return marketDB.getProducts().add(product);
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
    public String getAllProductsString() {
        String list = "";
        for (Product product : getAll()){
            list += product.toString() + "\n";
        }
        return list;
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

        if (index != -1){
            return marketDB.getProducts().get(index);
        }
        return null;

    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    public void setMarketDB(MarketDB marketDB) {
        this.marketDB = marketDB;
    }
}
