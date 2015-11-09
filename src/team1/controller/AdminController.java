package team1.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import team1.constant.ProjectConstants;
import team1.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class AdminController implements IAdminController {

    public static final String RESOURCES_JSON_PATH = ProjectConstants.RESOURCES_JSON_PATH;

    public static final String SRC_TEAM1_JSON_FILES_PRODUCT_FILE_JSON = RESOURCES_JSON_PATH + "/ProductFile.json";
    public static final String SRC_TEAM1_JSON_FILES_PRODUCT_FILE_JSON1 = RESOURCES_JSON_PATH + "/ProductFile.json";
    public static final String SRC_TEAM1_JSON_FILES_SELLER_USERS_JSON = RESOURCES_JSON_PATH + "/SellerUsers.json";
    public static final String SRC_TEAM1_JSON_FILES_ADMIN_USERS_JSON = RESOURCES_JSON_PATH + "/AdminUsers.json";
    public static final String SRC_TEAM1_JSON_FILES_SELLER_USERS_JSON1 = RESOURCES_JSON_PATH + "/SellerUsers.json";
    public static final String SRC_TEAM1_JSON_FILES_ADMIN_USERS_JSON1 = RESOURCES_JSON_PATH + "/AdminUsers.json";

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


      return users;

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

    public void updateAdmins() throws ParseException {
        String path = SRC_TEAM1_JSON_FILES_ADMIN_USERS_JSON1;
        File adminFile = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(adminFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(line);
            Admin res = new Admin();
            res.setId((int) (long) obj.get("id"));
            res.setLogin((String) obj.get("login"));
            res.setPass((String) obj.get("pass"));
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getAdmins().add(res);
        }

    }

    public void updateSellers() throws ParseException {
        String path = SRC_TEAM1_JSON_FILES_SELLER_USERS_JSON1;
        File sellerFile = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(sellerFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(line);
            Seller res = new Seller();
            res.setId((int) (long) obj.get("id"));
            res.setLogin((String) obj.get("login"));
            res.setPass((String) obj.get("pass"));
            //System.out.printf("%d %s %s\n", res.getId(), res.getLogin(), res.getPass());
            marketDB.getSellers().add(res);
        }

    }

    public void addToAdminFile(Admin admin) {
        String path = SRC_TEAM1_JSON_FILES_ADMIN_USERS_JSON;
        File jsonAdminFile = new File(path);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", admin.getId());
        jsonObject.put("login", admin.getLogin());
        jsonObject.put("pass", admin.getPass());
        try {
            FileWriter fileWriter = new FileWriter(jsonAdminFile, true);
            fileWriter.write(jsonObject.toString());
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToSellerFile(Seller seller) {
        String path = SRC_TEAM1_JSON_FILES_SELLER_USERS_JSON;
        File jsonSellerFile = new File(path);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", seller.getId());
        jsonObject.put("login", seller.getLogin());
        jsonObject.put("pass", seller.getPass());
        try {
            FileWriter fileWriter = new FileWriter(jsonSellerFile, true);
            fileWriter.write(jsonObject.toString());
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToProductFile(Product product){
        String path = SRC_TEAM1_JSON_FILES_PRODUCT_FILE_JSON;
        File jsonProductFile = new File(path);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("barcode", product.getBarCode());
        jsonObject.put("model", product.getModel());
        jsonObject.put("price", product.getPrice());
        try {
            FileWriter fileWriter = new FileWriter(jsonProductFile, true);
            fileWriter.write(jsonObject.toString());
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromProductFile() throws ParseException {
        String path = SRC_TEAM1_JSON_FILES_PRODUCT_FILE_JSON1;
        File jsonProductFile = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(jsonProductFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(line);
            Product res = new Product();
            res.setBarCode((String) obj.get("barcode"));
            res.setModel((String) obj.get("model"));
            res.setPrice((Double) obj.get("price"));
            marketDB.getProducts().add(res);
        }
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }
}
