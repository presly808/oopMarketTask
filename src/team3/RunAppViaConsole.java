package team3;

import team3.controller.AdminController;
import team3.controller.IAdminController;
import team3.controller.ISellerController;
import team3.controller.SellerController;
import team3.model.Admin;
import team3.model.MarketDB;
import team3.model.Product;
import team3.model.Seller;
import team3.view.AdminView;
import team3.view.SellerView;

import java.util.ArrayList;
import java.util.Scanner;



public class RunAppViaConsole {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);



        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("00101", "tea", 15.60));
        products.add(new Product("00111", "sugar", 17.60));
        products.add(new Product("01111", "coffee", 5.60));
        products.add(new Product("11111", "candles", 19.30));
        products.add(new Product("11110", "bread", 7.60));

        MarketDB marketDB = new MarketDB();
        marketDB.setProducts(products);

        Admin admin = new Admin(343, "Kolia", "1234");
        IAdminController iAdminController = new AdminController(marketDB);

        Seller seller = new Seller(346, "Ivan", "9856");
        ISellerController iSellerController = new SellerController(marketDB);


//        ArrayList<Admin> admins = new ArrayList<>();
//        admins.add(admin);
//        marketDB.setAdmins(admins);
//
//        ArrayList<Seller> sellers = new ArrayList<>();
//        sellers.add(seller);
//        marketDB.setSellers(sellers);
//
//        GeneraView generaView = new GeneraView(marketDB,iAdminController,iSellerController);

        int choice = -1;

        while (choice != 3) {

            // showLoginMenu();
            System.out.println("1.Admin");
            System.out.println("2.Seller");
            System.out.println("3.EXIT");

            choice = sc.nextInt();

            if (choice == 1) {
                AdminView adminView = new AdminView(admin, iAdminController);

                adminView.startMenu();
            } else if (choice == 2) {
                SellerView sellerView = new SellerView(seller, iSellerController);

                sellerView.startMenu();
            } else if(choice == 3){
                System.out.println("EXIT");
            }

        }




    }
}
