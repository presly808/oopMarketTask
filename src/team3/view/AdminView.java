package team3.view;

import team3.controller.IAdminController;
import team3.exception.NoProductFoundException;
import team3.model.Admin;
import team3.model.Product;

import java.util.Scanner;

public class AdminView {


    private Admin admin;
    private IAdminController adminController;

    public AdminView(Admin admin, IAdminController adminController) {
        this.admin = admin;
        this.adminController = adminController;
    }

    public void startMenu(){
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while(choice != 5){
            showMenu();
            choice = sc.nextInt();

            if(choice == 1){
                addProductMenu(sc);
            } else if(choice == 2){
                deleteProductMenu(sc);
            } else if(choice == 3){
                findProductMenu(sc);
            } else if(choice == 4){
                showAllMenu();
            } else {
                System.out.println("WRONG MENU ITEM");
            }

        }
    }

    private void showAllMenu() {
        System.out.println("********************");
        System.out.println("PRODUCT LIST");
        for (Product o : adminController.getAll()) {
            System.out.println(o);
        }
        System.out.println("********************");
    }

    public void showMenu(){
        System.out.println("1.Add product");
        System.out.println("2.Delete product");
        System.out.println("3.Find product");
        System.out.println("4.Get all product");
        System.out.println("5.BACK TO LOGIN MENU");
    }

    public void addProductMenu(Scanner sc){
        System.out.println("Input bar code");
        String barCode = sc.next();
        System.out.println("Input model");
        String model = sc.next();
        System.out.println("Input price");
        double price = sc.nextInt();

        adminController.addProduct(barCode, model, price);
        System.out.println("Product added");

    }



    public void findProductMenu(Scanner sc){
        System.out.println("Input bar code");
        String barCode = sc.next();

        Product found = null;
        try {
            found = adminController.findProductByCode(barCode);
        } catch (NoProductFoundException e) {
            e.printStackTrace();
        }

        System.out.println(found);
    }


    public void deleteProductMenu(Scanner sc){
        System.out.println("Input bar code");
        String barCode = sc.next();
        System.out.println("********************");
        System.out.println("PRODUCT");
        boolean deleted = adminController.deleteProduct(barCode);
        System.out.println("deleted " + deleted);
        System.out.println("********************");
    }



}
