package team1.view;

import team1.Authentication.LoginPass;
import team1.controller.SellerController;
import team1.model.Product;
import team1.model.Seller;

import java.util.Scanner;

public class SellerView {
    private Seller seller;
    private SellerController sellerController;

    public SellerView(Seller seller, SellerController sellerController) {
        this.seller = seller;
        this.sellerController = sellerController;
    }

    public void startMenu(){
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while(choice != 3){
            showMenu();
            choice = sc.nextInt();

            if(choice == 1){
                findProductMenu(sc);
            } else if(choice == 2) {
                showAllMenu();
            } else if (choice == 3){
                break;
            } else {
                System.out.println("WRONG MENU ITEM");
            }

        }
    }

    public void findProductMenu(Scanner sc){
        System.out.println("Input bar code");
        String barCode = sc.next();

        Product found = sellerController.findProductByCode(barCode);

        System.out.println(found);
    }

    private void showAllMenu() {
        System.out.println("********************");
        System.out.println("PRODUCT LIST");
        for (Product o : sellerController.getAll()) {
            System.out.println(o);
        }
        System.out.println("********************");
    }

    public void showMenu(){
        System.out.println("1.Find product");
        System.out.println("2.Get all product");
        System.out.println("3.EXIT");
    }
}
