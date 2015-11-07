package team3.view;

import team3.controller.ISellerController;
import team3.model.Bill;
import team3.model.Product;
import team3.model.Seller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SellerView {


    private Seller seller;
    private ISellerController sellerController;

    public SellerView(Seller seller, ISellerController sellerController) {
        this.seller = seller;
        this.sellerController = sellerController;
    }

    public void startMenu(){
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        while(choice != 3){
            showExternalMenu();
            choice = sc.nextInt();

            if(choice == 1){
                serveCustomerMenu(sc);
            } else if(choice == 2){
                scanProductInfoMenu(sc);
            } else if (choice == 3) {
                System.out.println("BACK TO LOGIN MENU");
            } else {
                System.out.println("WRONG MENU ITEM");
            }

        }
    }

    private void scanProductInfoMenu(Scanner sc) {

        System.out.println("Barcode: ");
        String barCode = sc.next();
        System.out.println(sellerController.scanProductInfo(barCode));

    }

    private void serveCustomerMenu(Scanner sc) {

        //Bill bill = sellerController.createBill(seller, new SimpleDateFormat().format(new Date()));
        Bill bill = sellerController.createBill(seller);

        int choice = -1;
        while (choice != 5) {
            showInternalMenu();
            choice = sc.nextInt();

            if (choice == 1) {
                //showAllMenu(bill);
                scanProductMenu(sc, bill);
            } else if (choice == 2) {
                //showAllMenu(bill);
                eraseProductMenu(sc, bill);
            }  else if (choice == 3) {
                showAllMenu(bill);
            } else if (choice == 4) {
                getPaidMenu(sc, bill);
                choice = 5;
            } else if (choice == 5) {
                System.out.println("CANCELED");
            } else {
                System.out.println("WRONG MENU ITEM");
            }
        }

    }



    private void getPaidMenu(Scanner sc, Bill bill) {

        System.out.printf("To pay: %f\n",bill.getAmountPrice());

        System.out.println("Customer gives: ");
        double money = sc.nextDouble();

        if (sellerController.getPaid(bill,money)){
            sellerController.showBill(bill);
        } else {
            System.out.println("NOT ENOUGH MONEY");
            if (money != 0.0){
                getPaidMenu(sc, bill);
            }
        }



    }

    private void eraseProductMenu(Scanner sc, Bill bill) {

        System.out.println("Barcode: ");
        String barCode = sc.next();
        System.out.println(sellerController.eraseProduct(bill, barCode));

    }

    private void scanProductMenu(Scanner sc, Bill bill) {

        System.out.println("Barcode: ");
        String barCode = sc.next();
        System.out.println(sellerController.scanProduct(bill, barCode));

    }

    private void showInternalMenu() {

        System.out.println("1.Scan product");
        System.out.println("2.Erase product");
        System.out.println("3.Show all products");
        System.out.println("4.Get paid");
        System.out.println("5.CANCEL");

    }



    public void showExternalMenu(){
        System.out.println("1.Serve customer");
        System.out.println("2.Scan product info");
        System.out.println("3.BACK TO LOGIN MENU");
    }


    private void showAllMenu(Bill bill) {
        System.out.println("********************");
        System.out.println("PRODUCT LIST");
        for (Product product : bill.getProducts()) {
            System.out.println(product.toString());
        }
        System.out.println("********************");
        System.out.printf("To pay: %f\n", bill.getAmountPrice());

    }




}


