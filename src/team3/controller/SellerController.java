package team3.controller;

import team3.model.Bill;
import team3.model.MarketDB;
import team3.model.Product;
import team3.model.Seller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 1 on 30.10.2015.
 */
public class SellerController implements ISellerController {

    private MarketDB marketDB;

    public SellerController(MarketDB marketDB) {
        this.marketDB = marketDB;
    }

    @Override
    public Bill createBill(Seller seller) {


        Bill bill = new Bill(seller, new SimpleDateFormat().format(new Date()));

        return bill;
    }

    @Override
    public boolean scanProduct(Bill bill, String barCode) {

        for (Product tempProductToFind : marketDB.getProducts()){
            if (tempProductToFind.getBarCode().equals(barCode)){

                bill.setAmountPrice(+ tempProductToFind.getPrice());
                return bill.getProducts().add(tempProductToFind);
            }
        }

        return false;

    }

    @Override
    public boolean eraseProduct(Bill bill, String barCode) {

        for (Product tempProductToErase : bill.getProducts()){
            if (tempProductToErase.getBarCode().equals(barCode)){

                bill.getProducts().remove(tempProductToErase);
                bill.setAmountPrice(- tempProductToErase.getPrice());
                return true;

            }
        }

        return false;
    }


    @Override
    public String scanProductInfo(String barCode) {

        for (Product tempProductToFind : marketDB.getProducts()){

            if (tempProductToFind.getBarCode().equals(barCode)){

                return tempProductToFind.toString();

            }
        }

        return "NO SUCH PRODUCT";

    }



    @Override
    public boolean getPaid(Bill bill, double money) {

        if (money >= bill.getAmountPrice()) {
            marketDB.setAmountMoney(+bill.getAmountPrice());

            for (Product tempProductToDelete : bill.getProducts()) {

                marketDB.getProducts().remove(tempProductToDelete);

            }

            marketDB.getBills().add(bill);

            return true;
        }

        return false;


    }

    @Override
    public void showBill(Bill bill) {
        System.out.println(bill.toString());
    }

    public MarketDB getMarketDB() {
        return marketDB;
    }

    public void setMarketDB(MarketDB marketDB) {
        this.marketDB = marketDB;
    }
}
